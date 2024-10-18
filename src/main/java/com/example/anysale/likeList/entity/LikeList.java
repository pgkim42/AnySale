package com.example.anysale.likeList.entity;

import com.example.anysale.common.BaseEntity;
import com.example.anysale.member.entity.Member;
import com.example.anysale.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likeList")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeList extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;  //고유ID

  @ManyToOne
//  @JoinColumn(name = "product_id", referencedColumnName = "item_code")
  private Product product;  //상품코드

  @ManyToOne
//  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

//  @Column(nullable = false)
//  private LocalDateTime regDate;  // 등록일

}
