package com.example.springProject1.service.posts;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.springProject1.domain.posts.Posts;
import com.example.springProject1.domain.posts.PostsRepository;
import com.example.springProject1.web.dto.PostsListResponseDto;
import com.example.springProject1.web.dto.PostsResponseDto;
import com.example.springProject1.web.dto.PostsSaveRequestDto;
import com.example.springProject1.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String new_fileName = uuid + "_" + file.getOriginalFilename();
        File new_file = new File(projectPath, new_fileName);
        file.transferTo(new_file); //예외처리 필수

        requestDto.setFileName(new_fileName);
        requestDto.setFilePath("/files/" + new_fileName);

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }


}
