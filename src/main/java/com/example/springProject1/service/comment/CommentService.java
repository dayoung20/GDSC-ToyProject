package com.example.springProject1.service.comment;

import com.example.springProject1.domain.comment.Comment;
import com.example.springProject1.domain.comment.CommentRepository;
import com.example.springProject1.domain.posts.Posts;
import com.example.springProject1.domain.posts.PostsRepository;
import com.example.springProject1.domain.user.User;
import com.example.springProject1.domain.user.UserRepository;
import com.example.springProject1.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long commentSave(Long id, CommentRequestDto dto) {

        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }
}
