package com.example.anysale.product.repository;

import com.example.anysale.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    // 생성일 역순으로 정렬하는 메서드
    List<Product> findAllByOrderByCreateDateDesc();

    // 제목으로 검색하여 페이징 처리
    Page<Product> findByTitleContaining(String title, Pageable pageable);

    // 카테고리로 검색하여 페이징 처리
    Page<Product> findByCategoryContaining(String category, Pageable pageable);


}
