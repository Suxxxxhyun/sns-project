package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {

    final private FollowReadService followReadService;
    final private PostReadService postReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        /*
            1. memberId의 follow조회
            2. 1번 게시물 조회
         */
        var followings = followReadService.getFollowings(memberId);
        var followerMemberIds = followings
                .stream()
                .map(Follow::getToMemberId)
                .toList();

        return postReadService.getPosts(followerMemberIds, cursorRequest);
    }
}
