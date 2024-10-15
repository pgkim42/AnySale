package com.example.anysale.product;

import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProduct() {
        Product product = Product.builder()
                .itemCode("A001")
                .category("Electronics")
                .content("Test Product")
                .imageUrl("test.jpg")
                .location("Seoul")
                .price("10000")
                .productCondition("New")
                .regDate(java.time.LocalDateTime.now())
                .status("Available")
                .userId("user1")
                .build();

        productRepository.save(product);
    }

    @Test
    void saveProducts2() {
        List<Product> products = Arrays.asList(
                Product.builder()
                        .itemCode("A002")
                        .category("Electronics")
                        .content("Test Product 2")
                        .imageUrl("test2.jpg")
                        .location("Busan")
                        .price("15000")
                        .productCondition("Used")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user2")
                        .build(),
                Product.builder()
                        .itemCode("A003")
                        .category("Books")
                        .content("Test Product 3")
                        .imageUrl("test3.jpg")
                        .location("Incheon")
                        .price("5000")
                        .productCondition("Like New")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user3")
                        .build(),
                Product.builder()
                        .itemCode("A004")
                        .category("Furniture")
                        .content("Test Product 4")
                        .imageUrl("test4.jpg")
                        .location("Daegu")
                        .price("30000")
                        .productCondition("Used")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user4")
                        .build(),
                Product.builder()
                        .itemCode("A005")
                        .category("Clothing")
                        .content("Test Product 5")
                        .imageUrl("test5.jpg")
                        .location("Daejeon")
                        .price("8000")
                        .productCondition("New")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user5")
                        .build(),
                Product.builder()
                        .itemCode("A006")
                        .category("Electronics")
                        .content("Test Product 6")
                        .imageUrl("test6.jpg")
                        .location("Gwangju")
                        .price("20000")
                        .productCondition("Used")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user6")
                        .build(),
                Product.builder()
                        .itemCode("A007")
                        .category("Kitchen")
                        .content("Test Product 7")
                        .imageUrl("test7.jpg")
                        .location("Ulsan")
                        .price("12000")
                        .productCondition("Like New")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user7")
                        .build(),
                Product.builder()
                        .itemCode("A008")
                        .category("Toys")
                        .content("Test Product 8")
                        .imageUrl("test8.jpg")
                        .location("Sejong")
                        .price("6000")
                        .productCondition("New")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user8")
                        .build(),
                Product.builder()
                        .itemCode("A009")
                        .category("Books")
                        .content("Test Product 9")
                        .imageUrl("test9.jpg")
                        .location("Gyeonggi")
                        .price("4000")
                        .productCondition("Used")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user9")
                        .build(),
                Product.builder()
                        .itemCode("A010")
                        .category("Furniture")
                        .content("Test Product 10")
                        .imageUrl("test10.jpg")
                        .location("Gangwon")
                        .price("50000")
                        .productCondition("Used")
                        .regDate(java.time.LocalDateTime.now())
                        .status("Available")
                        .userId("user10")
                        .build()
        );

        productRepository.saveAll(products);
    }

}
