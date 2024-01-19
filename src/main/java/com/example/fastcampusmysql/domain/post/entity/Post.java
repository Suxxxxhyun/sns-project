package com.example.fastcampusmysql.domain.post.entity;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    final private Long id;
    final private Long memberId;
    final private String contents;
    final private LocalDate createDate;
    final private LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createDate = createDate == null ? LocalDate.now() : createDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
