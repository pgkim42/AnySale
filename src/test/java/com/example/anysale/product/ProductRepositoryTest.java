package com.example.anysale.product;

import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        // given
        Product product = Product.builder()
                .itemCode("ITEM001")
                .price("15000")
                .category("Books")
                .content("Java Programming Book")
                .productCondition("New")
                .imageUrl("http://example.com/book.jpg")
                .dealDate(LocalDateTime.now())
                .status("Available")
                .location("Seoul")
                .userId("user001")
                .build();

        // when
        productRepository.save(product);

        // then
        System.out.println("Saved Product: " + product);
    }

    @Test
    public void testFindProductById() {
        // given
        String itemCode = "ITEM001";

        // when
        Optional<Product> product = productRepository.findById(itemCode);

        // then
        if (product.isPresent()) {
            System.out.println("Found Product: " + product.get());
        } else {
            System.out.println("Product with itemCode " + itemCode + " not found.");
        }
    }

    @Test
    public void testFindAllProducts() {
        // when
        List<Product> products = productRepository.findAll();

        // then
        System.out.println("All Products: ");
        products.forEach(System.out::println);
    }

    @Test
    public void testUpdateProduct() {
        // given
        String itemCode = "ITEM001";
        Optional<Product> productOpt = productRepository.findById(itemCode);

        // when
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setPrice("20000");
            product.setContent("Updated Java Programming Book");

            productRepository.save(product);

            System.out.println("Updated Product: " + product);
        } else {
            System.out.println("Product with itemCode " + itemCode + " not found for update.");
        }
    }

    @Test
    public void testDeleteProduct() {
        // given
        String itemCode = "ITEM001";

        // when
        productRepository.deleteById(itemCode);

        // then
        Optional<Product> product = productRepository.findById(itemCode);
        if (product.isPresent()) {
            System.out.println("Failed to delete Product with itemCode " + itemCode);
        } else {
            System.out.println("Product with itemCode " + itemCode + " deleted successfully.");
        }
    }
}
