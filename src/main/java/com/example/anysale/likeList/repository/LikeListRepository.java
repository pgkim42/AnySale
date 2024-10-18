package com.example.anysale.likeList.repository;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Integer> {

  @Query("SELECT new com.example.anysale.likeList.dto.LikeListDTO(pro.itemCode, mem.id) " +
      "FROM LikeList l " +
      "JOIN l.product pro " +
      "JOIN l.member mem " +
      "WHERE mem.id = :userId AND pro.itemCode = :itemCode")
  List<LikeListDTO> findLikedItemsByUserId(@Param("userId") String userId, @Param("itemCode") String itemCode);
}
