package com.example.anysale.likeList.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LikeListDTO {

  private String itemCode;          //상품 코드, 찜한 상품의 고유 코드
  private String memberId;          //멤버아이디, 찜한 회원의 ID
  private LocalDateTime wishDate;   //찜 등록일, 해당 상품이 찜된 날짜 및 시간

  // 모든 필드를 초기화하는 생성자
  public LikeListDTO(String itemCode, String memberId, LocalDateTime wishDate) {
    this.itemCode = itemCode; //상품 코드 설정
    this.memberId = memberId; //멤버 아이디 설정
    this.wishDate = wishDate; //찜 등록일 설정

  }
}