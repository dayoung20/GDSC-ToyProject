package com.example.springProject1.web.dto;

import com.example.springProject1.domain.comment.Comment;
import com.example.springProject1.domain.posts.Posts;
import com.example.springProject1.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CommentRequestDto {

    private Long id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Posts posts;

    public Comment toEntity() {
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .posts(posts)
                .build();

        return comments;
    }
}