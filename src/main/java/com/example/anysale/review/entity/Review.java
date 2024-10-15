package com.example.anysale.review.entity;

import com.example.anysale.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reviewNo; // 리뷰 고유no(게시글 번호)

    @Column(length = 50, nullable = false)
    String buyerId; // 회원 테이블의 FK(구매자)

    @Column(length = 50, nullable = false)
    String sellerId; // 회원 테이블의 FK(판매자)

    @Column(length = 50, nullable = false)
    String comment; // 리뷰 내용

    @Column(length = 50, nullable = false)
    int rating; // 별점

}
