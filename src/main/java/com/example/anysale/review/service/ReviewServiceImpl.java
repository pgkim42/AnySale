package com.example.anysale.review.service;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.entity.Review;
import com.example.anysale.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public int register(ReviewDTO dto) {

        System.out.println(dto);

        Review entity = dtoToEntity(dto);
        reviewRepository.save(entity);
        int newNo = entity.getReviewNo();

        System.out.println(entity);
        return newNo;

    }
}
