package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.unit.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;
    @Test
    public void bulkInsert(){
        var easyRandom = PostFixtureFactory.get(
                2L,
              LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1)
        );

        //save가 100만건이라면, 100만건 save메서드가 호출될것이기 때문에,
        //mysql에서 제공하는 bulkinsert를 이용해서 100만건의 쿼리문을 한방에 쏘자!
        //insert쿼리문을 list화한 후, namedParameterJdbcTemplate의 batchUpdate를 이용!
//        var posts = IntStream.range(0, 5)
//                .mapToObj( i -> easyRandom.nextObject(Post.class))
//                .toList();
//
//        postRepository.bulkInsert(posts);

        IntStream.range(0, 5)
                .mapToObj( i -> easyRandom.nextObject(Post.class))
                .forEach(x ->
                        postRepository.save(x)
                );

//        IntStream.range(0, 10)
//                .mapToObj( i -> easyRandom.nextObject(Post.class))
//                .forEach(x ->
//                        System.out.println(x.getMemberId() + "/" + x.getCreatedDate())
//                );
    }
}
