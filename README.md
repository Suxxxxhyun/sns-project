### 주요 기능

- Spring Data JPA가 아닌, NamedParameterJdbcTemplate를 이용하여 JAP가 제공하는 기능을 파악
- 회원정보 관리
  - [✅] 이메일, 닉네임, 생년월일을 입력받아 저장
    - 닉네임은 10자를 초과불가
  - [✅] 회원정보를 조회
  - [✅] 회원은 닉네임을 변경
    - 닉네임은 10자를 초과불가
  - [✅] 회원의 닉네임 변경이력을 저장 및 조회
  - [✅] 팔로우 등록 및 조회
    - 팔로우는 데이터의 최신성을 보장
    - 팔로잉
    - 팔로워 

### 위 기능을 구현 시 고려한 사항
- domain간 결합도를 어떻게 낮출 것인가?
- 서로 다른 domain간 데이터를 주고받을 때 어디서 처리해야하는가?

### 학습 로그 

- [Record, Assert, var, Request Body](https://github.com/Suxxxxhyun/sns-project/blob/main/learning-log/learning-log.md)
