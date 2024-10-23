package com.example.anysale.likeList.controller;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.service.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likeList")
public class LikeListController {

  private LikeListService likeListService;

  @Autowired
  public LikeListController(LikeListService likeListService) {
    this.likeListService = likeListService;
  }

  // 찜목록 추가
  @PostMapping
  public LikeList addLikeList(@RequestBody LikeList likeList) {
    return likeListService.addLikeList(likeList);
  }

  // 회원의 찜목록 조회
  @GetMapping("/{memberId}")
  public List<LikeListDTO> getLikeList(@PathVariable String memberId) {
    return likeListService.getLikeList(memberId);
  }

  // 찜 목록에서 특정 상품 제거
  @DeleteMapping("/{likeListId}")
  public LikeList removeLikeList(@PathVariable int likeListId) {
    return likeListService.removeLikeList(likeListId);
  }

  // 찜 목록에서 모든 상품 제거
  @DeleteMapping("/all/{memberId}")
  public void removeAllLikeList(@PathVariable String memberId) {
    likeListService.removeAllLikeList(memberId);
  }

}
