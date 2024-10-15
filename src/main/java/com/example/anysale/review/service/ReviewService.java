package com.example.anysale.review.service;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.entity.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    int register(ReviewDTO dto);

    default Review dtoToEntity(ReviewDTO dto) {

        Review entity = Review.builder()
                .reviewNo(dto.getReviewNo())
                .buyerId(dto.getBuyerId())
                .sellerId(dto.getSellerId())
                .reviewNo(dto.getReviewNo())
                .comment(dto.getComment())
                .build();

        return entity;
    }

    default ReviewDTO entityToDto(Review entity) {

        ReviewDTO dto = ReviewDTO.builder()
                .reviewNo(entity.getReviewNo())
                .buyerId(entity.getBuyerId())
                .sellerId(entity.getSellerId())
                .reviewNo(entity.getReviewNo())
                .comment(entity.getComment())
                .reviewDate(entity.getCreateDate())
                .build();

        return dto;
    }

}
