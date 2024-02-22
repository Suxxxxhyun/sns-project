- Spring Data JPA가 아닌, **NamedParameterJdbcTemplate를 이용하여 JAP가 제공하는 기능을 파악하는데 목표를 두었다.**
- 변경이력과 팔로우 등 **상황별로 쓰기 성능에 초점을 둔 정규화, 읽기 성능에 초점을 둔 비정규화 어느 것이 바람직한가**에 대한 의미를 두었다.

## 주요 기능
- 회원정보 관리
  - [✅] 이메일, 닉네임, 생년월일을 입력받아 저장
    - 닉네임은 10자를 초과불가
  - [✅] 회원정보를 조회
  - [✅] 회원은 닉네임을 변경
    - 닉네임은 10자를 초과불가
  - [✅] 회원의 닉네임 변경이력을 저장 및 조회
  - [✅] 팔로우 등록 및 조회

---
- 내가 쓴 글 캘린더
  - [✅] 작성일자와 회원별로, 회원이 작성한 게시물 갯수를 반환
  - [✅] 회원이 작성한 게시물 offset기반의 페이징처리하여 반환
  - [✅] 회원이 작성한 게시물 Cusor기반의 페이징처리하여 반환

## 위 기능을 구현 시 고민해본 것들
- Service단을 Write와 Read를 각각 분리해서 작성하는 게 더 좋지 않을까?
  

- 100만명의 팔로우를 들고 있는 인플루언서가 있다고 하였을때, Follow엔터티의 nickname필드를 두는 것이 바람직할까?
  - **회원닉네임은 자주 변경이 되는데, 만약 인플루언서가 닉네임을 변경하였다고 한다면, 100만건의 데이터를 모두 변경해야될 것이다.** 이는 관리가 힘들어질 것으로 예상이 되어 **Follow엔터티 내에 nickname필드를 두지 않고 join을 하도록 하여 정규화**가 이루어질 수 있도록 하였다.

- FollowWriteService에서 create()의 파라미터를 MemberDto로 둔다면, 이 파라미터는 누가 넘겨주는게 좋을까?
  - 즉, Follow와 Member, 서로 다른 domain간 데이터를 주고 받는 상황인데, 이를 어디서 처리하는게 좋을까?
  - 이를 위해, application의 usacase계층을 별도로 두어, usacase는 서로 다른 domain간 데이터를 주고받는 계층이 되도록 하였다. 이렇게 하면 결합도를 낮추는데 좋을 것이라고 판단되었다.
  - 이후, usacase계층에서 작성한 것들은 모두, 서로 다른 domain간 데이터를 주고 받는 상황일 경우를 제어하기 위한 것으로 구현하였다. 

### NamedParameterJdbcTemplate 사용법

### 필수 개념
- [Record, Assert, var, Request Body](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log.md)
- [Object Mother Pattern, EasyRandom 라이브러리](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(2).md)
- [인덱스를 사용하는 이유(MySQL기준)](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(3).md)
  - 조건을 만족하는 튜플들을 빠르게 조회하기 위해 인덱스를 사용한다.
- [BTree](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(4).md)
  - B tree는 BST를 일반화한 tree로 자녀노드를 2개 이상 가질 수 있다.
- [인덱스로 BTree계열이 자주 사용되는 이유](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(5).md)
  - B tree index는 self-balancing BST에 비해 secondary storage접근을 적게 한다.
  - B tree 노드는 block단위의 저장공간을 알차게 활용할 수 있다.
- [clustered Index vs non-clustered Index (MYSQL기준)](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(6).md)
- [오프셋 기반 페이지네이션 vs 커서기반 페이지네이션](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log(7).md)
- [pk를 auto increment로 할경우, UUID로 할경우 각각의 장단점]()
