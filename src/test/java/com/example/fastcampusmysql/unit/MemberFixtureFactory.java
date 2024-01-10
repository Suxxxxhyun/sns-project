package com.example.fastcampusmysql.unit;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {

    static public Member create(){
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }

    /*
    new EasyRandom(parm) : EasyRandom클래스를 인스턴스화하고,
    nextObject메소드를 호출하여 member인스턴스를 랜덤하게 생성
     */
    static public Member create(Long seed){
        var parm = new EasyRandomParameters().seed(seed);
        return new EasyRandom(parm).nextObject(Member.class);
    }
}
