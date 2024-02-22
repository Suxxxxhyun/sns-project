package com.example.fastcampusmysql.util;

public record CursorRequest(Long key, int size) {
    public static final Long NONE_KEY = -1L; //더이상 데이터가 없음을 표시하기 위해 -1로 default지정
    //데이터가 더이상 없는경우, 클라이언트가 무한스크롤 요청을 더이상 하면 안되기때문에 이를 구별하기 위한 키라고 생각하면 됨!

    //키가 있다면, findAllByLessThanIdAndMemberIdAndOrderByIdDesc호출,
    //키가 없다면, findAllByMemberIdAndOrderByIdDesc호출하도록
    //키가 있냐 없냐를 묻는 메소드 (hasKey)
    public Boolean hasKey() {
        return key != null;
    }

    //클라이언트가 최초로 요청한 이후에는, 클라이언트가 키를 쥐고 있기때문에 아래와 같은 메소드를 구현할 수 있음.
    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}
