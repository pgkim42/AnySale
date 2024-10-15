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

    int review_no; // 리뷰 고유ID(게시글 번호)

    String buyer_id; // 회원 테이블의 FK(구매자)

    String seller_id; // 회원 테이블의 FK(판매자)

    String comment; // 리뷰내용

    int rating; // 별점

    LocalDateTime review_date; // 리뷰 작성일

}
