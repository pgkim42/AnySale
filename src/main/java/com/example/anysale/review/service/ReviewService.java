package com.example.anysale.review.service;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.entity.Review;
import com.example.anysale.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .rating(dto.getRating())
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
                .rating(entity.getRating())
                .build();

        return dto;
    }

    List<ReviewDTO> getList();

    void remove(int no);

    List<ReviewDTO> getReviewIdList(String sellerId);

    List<Review> searchReviews(String search);

}
