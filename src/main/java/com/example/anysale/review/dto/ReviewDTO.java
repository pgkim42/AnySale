package com.example.anysale.review.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    String comment; // 리뷰내용

    String buyerAddress; // 구매자의 지역

    String buyerProfile; // 구매자 프사 URL

    int rating; // 별점

    List<String> mannerCheck; // 칭찬항목

    LocalDateTime reviewDate; // 리뷰 작성일

}
