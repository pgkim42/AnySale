package com.example.anysale.product.controller;

import com.example.anysale.member.controller.MemberController;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.service.MemberService;
import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.service.ProductService;
import com.example.anysale.comment.dto.CommentDTO;
import com.example.anysale.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductPageController {

    private final ProductService productService;
    private final CommentService commentService;
    private final MemberService memberService;
    private final MemberController memberController;

    @Autowired
    public ProductPageController(ProductService productService, CommentService commentService, MemberService memberService, MemberController memberController) {
        this.productService = productService;
        this.commentService = commentService;
        this.memberService = memberService;
        this.memberController = memberController;
    }

    @GetMapping("/products")
    public String showProductList(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        Member member = null;
        if (userId != null) {
            member = memberService.getMemberById(userId).orElse(null); // Optional에서 값 꺼내기
        }

        model.addAttribute("member", member);
        model.addAttribute("products", productService.getAllProducts());
        return "product/product-list";  // 이 템플릿을 렌더링
    }

    @GetMapping("/products/add")
    public String showAddProductPage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        Member member = null;
        if (userId != null) {
            member = memberService.getMemberById(userId).orElse(null);
        }
        model.addAttribute("member", member);
        model.addAttribute("productDTO", new ProductDTO());
        return "product/product-add";
    }

    @PostMapping("/products/add")
    public String addProduct(ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/products/detail/{itemCode}")
    public String showProductDetailPage(Model model, @PathVariable("itemCode") String itemCode, HttpSession session ) {
        String userId = (String) session.getAttribute("userId");

        if(userId == null) {
            return "redirect:/member/login";
        }

        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));

        // 댓글 목록 가져오기
        List<CommentDTO> comments = commentService.getCommentsByItemCode(itemCode);

        // 상품과 댓글을 모델에 추가

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("userid", userId);
        model.addAttribute("member", memberService.getMemberById(userId).orElse(null));
        return "product/product-detail";
    }

}
