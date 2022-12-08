package com.example.springProject1.web.dto;

import com.example.springProject1.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class PostsSaveRequestDto{
    private String title;
    private String content;
    private String author;
    private String userId;
    private String fileName;
    private String filePath;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String fileName, String filePath){
        this.title = title;
        this.content = content;
        this.author = author;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .fileName(fileName)
                .filePath(filePath)
                .build();
    }
}
