package com.example.anysale.likeList.controller;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.service.LikeListService;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.service.MemberService;
import com.example.anysale.product.dto.ProductDTO;
import com.example.anysale.product.entity.Product;
import com.example.anysale.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likeList")
public class LikeListController {

  @Autowired
  private ProductService productService;

  @Autowired
  private LikeListService likeListService;

  @Autowired
  private MemberService memberService;

  // 상품 세부 정보 조회
  @GetMapping("/{id}")
  public String getProductDetails(@PathVariable String id, Model model) {
    ProductDTO product = productService.getProductById(id).orElse(null);

    if (product != null) {
      model.addAttribute("title", product.getTitle());  // title 값을 Model에 추가
    } else {
      model.addAttribute("error", "상품을 찾을 수 없습니다.");
    }

    return "like/likeList";  // 해당하는 HTML 페이지를 반환
  }


//  // 찜하기 버튼 클릭 처리
//  @PostMapping("/add")
//  public ResponseEntity<LikeList> addLikeList(@RequestParam String itemCode, @RequestParam String memberId) {
//    ProductDTO product = productService.getProductById(itemCode).orElse(null);
//    Member member = memberService.findById(memberId).orElse(null);
//
//    if (product == null || member == null) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 상품이나 회원이 없으면 404
//    }
//
//    LikeList likeList = LikeList.builder()
//        .product(product)
//        .member(member)
//        .build();
//
//    LikeList addedLikeList = likeListService.addLikeList(likeList);
//    return new ResponseEntity<>(addedLikeList, HttpStatus.CREATED);
//  }

  // 회원의 찜목록 조회
  @GetMapping("/list")
  public List<LikeListDTO> getLikeLists(@RequestParam(value = "memberId") String memberId, Model model) {
    // memberId로 찜 목록을 조회하는 로직 추가 필요
     List<LikeListDTO> likeLists = likeListService.getLikeList(memberId);
    // model.addAttribute("likeLists", likeLists);

    return likeLists;
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