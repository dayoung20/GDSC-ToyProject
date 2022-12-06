package com.example.springProject1.domain.comment;

import com.example.springProject1.domain.posts.Posts;
import com.example.springProject1.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; //댓글 내용

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Posts posts;

    /*
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

     */

    //git
}
