package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

public record MemberRegisterCommand(
        String email,
        String nickname,
        LocalDate birthday) {
}
