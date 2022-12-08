package com.example.springProject1.web;

import com.example.springProject1.service.posts.PostsService;
import com.example.springProject1.web.dto.PostsResponseDto;
import com.example.springProject1.web.dto.PostsSaveRequestDto;
import com.example.springProject1.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


//이전

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestPart(value = "key") PostsSaveRequestDto requestDto,
                     @RequestPart(value = "file") MultipartFile file) throws Exception{
        return postsService.save(requestDto, file);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public  Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }


}
