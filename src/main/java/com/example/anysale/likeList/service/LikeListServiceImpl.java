package com.example.anysale.likeList.service;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.repository.LikeListRepository;
import com.example.anysale.member.entity.Member;
import com.example.anysale.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeListServiceImpl implements LikeListService {

  private final LikeListRepository likeListRepository;

  @Autowired
  public LikeListServiceImpl(LikeListRepository likeListRepository) {
    this.likeListRepository = likeListRepository;
  }

  // 찜 목록에 상품추가
  @Override
  public LikeList addLikeList(LikeList likeList) {
    return likeListRepository.save(likeList);
  }

  // 회원의 찜목록 조회
  @Override
  public List<LikeListDTO> getLikeList(String memberId) {
    List<LikeList> likeLists = likeListRepository.findByMemberId(memberId);
    return likeLists.stream()
        .map(this::entityToDto)  //변환 메서드 사용?
        .collect(Collectors.toList());
  }

  // 찜목록에서 특정상품 제거
  @Override
  public LikeList removeLikeList(int likeListId) {
    Optional<LikeList> likeListOpt = likeListRepository.findById(likeListId);
    if (likeListOpt.isPresent()) {
      likeListRepository.delete(likeListOpt.get());
      return likeListOpt.get();  //삭제한 항목 반환?
    }
    return null;  //항목이 없을 경우 null 반환(예외 처리도 고려)?
  }

  // 찜목록에서 모든 상품 제거
  @Override
  public void removeAllLikeList(String memberId) {
    List<LikeList> likeLists = likeListRepository.findByMemberId(memberId);
    likeLists.forEach(likeListRepository::delete);
  }

  // Entity -> DTO 변환
  public LikeListDTO entityToDto(LikeList likeList) {
    return new LikeListDTO(likeList.getProduct().getItemCode(), likeList.getMember().getId());
  }

  // DTO -> Entity 변환
  public LikeList dtoToEntity(LikeListDTO dto) {

    Product product = new Product();
    product.setItemCode(dto.getItemCode());

    Member member = new Member();
    member.setId(dto.getMemberId());

    LikeList entity = LikeList.builder()
        .product(product)
        .member(member)
        .build();

    return entity;
  }
}
