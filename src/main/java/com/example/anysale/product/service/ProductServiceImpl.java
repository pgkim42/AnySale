package com.example.anysale.product.service;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
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
        List<Product> products = productRepository.findAll();
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
