package com.example.anysale.review.controller;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.entity.Review;
import com.example.anysale.review.service.ReviewService;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/main")
    public String main() {
        System.out.println("main call 확인: ");
        return "review/main";
    }

    @GetMapping("/register")
    public String register() {
        return "review/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestBody ReviewDTO dto) {
        System.out.println("리뷰 등록 확인: " + dto);
        int newReviewNo = reviewService.register(dto);
        return newReviewNo;
    }

}
