package com.example.anysale.review.service;

import com.example.anysale.review.dto.ReviewDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    int register(ReviewDTO dto);



}
