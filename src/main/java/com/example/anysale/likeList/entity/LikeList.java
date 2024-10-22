package com.example.anysale.likeList.entity;

import com.example.anysale.common.BaseEntity;
import com.example.anysale.member.entity.Member;
import com.example.anysale.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likeList")  // 데이터베이스 테이블 이름을 "likeList"로 매핑
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeList extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; // 고유 ID, 기본키로 자동 증가하는 값

  @ManyToOne
  @JoinColumn(name = "item_code")  // 'Product' 엔티티와 다대일 관계, 외래키로 'item_code'를 명시적으로 지정
  private Product product;  // 찜한 상품과의 연관 관계

  @ManyToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")  // 'Member' 엔티티와 다대일 관계, 외래키로 'member_id'를 명시적으로 지정
  private Member member;  // 찜한 회원과의 연관 관계

  @Column(nullable = false)
  private LocalDateTime wishDate;  // 찜 등록일, 필수 값으로 null 불가

  //test
}
