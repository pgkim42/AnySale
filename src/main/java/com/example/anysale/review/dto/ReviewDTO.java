package com.example.anysale.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReviewDTO {

    int reviewNo; // 리뷰 고유ID(게시글 번호)

    String buyerId; // 회원 테이블의 FK(구매자)

    String sellerId; // 회원 테이블의 FK(판매자)

    String comment; // 리뷰내용S

    int rating; // 별점

    LocalDateTime reviewDate; // 리뷰 작성일

}
