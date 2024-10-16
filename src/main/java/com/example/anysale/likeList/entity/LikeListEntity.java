package com.example.anysale.likeList.entity;

import com.example.anysale.common.BaseEntity;
import com.example.anysale.member.entity.Member;
import com.example.anysale.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_likeList")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeListEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;  //고유ID

  @ManyToOne
  @JoinColumn(name = "product", referencedColumnName = "item_code")
  private Product product;  //상품코드

  @ManyToOne
  @JoinColumn(name = "member", referencedColumnName = "id")
  private Member member;

  private LocalDateTime regDate;


}
