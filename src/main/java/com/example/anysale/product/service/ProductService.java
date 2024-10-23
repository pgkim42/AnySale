package com.example.anysale.product.service;

import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

import java.util.Optional;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> getProductById(String itemCode);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(String itemCode, ProductDTO productDTO);

    void deleteProduct(String itemCode);

    Page<ProductDTO> getPagedProducts(int page, int size);

    Page<ProductDTO> searchProducts(String searchType, String keyword, int page, int size);

    Page<ProductDTO> searchProductsByTitle(String title, int page, int size);
    Page<ProductDTO> searchProductsByCategory(String category, int page, int size);

    default Product dtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .itemCode(productDTO.getItemCode())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .title(productDTO.getTitle())
                .content(productDTO.getContent())
                .productCondition(productDTO.getProductCondition())
                .imageUrl(productDTO.getImageUrl())
                .dealDate(productDTO.getDealDate())
                .status(productDTO.getStatus())
                .location(productDTO.getLocation())
                .userId(productDTO.getUserId())  // userId를 반드시 매핑
                .build();
    }

    default ProductDTO entityToDto(Product product) {
        return ProductDTO.builder()
                .itemCode(product.getItemCode())
                .price(product.getPrice())
                .category(product.getCategory())
                .title(product.getTitle())
                .content(product.getContent())
                .productCondition(product.getProductCondition())
                .imageUrl(product.getImageUrl())
                .dealDate(product.getDealDate())
                .status(product.getStatus())
                .location(product.getLocation())
                .userId(product.getUserId())
                .createDate(product.getCreateDate())
                .updateDate(product.getUpdateDate())
                .build();
    }


}

