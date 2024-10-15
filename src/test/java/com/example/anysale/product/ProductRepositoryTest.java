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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)  // Spring 테스트 컨텍스트 확장
@SpringBootTest  // SpringBoot 애플리케이션 전체를 로드하여 통합 테스트
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProductTest() {
        // given
        Product product = Product.builder()
                .itemCode("ITEM123")
                .price("10000")
                .category("Electronics")
                .content("Smartphone")
                .productCondition("New")
                .imageUrl("http://example.com/image.jpg")
                .regDate(LocalDateTime.now())
                .dealDate(LocalDateTime.now())
                .status("Available")
                .location("Seoul")
                .userId("user123")
                .build();

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertNotNull(savedProduct);
        assertEquals("ITEM123", savedProduct.getItemCode());
        assertEquals("10000", savedProduct.getPrice());
        assertEquals("Electronics", savedProduct.getCategory());
        assertEquals("Smartphone", savedProduct.getContent());
    }

    @Test
    public void findProductByIdTest() {
        // given
        Product product = Product.builder()
                .itemCode("ITEM124")
                .price("20000")
                .category("Home Appliances")
                .content("Vacuum Cleaner")
                .productCondition("Used")
                .imageUrl("http://example.com/vacuum.jpg")
                .regDate(LocalDateTime.now())
                .dealDate(LocalDateTime.now())
                .status("Available")
                .location("Busan")
                .userId("user124")
                .build();

        productRepository.save(product);

        // when
        Optional<Product> foundProduct = productRepository.findById("ITEM124");

        // then
        assertTrue(foundProduct.isPresent());
        assertEquals("Vacuum Cleaner", foundProduct.get().getContent());
        assertEquals("20000", foundProduct.get().getPrice());
    }

    @Test
    public void deleteProductTest() {
        // given
        Product product = Product.builder()
                .itemCode("ITEM125")
                .price("30000")
                .category("Furniture")
                .content("Sofa")
                .productCondition("Like New")
                .imageUrl("http://example.com/sofa.jpg")
                .regDate(LocalDateTime.now())
                .dealDate(LocalDateTime.now())
                .status("Available")
                .location("Incheon")
                .userId("user125")
                .build();

        productRepository.save(product);

        // when
        productRepository.deleteById("ITEM125");

        // then
        Optional<Product> deletedProduct = productRepository.findById("ITEM125");
        assertFalse(deletedProduct.isPresent());
    }
}

