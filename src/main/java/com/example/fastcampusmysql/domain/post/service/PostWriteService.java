package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    final private PostRepository postRepository;

    public Long create(PostCommand command){
        var post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional
    public void likePost(Long postId){
        var post = postRepository.findById(postId, true).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }

    public void likePostByOptimisticLock(Long postId){
        var post = postRepository.findById(postId, false).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
