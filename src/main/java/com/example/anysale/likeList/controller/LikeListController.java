package com.example.anysale.likeList.controller;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.service.LikeListService;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.service.MemberService;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likeList")
public class LikeListController {

  private final LikeListService likeListService;
  private final ProductService productService;  // ProductService 주입
  private final MemberService memberService;    // MemberService 주입

  @Autowired
  public LikeListController(LikeListService likeListService, ProductService productService, MemberService memberService) {
    this.likeListService = likeListService;
    this.productService = productService;
    this.memberService = memberService;
  }

  // 찜 목록에 상품 추가
  @PostMapping
  public ResponseEntity<LikeList> addLikeList(@RequestBody LikeListDTO likeListDto) {
    // Product와 Member 객체 생성
    Product product = new Product();
    product.setItemCode(likeListDto.getItemCode());

    Member member = new Member();
    member.setId(likeListDto.getMemberId());

    LikeList likeList = LikeList.builder()
        .product(product)
        .member(member)
        .build();

    LikeList addedLikeList = likeListService.addLikeList(likeList);
    return new ResponseEntity<>(addedLikeList, HttpStatus.CREATED);
  }

  // 회원의 찜목록 조회
  @GetMapping("/list")
  public List<LikeListDTO> getLikeLists(@RequestParam String memberId) {
    return likeListService.getLikeList(memberId);
  }

  // 찜 목록에서 특정 상품 제거
  @DeleteMapping("/{likeListId}")
  public ResponseEntity<Void> removeLikeList(@PathVariable int likeListId) {
    LikeList removedLikeList = likeListService.removeLikeList(likeListId);
    if (removedLikeList != null) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // 찜 목록에서 모든 상품 제거
  @DeleteMapping("/all/{memberId}")
  public ResponseEntity<Void> removeAllLikeLists(@PathVariable String memberId) {
    likeListService.removeAllLikeList(memberId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
