package com.example.fastcampusmysql.unit;

import com.example.fastcampusmysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {
    static public EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate){
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class)); //Post클래스 내의 memberId를 지정

        var param = new EasyRandomParameters()
                .excludeField(idPredicate) //id는 null
                .dateRange(firstDate, lastDate) //시작날짜와 마지막날짜사이의 랜덤값
                .randomize(memberIdPredicate, () -> memberId); //고정된 memberId
        return new EasyRandom(param);

    }
}
