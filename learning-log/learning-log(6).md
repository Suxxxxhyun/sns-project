## clustered Index vs non-clustered Index (MYSQL기준)
 
### clustered Index
- 특정 나열된 데이터들을 일정 기준으로 정렬해주는 인덱스이다. (ex: 영어사전) 그래서 클러스터형 인덱스 생성 시에는 데이터 페이지 전체가 다시 정렬된다.
- 하지만, 이러한 정렬 특징 때문에, 이미 대용량의 데이터가 입력된 상태라면 클러스터형 인덱스 생성은 심각한 시스템 부하를 줄 수 있다.
- **한개의 테이블에 한개씩만 만들 수 있다. (ex: pk)**
- 본래 인덱스는 생성 시, 데이터들의 배열정보를 따로 저장하는 공간을 사용하나, 클러스터 인덱스는 따로 저장하는 정보 공간을 적게 사용하면서 테이블 공간 자체를 활용한다.
- 인덱스 자체의 리프 페이지가 곧 Data이기 때문에, 인덱스 자체에 데이터가 포함되어있다고 볼 수 있다.
- **Data Page의 데이터들이 순차적으로 정렬되어있다.**
- 보조 인덱스(non-clustered Index)보다 검색 속도는 더 빠르다.
- 하지만 CUD는 보조 인덱스에 비해 더 느리다.
- **Mysql에서는 pk가 있다면 pk를 Clustered index로, pk가 없다면 UNIQUE하면서 NOT NULL인 컬럼을 Clustered index로**, 
- **이것조차 없다면 임의로 보이지 않는 컬럼을 만들어 Clustered index로 지정한다.**

### non-clustered Index (보조 인덱스)
- 개념적으로 후보키에만 부여할 수 있는 인덱스이다. (후보키 : 고유식별번호, 주민번호와 같이 데이터를 인식할 수 있는 최소한의 고유 식별 속성 집합)
- **보조 인덱스 생성시에는 데이터 페이지는 그냥 둔 상태에서(즉, 정렬하지 않은 상태에서) 별도의 페이지에 인덱스를 구성한다.**
- **Data Page의 데이터들이 순차적으로 정렬되어있지 않다.**
- 보조 인덱스의 리프 페이지에는 데이터가 아니라 데이터가 위치하는 주솟값이 저장된다.
- 클러스터형보다 검색 속도는 느리지만, 데이터의 CUD는 덜 느리다.
- **테이블당 여러개 생성할 수 있다. 그러나 함부로 사용할 경우 오히려 성능을 떨어뜨릴 수 있다.**

- 참조 블로그
  - https://pangtrue.tistory.com/286
  - https://inpa.tistory.com/entry/MYSQL-%F0%9F%93%9A-%EC%9D%B8%EB%8D%B1%EC%8A%A4index-%ED%95%B5%EC%8B%AC-%EC%84%A4%EA%B3%84-%EC%82%AC%EC%9A%A9-%EB%AC%B8%EB%B2%95-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC
  - https://gwang920.github.io/database/clusterednonclustered/

