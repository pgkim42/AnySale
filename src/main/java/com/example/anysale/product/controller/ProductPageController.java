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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.*;

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
            Model model, HttpSession session) {

        String userId = (String) session.getAttribute("userId");

        Member member = null;
        if (userId != null) {
            member = memberService.getMemberById(userId).orElse(null);
        }

        // 페이징된 결과 가져오기
        Page<ProductDTO> productsPage = productService.getPagedProducts(page, size);

        // totalPages가 0인 경우 최소 1로 설정
        int totalPages = productsPage.getTotalPages();
        if (totalPages == 0) {
            totalPages = 1;
        }

        model.addAttribute("member", member);
        model.addAttribute("products", productsPage.getContent());  // 현재 페이지의 제품 목록
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", totalPages);  // 총 페이지 수

        return "product/product-list";
    }

    // 상품 등록 페이지
    @GetMapping("/products/add")
    public String showAddProductPage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/member/login";  // 로그인 페이지로 리다이렉트
        }

        Member member = memberService.getMemberById(userId).orElse(null);
        if (member == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("productDTO", new ProductDTO());

        return "product/product-add";  // 상품 등록 페이지로 이동
    }

    //
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductDTO productDTO,
                             @RequestParam("uploadFile") MultipartFile multipartFile, HttpServletRequest request) {

        System.out.println("요청 메소드: " + request.getMethod()); // 디버깅용 로그
        System.out.println("요청 URL: " + request.getRequestURL());

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

        String userId = (String) session.getAttribute("userId");

        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDealDate = productDTO.getDealDate().format(formatter);

        /* 시간 표시 계산용, 메서드로 만들 예정? */

        // 현재 시간과 등록일 차이를 계산
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(productDTO.getCreateDate(), now);

        // 시간 차이를 '몇 시간 전', '몇 분 전' 형식으로 변환
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        String formattedCreateDate;
        if (days > 0) {
            formattedCreateDate = days + "일 전";
        } else if (hours > 0) {
            formattedCreateDate = hours + "시간 전";
        } else if (minutes > 0) {
            formattedCreateDate = minutes + "분 전";
        } else {
            formattedCreateDate = "방금 전";
        }

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
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/member/login";
        }

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

        System.out.println("showUpdateProductPage의 imageUrl : " + productDTO.getImageUrl());

        return "product/product-update";
    }

    @PostMapping("/products/update/{itemCode}")
    public String updateProduct(@PathVariable("itemCode") String itemCode,
                                @ModelAttribute ProductDTO productDTO,
                                @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
                                @RequestParam(value = "existingImageUrl", required = false) String existingImageUrl) {

        // 파일이 업로드되었는지 확인 (null 체크 추가)
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String uploadedFileName = fileUtil.fileUpload(uploadFile);
            productDTO.setImageUrl(uploadedFileName);  // 새 이미지 설정
        } else {
            // 업로드된 파일이 없을 때는 기존 이미지 유지
            System.out.println("기존 이미지 url : " + existingImageUrl);
            if (existingImageUrl != null) {
                productDTO.setImageUrl(existingImageUrl);  // 기존 이미지 유지
            }
        }

        productService.updateProduct(itemCode, productDTO);
        return "redirect:/products/detail/" + itemCode;  // 업데이트 후 상세 페이지로 이동
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
