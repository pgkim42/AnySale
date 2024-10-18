/*
package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.repository.LikeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikeListServiceImpl implements LikeListService {

  private final LikeListRepository likeListRepository;

  @Autowired
  public LikeListServiceImpl(LikeListRepository likeListRepository) {
    this.likeListRepository = likeListRepository;
  }

  @Override
  public LikeList addLikeList(LikeList likeList) {
    return likeListRepository.save(likeList);
  }


  @Override
  public List<LikeListDTO> getLikedItemsByUserId(String id, String itemCode, LocalDateTime wishDate) {
    return likeListRepository.findLikedItemsByUserId(id, itemCode, wishDate);
  }

  @Override
  public void removeLikeList(String id) {

  }

  @Override
  public LikeList removeLikeList(int likeListId) {
    Optional<LikeList> likeListEntity = likeListRepository.findById(likeListId);
    likeListEntity.ifPresent(likeListRepository::delete);
    return null;
  }
}
*/
