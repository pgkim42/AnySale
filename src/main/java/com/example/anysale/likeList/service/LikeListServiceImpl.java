package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeListEntity;
import com.example.anysale.likeList.repository.LikeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public LikeListEntity addLikeList(LikeListEntity likeListEntity) {
    return likeListRepository.save(likeListEntity);
  }


  @Override
  public List<LikeListDTO> getLikedItemsByUserId(String id, String itemCode) {
    return likeListRepository.findLikedItemsByUserId(id, itemCode);
  }

  @Override
  public void removeLikeList(String id) {

  }

  @Override
  public LikeListEntity removeLikeList(Long likeListId) {
    Optional<LikeListEntity> likeListEntity = likeListRepository.findById(likeListId);
    likeListEntity.ifPresent(likeListRepository::delete);
    return null;
  }
}
