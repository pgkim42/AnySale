package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;

import java.time.LocalDateTime;
import java.util.List;

public interface LikeListService {

  LikeList addLikeList(LikeList likeList);

  List<LikeListDTO> getLikedItemsByUserId(String id, String itemCode, LocalDateTime wishDate);

  void removeLikeList(String id);

  LikeList removeLikeList(int likeListId);
}
