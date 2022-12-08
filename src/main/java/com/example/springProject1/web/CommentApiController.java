package com.example.springProject1.web;

import com.example.springProject1.config.auth.LoginUser;
import com.example.springProject1.config.auth.dto.SessionUser;
import com.example.springProject1.service.comment.CommentService;
import com.example.springProject1.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/v1/posts/detail/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.commentSave(id, dto));
    }

}
