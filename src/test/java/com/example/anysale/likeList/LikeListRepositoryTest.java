package com.example.anysale.likeList;

import com.example.anysale.likeList.repository.LikeListRepository;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import com.example.anysale.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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




}