package com.example.anysale.likeList.service;


import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;

import java.util.List;

public interface LikeListService {

  // 찜 목록에 상품 추가
  LikeList addLikeList(LikeList likeList);

  // 회원의 찜 목록 조회
  List<LikeListDTO> getLikeList(String memberId);

  // 찜 목록에서 특정 상품 제거
  LikeList removeLikeList(int likeListId);

  // 찜 목록에서 모든 상품 제거
  void removeAllLikeList(String memberId);

}

