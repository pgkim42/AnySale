package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;

import java.time.LocalDateTime;
import java.util.List;

public interface LikeListService {

  // 찜목록에 새로운 항목을 추가하는 메서드
  // LikeList객체를 받아서 추가하고 추가된 찜목록 항목을 반환
  LikeList addLikeList(LikeList likeList);

  // 특정 사용자의 찜목록을 조회하는 메서드
  // memberId, itemCode, wistDate를 기준으로 likeListDTO 목록을 반환
  List<LikeListDTO> getLikedItemsByUserId(String id, String itemCode, LocalDateTime wishDate);

  // 사용자ID를 기준으로 해당 사용자의 모든 찜목록을 제거하는 메서드
  void removeLikeList(String id);

  // 찜 목록의 고유ID(likeListId)를 기준으로 찜목록에서 항목을 제거하는 메소드
  // 제거된 LikeList 항목을 반환
  LikeList removeLikeList(int likeListId);
}
