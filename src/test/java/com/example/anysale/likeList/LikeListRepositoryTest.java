package com.example.anysale.likeList;

import com.example.anysale.likeList.entity.LikeListEntity;
import com.example.anysale.likeList.repository.LikeListRepository;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class LikeListRepositoryTest {

  @Autowired
  private LikeListRepository likeListRepository;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  ProductRepository productRepository;

  @Test
  void 등록() {
    Member member = Member.builder()
        .id("memberId2")
        .password("securePassword")
        .name("John Doe")
        .email("john.doe@example.com")
        .phone("123-456-7890")
        .profilePhotoUrl("http://example.com/photo.jpg")
        .role("USER")
        .score(0.0)
        .build();
    memberRepository.save(member);

    Product product = Product.builder()
        .itemCode("ITEM1223")
        .price("10000")
        .category("Electronics")
        .content("Latest smartphone with advanced features.")
        .productCondition("New")
        .imageUrl("http://example.com/image.jpg")
        .regDate(LocalDateTime.now())
        .modDate(LocalDateTime.now())
        .location("Seoul")
        .status("Available")
        .userId("user123")
        .build();
    productRepository.save(product);

    LikeListEntity likeListEntity = LikeListEntity.builder().member(member).product(product).regDate(LocalDateTime.now()).build();
    likeListRepository.save(likeListEntity);

  }

}
