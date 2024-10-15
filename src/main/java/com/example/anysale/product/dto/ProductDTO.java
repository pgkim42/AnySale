package com.example.anysale.product.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private String itemCode;
    private String price;
    private String category;
    private String content;
    private String productCondition;
    private String imageUrl;
    private LocalDateTime dealDate;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String status;
    private String location;
    private String userId;

}
