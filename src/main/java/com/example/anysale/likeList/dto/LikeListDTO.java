package com.example.anysale.likeList.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

//@AllArgsConstructor
public class LikeListDTO {

  private String itemCode;        //상품 코드
  private String memberId;        //아이디
//  private LocalDateTime regDate;  //등록일
//  private LocalDateTime createDate;
//  private LocalDateTime updateDate;

  public LikeListDTO(String itemCode, String memberId) {
    this.itemCode = itemCode;
    this.memberId = memberId;
//    this.regDate = regDate;
  }
//
//  // regDate 없이 사용할 수 있는 생성자 추가
//  public LikeListDTO(String itemCode, String memberId) {
//    this.itemCode = itemCode;
//    this.memberId = memberId;
//  }
}
