package com.example.anysale.likeList.repository;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*public interface LikeListRepository extends JpaRepository<LikeList, Integer> {

  @Query("SELECT new com.example.anysale.likeList.dto.LikeListDTO(pro.itemCode, mem.id) " +
      "FROM LikeList l " +
      "JOIN l.product pro " +
      "JOIN l.member mem " +
      "WHERE mem.id = :userId AND pro.itemCode = :itemCode")
  List<LikeListDTO> findLikedItemsByUserId(@Param("userId") String userId, @Param("itemCode") String itemCode);
}*/
public interface LikeListRepository extends JpaRepository<LikeList, Integer> {

  // 특정 회원의 찜 목록 조회
  List<LikeList> findByMemberId(String memberId);

  // 특정 삼품을 찜한 사용자 조회
  List<LikeList> findByProduct_ItemCode(String itemCode);

  // 특정 회원이 찜한 특정 상품 조회(존재여부를 체크)
  LikeList findByProduct_ItemCodeAndMember_Id(String itemCode, String memberId);

  // 찜 목록에서 상품 삭제
  void deleteByProduct_ItemCodeAndMember_Id(String itemCode, String memberId);

}