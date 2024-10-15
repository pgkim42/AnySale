package com.example.anysale.review;

import com.example.anysale.review.entity.Review;
import com.example.anysale.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void 리뷰등록() {

        Review review = Review.builder()
                .review_no(0)
                .buyer_id("정성민")
                .seller_id("김평기")
                .comment("쿨거래 감사합니다.")
                .rating(5)
                .build();

        Review review2 = Review.builder()
                .review_no(0)
                .buyer_id("심태훈")
                .seller_id("김인수")
                .comment("쿨거래 감사합니다2.")
                .rating(1)
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        System.out.println(review);

    }

    @Test
    void 리뷰조회() {
        List<Review> list = reviewRepository.findAll();
        for(Review review : list) {
            System.out.println(review);
        }
    }

    @Test
    void 리뷰수정() {
        Optional<Review> findNo = reviewRepository.findById(2);
        Review review = findNo.get();
        review.setComment("쿨거래 취소요 사기꾼임");
        reviewRepository.save(review);

    }

    @Test
    void 리뷰삭제() {
        reviewRepository.deleteById(1);
    }

}
