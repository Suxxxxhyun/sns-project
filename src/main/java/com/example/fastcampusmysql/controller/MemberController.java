package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    final private MemberWriteService memberWriteService;

    @PostMapping("/members")
    public Member register(@RequestBody MemberRegisterCommand command){
        return memberWriteService.create(command);
    }
}
