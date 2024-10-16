package com.example.anysale.likeList.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class LikeListDTO {

  private String itemCode;        //상품 코드
  private Long id;                //아이디
  private LocalDateTime regDate;  //등록일

  public LikeListDTO(String itemCode, Long id, LocalDateTime regDate) {
    this.itemCode = itemCode;
    this.id = id;
    this.regDate = regDate;

  }
}
