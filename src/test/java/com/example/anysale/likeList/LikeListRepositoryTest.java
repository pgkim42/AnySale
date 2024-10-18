package com.example.anysale.likeList;

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
public class LikeListRepositoryTest {

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
        .password("1234")
        .name("John")
        .email("john11@naver.com")
        .phone("010-0000-0001")
        .profilePhotoUrl("http://example.com/photo.jpg")
        .role("user")
        .score(10.0)
        .build();

    Member member2 = Member.builder()
        .id("user2")
        .password("1234")
        .name("Sophia")
        .email("Sophia22@naver.com")
        .phone("010-0000-0002")
        .profilePhotoUrl("http://example.com/photo.jpg")
        .role("user")
        .score(20.0)
        .build();

    Member member3 = Member.builder()
        .id("user3")
        .password("1234")
        .name("Oliver")
        .email("Oliver33@naver.com")
        .phone("010-0000-0003")
        .profilePhotoUrl("http://example.com/photo.jpg")
        .role("ADMIN")
        .score(30.0)
        .build();

    memberRepository.save(member);
    memberRepository.save(member2);
    memberRepository.save(member3);
  }


  @Test
  void product등록() {
    Product product = Product.builder()
        .itemCode("지니의 요술램프(1번남음..)")
        .price("100000")
        .category("골동품")
        .content("살살문질러서 외부에 사용감도 없어요! 네고x (주의사항! 지니 생각보다 크니깐 집에서 부르지마세요!!)")
        .productCondition("새거같은헌거")
        .imageUrl("http://example.com/photo.jpg")
        .modDate(LocalDateTime.now())
        .dealDate(LocalDateTime.now())
        .status("판매중")
        .location("인천광역시 구월동 124-12")
        .userId("user1")
        .build();

    Product product2 = Product.builder()
        .itemCode("프리미엄 주환이")
        .price("5000")
        .category("탈것")
        .content("속도 장난아님. 승차감 좋아요!")
        .productCondition("중고")
        .imageUrl("http://example.com/photo.jpg")
        .modDate(LocalDateTime.now())
        .dealDate(LocalDateTime.now())
        .status("거래중")
        .location("인천광역시 구월동 546-75")
        .userId("user2")
        .build();

    Product product3 = Product.builder()
        .itemCode("지니의 요술램프(1번남음..)")
        .price("100000")
        .category("골동품")
        .content("살살문질러서 외부에 사용감도 없어요! 네고x (주의사항! 지니 생각보다 크니깐 집에서 부르지마세요!!)")
        .productCondition("새거같은헌거")
        .imageUrl("http://example.com/photo.jpg")
        .modDate(LocalDateTime.now())
        .dealDate(LocalDateTime.now())
        .status("판매중")
        .location("인천광역시 구월동 124-12")
        .userId("user1")
        .build();

    productRepository.save(product);
    productRepository.save(product2);
    productRepository.save(product3);
  }



}