package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.repository.LikeListRepository;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeListServiceImpl implements LikeListService {

  private final LikeListRepository likeListRepository;
  private final ProductRepository productRepository;

  @Autowired
  public LikeListServiceImpl(LikeListRepository likeListRepository, ProductRepository productRepository) {
    this.likeListRepository = likeListRepository;
    this.productRepository = productRepository;
  }

  // 찜 목록에 상품 추가
  @Override
  public LikeList addLikeList(LikeList likeList) {
    return likeListRepository.save(likeList);
  }


  // 회원의 찜목록 조회
  @Override
  public List<LikeListDTO> getLikeList(String memberId) {
    List<LikeList> likeLists = likeListRepository.findByMemberId(memberId);
    return likeLists.stream()
        .map(likeList -> new LikeListDTO(likeList.getProduct().getItemCode(), likeList.getMember().getId()))
        .collect(Collectors.toList());
  }

  // 찜목록에서 특정상품 제거
  @Override
  public LikeList removeLikeList(int likeListId) {
    LikeList likeList = likeListRepository.findById(likeListId).orElse(null);
    if (likeList != null) {
      likeListRepository.delete(likeList);
    }
    return likeList;
  }

  // 찜목록에서 모든 상품 제거
  @Override
  public void removeAllLikeList(String memberId) {
    List<LikeList> likeLists = likeListRepository.findByMemberId(memberId);
    likeLists.forEach(likeListRepository::delete);
  }
}
