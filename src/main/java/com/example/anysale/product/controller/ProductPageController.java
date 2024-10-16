package com.example.anysale.product.controller;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductPageController {

    private final ProductService productService;

    public ProductPageController(ProductService productService) {
        this.productService = productService;
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
        return "redirect:/products";
    }

    @GetMapping("/products/detail/{itemCode}")
    public String showProductDetailPage(Model model, @PathVariable String itemCode) {
        ProductDTO productDTO = productService.getProductById(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with itemCode: " + itemCode));
        model.addAttribute("productDTO", productDTO);
        return "product/product-detail";
    }
}
