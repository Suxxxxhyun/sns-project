## Object Mother패턴?
- 테스트 데이터 생성에 사용되는 패턴 중 하나로, 
- 테스트에 사용될 객체를 중앙화된 위치에서 생성하고 관리하는 것
- 장점) 반복적인 테스트 데이터 생성로직 중복을 줄이고, 테스트 데이터의 생성과 관리를 일관성 있게 할 수 있다.

- 참조블로그 : https://velog.io/@yeongori/Spring-Boot-Mother-Object-pattern

## EasyRandom 라이브러리?
- 객체의 필드를 따로 지정하지 않고 객체를 자동으로 생성해주는 라이브러리
- 테스트 데이터를 생성하거나 모의데이터를 만들 때 유용하게 사용된다.

- EasyRandom은 시드값을 이용하여 랜덤값을 생성하는데, 이때 시드값은 랜덤값 생성의 기준점이라고 할 수 있다.
- 동일한 시드값을 가지고 랜덤 값을 생성하면 항상 동일한 결과가 반환된다.
- EasyRandom에서는 `seed(Long seed)`메소드를 통해 시드값을 지정할 수 있다.

- 참조블로그 : https://berom.tistory.com/293
