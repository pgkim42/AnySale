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

    @PostMapping("/remove")
    public String remove(@RequestParam("no") int no){

        reviewService.remove(no);
        return "redirect:/review/list";

    }

    @GetMapping("/seller/{sellerId}")
    public String getReviewIdList(@PathVariable String sellerId, Model model) {
        List<ReviewDTO> sellerList = reviewService.getReviewIdList(sellerId);
        int reviewCount = sellerList.size();
        model.addAttribute("list", sellerList);
        model.addAttribute("reviewCount", reviewCount);
        return "review/seller";
    }

    @GetMapping("/sellerId")
    public String ReviewSearch(@RequestParam("sellerId") String sellerId, Model model) {
        List<ReviewDTO> reviewList = reviewService.searchReviews(sellerId);

        model.addAttribute("list", reviewList);

        if(reviewList.isEmpty()) {
            model.addAttribute("message", "검색결과가 없습니다.");
        } else {
            model.addAttribute("message", sellerId + "검색 결과입니다.");
        }

        return "review/list";
    }

}
