package com.example.anysale.review.controller;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.entity.Review;
import com.example.anysale.review.service.ReviewService;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String register(@ModelAttribute ReviewDTO dto, RedirectAttributes redirectAttributes) {
        System.out.println("리뷰 등록 확인: " + dto);
        int newReviewNo = reviewService.register(dto);
        redirectAttributes.addFlashAttribute("message", "리뷰가 성공적으로 등록되었습니다.");
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<ReviewDTO> list = reviewService.getList();
        model.addAttribute("list", list);

        return "review/list";
    }

}
