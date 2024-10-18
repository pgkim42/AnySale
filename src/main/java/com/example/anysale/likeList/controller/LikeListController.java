/*
package com.example.anysale.likeList.controller;

import com.example.anysale.likeList.dto.LikeListDTO;
import com.example.anysale.likeList.entity.LikeList;
import com.example.anysale.likeList.service.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeListController {

  private final LikeListService likeListService;

  @Autowired
  public LikeListController(LikeListService likeListService) {
    this.likeListService = likeListService;
  }

  @GetMapping("/likeList")
  public String getLikeList(Model model) {
    String userId = "userId";
    String itemCode = "itemCode";
    LocalDateTime wishDate = LocalDateTime.now();
    model.addAttribute("likeList", likeListService.getLikedItemsByUserId(userId, itemCode, wishDate));
    return "likeList";
  }

  @GetMapping("/member/{id}")
  public List<LikeListDTO> getLikedItemsByUserId(@PathVariable String id, String itemCode, LocalDateTime wishDate) {
    return likeListService.getLikedItemsByUserId(id, itemCode, wishDate);
  }

  @PatchMapping("/add")
  public LikeList addLikedItem(@RequestBody LikeList likeList) {
    return likeListService.addLikeList(likeList);
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<LikeList> removeLikedItem(@PathVariable int id) {
    LikeList removedItem = likeListService.removeLikeList(id);
    return ResponseEntity.ok(removedItem);
  }
}
*/
