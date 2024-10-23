package com.example.anysale.likeList;

import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.repository.LikeListRepository;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class LikeListRepositoryTest2 {

  @Autowired
  LikeListRepository likeListRepository;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  ProductRepository productRepository;

  @Test
  void member등록() {
    Member member = Member.builder()
        .id("user1")
        .password("securePassword")
        .name("John Doe")
        .email("john1.doe@example.com")
        .phone("123-456-7890")
        .profilePhotoUrl("http://example.com/photo.jpg")
        .role("USER1")
        .score(0.0)
        .build();

    memberRepository.save(member);
  }

//  @Test
//  void product등록() {
//    Product product = Product.builder()
//        .itemCode("ITEM2024")
//        .price("20000")
//        .category("Electronics")
//        .content("Latest smartphone with advanced features.")
//        .productCondition("New")
//        .imageUrl("http://example.com/image.jpg")
//        .dealDate(LocalDateTime.now())
//        .modDate(LocalDateTime.now())
//        .status("Available")
//        .location("Seoul")
//        .userId("user1")
//        .build();
//
//    productRepository.save(product);
//  }

  @Test
  void likelist등록() {

    Member member = Member.builder()
        .id("user1")
        .build();

    Product product = Product.builder()
        .itemCode("ITEM2024")
        .build();

    LikeList likeList = LikeList.builder()
        .member(member)
        .product(product)
        .build();

    likeListRepository.save(likeList);
  }

}
