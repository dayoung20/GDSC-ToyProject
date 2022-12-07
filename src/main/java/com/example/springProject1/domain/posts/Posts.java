package com.example.springProject1.domain.posts;

import com.example.springProject1.domain.BaseTimeEntity;
import com.example.springProject1.domain.comment.Comment;
import com.example.springProject1.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //추가
    @Column(columnDefinition = "integer default 0")
    private int view;


    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")//댓글 정렬
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Comment> comments;


    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        //this.user = user;
    }
    public void update(String title, String content){ //게시글 수정 메소드
        this.title =  title;
        this.content = content;
    }

}
