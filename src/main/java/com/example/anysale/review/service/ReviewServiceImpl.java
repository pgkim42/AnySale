package com.example.anysale.review.service;

import com.example.anysale.review.dto.ReviewDTO;
import com.example.anysale.review.dto.ReviewMannerCheckDTO;
import com.example.anysale.review.entity.Review;
import com.example.anysale.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<ReviewDTO> getList() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> list = new ArrayList<>();
        list = reviews.stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public void remove(int no) {

        Optional<Review> findNo = reviewRepository.findById(no);
        if (findNo.isPresent()) {
            reviewRepository.deleteById(no);
        }

    }

    @Override
    public List<ReviewDTO> getReviewIdList(String sellerId) {
        List<Review> list = reviewRepository.findBySellerId(sellerId);
        List<ReviewDTO> listDto = new ArrayList<>();

        for (Review review : list) {
            ReviewDTO dto = entityToDto(review);

            // manner Count 설정
            Map<String, Integer> mannerCounts = getMannerCountBySellerId(sellerId);
            dto.setMannerCounts(mannerCounts);
            //

            listDto.add(dto);

        }
        return listDto;
    }

    @Override
    public List<ReviewDTO> searchReviews(String sellerId) {
        List<Review> list = reviewRepository.findBySellerId(sellerId);
        List<ReviewDTO> listDto = new ArrayList<>();

        for (Review review : list) {
            ReviewDTO dto = entityToDto(review);
            listDto.add(dto);
        }

        return listDto;

    }


    //매너 체크 항목(id별)
    @Override
    public Map<String, Integer> getMannerCountBySellerId(String sellerId) {
        List<Review> reviews = reviewRepository.findBySellerId(sellerId);
        Map<String, Integer> mannerCountMap = new HashMap<>();

        for (Review review : reviews) {
            System.out.println("Review(serviceImpl): " + review);
            for (String manner : review.getMannerCheck()) {
                mannerCountMap.put(manner, mannerCountMap.getOrDefault(manner, 0) + 1);
            }
        }

        return mannerCountMap;
    }
}
