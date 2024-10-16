package com.example.anysale.product.service;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.entity.Product;

import java.util.List;

import java.util.Optional;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> getProductById(String itemCode);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(String itemCode, ProductDTO productDTO);

    void deleteProduct(String itemCode);

    default Product dtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .itemCode(productDTO.getItemCode())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .content(productDTO.getContent())
                .productCondition(productDTO.getProductCondition())
                .imageUrl(productDTO.getImageUrl())
                .dealDate(productDTO.getDealDate())
                .status(productDTO.getStatus())
                .location(productDTO.getLocation())
                .userId(productDTO.getUserId())  // userId를 반드시 매핑
                .modDate(productDTO.getModDate())
                .build();
    }

    default ProductDTO entityToDto(Product product) {
        return ProductDTO.builder()
                .itemCode(product.getItemCode())
                .price(product.getPrice())
                .category(product.getCategory())
                .content(product.getContent())
                .productCondition(product.getProductCondition())
                .imageUrl(product.getImageUrl())
                .dealDate(product.getDealDate())
                .status(product.getStatus())
                .location(product.getLocation())
                .userId(product.getUserId())
                .modDate(product.getModDate())  // 수정 날짜가 있으면 설정
                .build();
    }


}

