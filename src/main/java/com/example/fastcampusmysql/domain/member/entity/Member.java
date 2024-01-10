package com.example.fastcampusmysql.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Builder
public class Member {

    final private Long id;
    private String nickname;
    final private String email;
    final private LocalDate birthday;
    final private LocalDateTime createdAt;
    final private static Long NAME_MAX_LENGTH = 10L;

    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public void changeNickname(String to){
        Objects.requireNonNull(to);
        validateNickname(to);
        nickname = to;
    }

    private void validateNickname(String nickname){
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
