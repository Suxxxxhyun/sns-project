package com.example.fastcampusmysql.util;

import java.util.List;

public record PageCursor<T>(
        //클라이언트가 요청할 다음 키(cursor)
        CursorRequest nextCursorRequest,
        List<T> body
) {
}
