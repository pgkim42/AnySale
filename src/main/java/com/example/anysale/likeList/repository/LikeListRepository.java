package com.example.anysale.likeList.repository;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeListEntity, Long> {

  @Query("SELECT LikeListDTO(pro.itemCode, mem.id) " +
      "FROM LikeListEntity l " +
      "JOIN l.product pro " +
      "JOIN l.member mem " +
      "WHERE l.member.id = :userId and l.product.itemCode = :itemCode")
  List<LikeListDTO> findLikedItemsByUserId(@Param("userId") String userId, @Param("itemCode")String itemCode);
}
