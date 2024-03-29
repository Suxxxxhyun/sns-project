package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usacase.CreatePostLikeUsecase;
import com.example.fastcampusmysql.application.usacase.CreatePostUsecase;
import com.example.fastcampusmysql.application.usacase.GetTimelinePostsUsecase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.entity.PostLike;
import com.example.fastcampusmysql.domain.post.service.PostLikeWriteService;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsecase getTimelinePostsUsecase;
    final private CreatePostUsecase createPostUsecase;
    final private CreatePostLikeUsecase createPostLikeUsecase;

    @PostMapping("")
    public Long create(PostCommand command){
//        return postWriteService.create(command);
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request){
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCoursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
//        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    // 낙관적 락을 이용하여 좋아요 수 증가
    @PostMapping("/{postId}/like/v1")
    public void likePost(@PathVariable Long postId){
//        postWriteService.likePost(postId);  // 비관적 락을 이용해서 좋아요 수 증가
        postWriteService.likePostByOptimisticLock(postId);
    }

    // 좋아요 집계 테이블을 분리하여 좋아요 수 증가
    @PostMapping("/{postId}/like/v2")
    public void likePost(@PathVariable Long postId, @RequestParam Long memberId){
        createPostLikeUsecase.execute(postId, memberId);
    }

}
