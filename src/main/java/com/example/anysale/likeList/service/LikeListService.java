package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeListEntity;

import java.util.List;

public interface LikeListService {

  LikeListEntity addLikeList(LikeListEntity likeListEntity);

  List<LikeListDTO> getLikedItemsByUserId(String id, String itemCode);

  void removeLikeList(String id);

  LikeListEntity removeLikeList(Long likeListId);
}
