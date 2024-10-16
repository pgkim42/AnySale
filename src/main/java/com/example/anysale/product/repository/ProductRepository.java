package com.example.anysale.product.repository;

import com.example.anysale.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByOrderByCreateDateDesc();
}
