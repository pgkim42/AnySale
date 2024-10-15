package com.example.anysale.product.controller;

import com.example.anysale.product.entity.Product;
import com.example.anysale.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println("product = " + product);
        }
        model.addAttribute("products", products);
        return "productList";
    }
}
