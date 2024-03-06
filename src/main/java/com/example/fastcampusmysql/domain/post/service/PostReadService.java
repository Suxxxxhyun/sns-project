package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostLikeRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;
    final private PostLikeRepository postLikeRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request){
        /*
            반환 값 -> 리스트 (작성일자, 작성회원, 작성 게시물 갯수)
            select createdDate, memberId, count(id)
            from Post
            where memberId = :memberId and createdDate between firstDate and lastDate
            group by createdDate, memberId
         */
        return postRepository.groupByCreatedDate(request);
    }

    public Page<PostDto> getPosts(Long memberId, Pageable pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest).map(this::toDto);
    }

    private PostDto toDto(Post post){
        return new PostDto(post.getId(), post.getContents(), post.getCreatedAt(), postLikeRepository.countByPostId(post.getId()));
    }

    public Post getPost(Long postId){
        return postRepository.findById(postId, false).orElseThrow();
    }

    //Page가 아닌 PageCursor를 사용한 이유는, 애초에 Page객체를 구현하려면 전체 개수를 파라미터로 받아야하기때문에,
    //커서기반페이징을 위한 별도의 인터페이스인 PageCursor를 만들어주자!
    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
        var posts = findAllBy(memberIds, cursorRequest);
        long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(
                    cursorRequest.key(),
                    memberId,
                    cursorRequest.size()
            );
        }

        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }

    private List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdInAndOrderByIdDesc(
                    cursorRequest.key(),
                    memberIds,
                    cursorRequest.size()
            );
        }

        return postRepository.findAllByMemberIdInAndOrderByIdDesc(memberIds, cursorRequest.size());
    }

    public List<Post> getPosts(List<Long> postIds) {
        return postRepository.findAllByIdIn(postIds);
    }

    //Post게시물 테이블에 가장 작은 id값을 반환, 아예 데이터가 없다면 -1반환
    private long getNextKey(List<Post> posts) {
        return posts.stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }

}
