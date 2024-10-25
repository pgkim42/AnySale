package com.example.anysale.product.controller;

import com.example.anysale.member.controller.MemberController;
import com.example.anysale.member.dto.MemberDTO;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.service.MemberService;
import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.service.ProductService;
import com.example.anysale.comment.dto.CommentDTO;
import com.example.anysale.comment.service.CommentService;
import com.example.anysale.util.FileUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ProductPageController {

    private final ProductService productService;
    private final CommentService commentService;
    private final MemberService memberService;
    private final MemberController memberController;
    private final FileUtil fileUtil;

    @Autowired
    public ProductPageController(ProductService productService, CommentService commentService,
                                 MemberService memberService, MemberController memberController, FileUtil fileUtil) {
        this.productService = productService;
        this.commentService = commentService;
        this.memberService = memberService;
        this.memberController = memberController;
        this.fileUtil = fileUtil;
    }

    // 상품 목록 보기 (페이징 처리)
    @GetMapping("/products")
    public String showProductList(
            @RequestParam(value = "searchType", defaultValue = "title") String searchType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            Model model) {

        // SecurityContextHolder에서 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login"; // 로그인하지 않은 경우 로그인 페이지로 리디렉션
        }
        String userId = authentication.getName(); // 인증된 사용자 ID (예: email 또는 username)

        Member member = memberService.getMemberById(userId).orElse(null);

        // 페이징된 결과 가져오기
        Page<ProductDTO> productsPage = productService.getPagedProducts(page, size);

        // totalPages가 0인 경우 최소 1로 설정
        int totalPages = productsPage.getTotalPages();
        if (totalPages == 0) {
            totalPages = 1;
        }

        model.addAttribute("member", member);  // member 객체를 모델에 추가
        model.addAttribute("products", productsPage.getContent());  // 현재 페이지의 제품 목록
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", totalPages);  // 총 페이지 수

        return "product/product-list";  // 뷰 이름 반환
    }

    // 상품 등록 페이지
    @GetMapping("/products/add")
    public String showAddProductPage(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";  // 로그인 페이지로 리다이렉트
        }
        String userId = authentication.getName();

        Member member = memberService.getMemberById(userId).orElse(null);
        if (member == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("productDTO", new ProductDTO());

        return "product/product-add";  // 상품 등록 페이지로 이동
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductDTO productDTO,
                             @RequestParam("uploadFile") MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String imageUrl = fileUtil.fileUpload(multipartFile); // 파일 업로드 처리 로직
            productDTO.setImageUrl(imageUrl); // 파일 URL을 DTO에 설정
        }
        productService.saveProduct(productDTO);
        return "redirect:/products";
    }

    // 상품 상세 페이지
    @GetMapping("/products/detail/{itemCode}")
    public String showProductDetailPage(Model model, @PathVariable("itemCode") String itemCode, HttpSession session) {
        System.out.println("받은 itemCode: " + itemCode);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = null;
        if (authentication != null && authentication.isAuthenticated()) {
            userId = authentication.getName();
        }

        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDealDate = productDTO.getDealDate().format(formatter);
        String formattedCreateDate = productDTO.getCreateDate().format(formatter);

        if (productDTO.getUpdateDate() != null) {
            String formattedUpdateDate = productDTO.getUpdateDate().format(formatter);
            model.addAttribute("formattedUpdateDate", formattedUpdateDate);
        }

        List<CommentDTO> comments = commentService.getCommentsByItemCode(itemCode);

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("formattedDealDate", formattedDealDate);
        model.addAttribute("formattedCreateDate", formattedCreateDate);

        if (userId != null) {
            model.addAttribute("userid", userId);
            model.addAttribute("member", memberService.getMemberById(userId).orElse(null));
        } else {
            model.addAttribute("userid", "");
            model.addAttribute("member", new MemberDTO());
        }

        return "product/product-detail";
    }

    // 상품 수정 페이지
    @GetMapping("/products/update/{itemCode}")
    public String showUpdateProductPage(Model model, @PathVariable("itemCode") String itemCode, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";  // 로그인하지 않은 경우 로그인 페이지로 리디렉션
        }
        String userId = authentication.getName();

        Member member = memberService.getMemberById(userId).orElse(null);
        if (member == null) {
            return "redirect:/member/login";
        }

        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDealDate = productDTO.getDealDate().format(formatter);

        model.addAttribute("member", member);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("formattedDealDate", formattedDealDate);

        return "product/product-update";
    }

    // 상품 수정 처리
    @PostMapping("/products/update/{itemCode}")
    public String updateProduct(@PathVariable("itemCode") String itemCode, @ModelAttribute ProductDTO productDTO,
                                @RequestParam("dealDate") String dealDateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dealDate = LocalDateTime.parse(dealDateString, formatter);
        productDTO.setDealDate(dealDate);

        productService.updateProduct(itemCode, productDTO);
        return "redirect:/products/detail/" + itemCode;
    }

    // Ajax 검색 처리
    @GetMapping("/products/searchAjax")
    @ResponseBody
    public Page<ProductDTO> searchProductsAjax(
            @RequestParam(value = "searchType", defaultValue = "title") String searchType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        Page<ProductDTO> productsPage;
        if ("title".equals(searchType)) {
            productsPage = productService.searchProductsByTitle(keyword, page, size);
        } else if ("category".equals(searchType)) {
            productsPage = productService.searchProductsByCategory(keyword, page, size);
        } else {
            productsPage = productService.getPagedProducts(page, size);
        }

        return productsPage;
    }
}