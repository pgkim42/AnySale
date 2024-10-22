package com.example.anysale.likeList.repository;

import com.example.anysale.likeList.entity.LikeList;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Integer> {

  // 특정 회원의 찜 목록 조회
  List<LikeList> findByMemberId(String memberId);

  // 특정 회원이 찜한 특정 상품 삭제
  void deleteByProduct_ItemCodeAndMember_Id(String itemCode, String memberId);

  // 특정회원의 찜목록 삭제
  void deleteByMemberId(String itemCode);

}