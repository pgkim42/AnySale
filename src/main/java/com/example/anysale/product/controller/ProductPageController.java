package com.example.anysale.product.controller;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.service.ProductService;
import com.example.anysale.comment.dto.CommentDTO;
import com.example.anysale.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductPageController {

    private final ProductService productService;
    private final CommentService commentService;

    @Autowired
    public ProductPageController(ProductService productService, CommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    @GetMapping("/products")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/product-list";  // 이 템플릿을 렌더링
    }

    @GetMapping("/products/add")
    public String showAddProductPage(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "product/product-add";
    }

    @PostMapping("/products/add")
    public String addProduct(ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/products/detail/{itemCode}")
    public String showProductDetailPage(Model model, @PathVariable String itemCode) {
        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));

        // 댓글 목록 가져오기
        List<CommentDTO> comments = commentService.getCommentsByItemCode(itemCode);

        // 상품과 댓글을 모델에 추가
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("comments", comments);
        return "product/product-detail";
    }
}
