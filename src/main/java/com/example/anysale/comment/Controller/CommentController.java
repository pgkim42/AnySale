package com.example.anysale.comment.Controller;

import com.example.anysale.comment.dto.CommentDTO;
import com.example.anysale.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody CommentDTO commentDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");    // 세션에서 사용자 ID 값 가져오기
        commentDTO.setUserId(userId);   // 세션 사용자 ID 설정

        CommentDTO savedComment = commentService.createComment(commentDTO);

        // 응답에 성공 여부와 새로 작성된 댓글 데이터를 포함
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("comment", savedComment);

        return ResponseEntity.ok(response);
    }

    // 댓글 ID로 조회
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int id) {
        Optional<CommentDTO> commentDTO = commentService.findCommentById(id);
        return commentDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 모든 댓글 조회
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComment();
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam("commentId") int commentId,
                                @RequestParam("content") String content,
                                @RequestParam("itemCode") String itemCode) {
        CommentDTO commentDTO = commentService.findCommentById(commentId).orElseThrow();
        commentDTO.setContent(content);
        commentService.updateComment(commentDTO);
        return "redirect:/products/detail/" + itemCode;
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam("commentId") int commentId,
                                @RequestParam("itemCode") String itemCode) {
        commentService.deleteComment(commentId);
        return "redirect:/products/detail/" + itemCode;
    }

    // 특정 상품의 댓글 목록 조회
    @GetMapping("/item/{itemCode}")
    @ResponseBody
    public ResponseEntity<List<CommentDTO>> getCommentsByItemCode(@PathVariable String itemCode) {
        List<CommentDTO> comments = commentService.getCommentsByItemCode(itemCode);
        return ResponseEntity.ok(comments);
    }
}

