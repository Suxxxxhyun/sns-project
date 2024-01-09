### Record란?
- Java 14부터 도입된 클래스의 일종
- 불변 데이터를 표현하는데 사용
- 클래스이므로, interface구현 및 extends확장이 가능
- 생성자, getter, equals(), hashCode()를 컴파일러가 자동생성
- **불변이므로, setter는 생성되지 않는다.**

참조블로그 : https://ddol.tistory.com/61

---

### Assert?

- Spring Assert는 인수를 검증하고 조건에 맞지 않는 경우, IllegalArgumentException 또는 IllegalStateException발생
- 조건문을 단순화하고 반복적인 코드를 줄이는 역할을 함.
- Assert를 사용하지 않은 경우

```jsx
if(user == null){
	throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
}
```
- Assert를 사용한 경우

```jsx
Assert.notEmpty(user, "사용자 정보가 존재하지 않습니다.");
```

참조블로그 : https://velog.io/@mingsound21/Spring-Assert

---
### var?
- Java10부터 지역변수에 대한 **Type Inference**가 가능한 var키워드를 제공한다.
- **Type Inference**는 제네릭이나 람다식에서도 찾아볼 수 있는데, 타입을 생략하여도 이를 문맥상 충분히 알 수 있으면 컴파일러가 타입을 추론해주는 것을 말합니다.

참조블로그 : [https://kjhoon0330.tistory.com/entry/Java-var-키워드에-대하여](https://kjhoon0330.tistory.com/entry/Java-var-%ED%82%A4%EC%9B%8C%EB%93%9C%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC)

---
### RequestBody? vs ResponseBody
- 클라이언트 → 서버 : `@RequestBody`
- 서버 → 클라이언트 : `@ResponseBody`
- `@RequestBody` - json기반의 HTTP Body를 자바객체로 반환
- `@ResponseBody` - 자바객체를 json기반의 HTTP Body로 반환
  - RestController를 사용하는 경우, 반환값에 자동으로 `@ResponseBody` 어노테이션이 붙게된다.

참조블로그 : [https://velog.io/@nomonday/Spring-RequestBody-ResponseBody-이해하기](https://velog.io/@nomonday/Spring-RequestBody-ResponseBody-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)
