package com.example.anysale.product.service;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    // 지정된 길이만큼 랜덤 문자열 생성
    private String generateRandomItemCode(int length) {
        StringBuilder itemCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            itemCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return itemCode.toString();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        if (productDTO.getItemCode() == null || productDTO.getItemCode().isEmpty()) {
            productDTO.setItemCode(generateRandomItemCode(10));  // 10자리의 랜덤 상품 코드 생성
        }

        productDTO.setStatus("대기중");

        Product product = dtoToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return entityToDto(savedProduct);
    }

    @Override
    public Optional<ProductDTO> getProductById(String itemCode) {
        Optional<Product> product = productRepository.findById(itemCode);
        return product.map(this::entityToDto);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAllByOrderByCreateDateDesc();
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(String itemCode, ProductDTO productDTO) {
        Optional<Product> productOpt = productRepository.findById(itemCode);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            product.setContent(productDTO.getContent());
            product.setProductCondition(productDTO.getProductCondition());
            product.setImageUrl(productDTO.getImageUrl());
            product.setModDate(productDTO.getModDate());
            product.setDealDate(productDTO.getDealDate());
            product.setStatus(productDTO.getStatus());
            product.setLocation(productDTO.getLocation());
            Product updatedProduct = productRepository.save(product);
            return entityToDto(updatedProduct);
        } else {
            throw new IllegalArgumentException("Product not found with itemCode: " + itemCode);
        }
    }

    @Override
    public void deleteProduct(String itemCode) {
        productRepository.deleteById(itemCode);
    }
}
