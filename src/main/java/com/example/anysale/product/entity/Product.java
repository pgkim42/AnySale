package com.example.anysale.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    @Column(name = "item_code")
    private String itemCode;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String productCondition;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime regDate;

    private LocalDateTime modDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String userId;

}
