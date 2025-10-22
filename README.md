# 자동차 경주

---

## 기능 요구 사항

---
초간단 자동차 경주 게임을 구현한다.

- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
- 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능하다.
- 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.
- 우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분한다.
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.


### 입출력 요구 사항

**입력**
- 경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)
```java
pobi,woni,jun
```
- 시도할 횟수
```java
5
```

**출력**
- 차수별 실행 결과
```java
pobi : --
woni : ----
jun : ---
```
- 단독 우승자 안내 문구
```java
최종 우승자 : pobi
```
- 공동 우승자 안내 문구
```java
최종 우승자 : pobi, jun
```

**실행 결과 예시**
```java
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,woni,jun
시도할 횟수는 몇 회인가요?
5

실행 결과
pobi : -
woni : 
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 우승자 : pobi, jun
```

## 프로그래밍 요구 사항1

---
- JDK 21 버전에서 실행 가능해야 한다.
- 프로그램 실행의 시작점은 Application의 main()이다.
- `build.gradle` 파일은 변경할 수 없으며, 제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.
- 프로그램 종료 시 System.exit()를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - 기본적으로 Java Style Guide를 원칙으로 한다.

## 프로그래밍 요구 사항2

---
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
  - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인한다.
  - 테스트 도구 사용법이 익숙하지 않다면 아래 문서를 참고하여 학습한 후 테스트를 구현한다.
    - JUnit 5 User Guide
    - AssertJ User Guide
    - AssertJ Exception Assertions
    - Guide to JUnit 5 Parameterized Tests

### 라이브러리
- `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현해야 한다.
  - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange()`를 활용한다.
  - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

**사용 예시**
- 0에서 9까지의 정수 중 한 개의 정수 반환
```java
Randoms.pickNumberInRange(0, 9);
```

## 기능 목록

### 패키지 구조
```java
racingcar
├─ application
│   └─ GameRunner.java           // 애플리케이션 흐름 오케스트레이션
├─ domain
│   ├─ Car.java                  // 자동차 엔티티 (불변 name, 가변 position)
│   ├─ CarName.java              // 이름 검증(<=5), 값 객체
│   ├─ Position.java             // 위치 값 객체(>=0)
│   ├─ MoveStrategy.java         // 이동 여부 결정 전략(테스트 용이)
│   ├─ RandomMoveStrategy.java   // Randoms 기반 구현(>=4면 전진)
│   ├─ Race.java                 // 레이스 전체 로직(라운드 진행, 우승자 계산)
│   └─ RoundResult.java          // 단일 라운드 결과 스냅샷 (출력용 DTO)
├─ ui
│   ├─ InputView.java            // 입력 안내 및 읽기
│   └─ OutputView.java           // 출력 전담
└─ Application.java              // main()
```

**application**
- GameRunner.java
  - 게임 전체의 실행 흐름을 관리 (입력 → 진행 → 출력)
  - 도메인과 UI 계층을 연결하는 오케스트레이터 역할

**domain**
  - Car.java
    - 자동차 도메인 객체
    - 이름(CarName)과 위치(Position)를 보유
    - 주어진 이동 전략(MoveStrategy)에 따라 전진 가능
  
  - CarName.java
    - 자동차 이름 값 객체 
    - 이름의 유효성 검증 수행 (공백 불가, 5자 이하)

  - Position.java 
    - 자동차의 위치 값 객체 
    - 음수 위치 불가, 전진 시 1 증가

  - MoveStrategy.java 
    - 자동차 이동 여부를 결정하는 전략 인터페이스 
    - 테스트에서 고정 전략으로 대체 가능 (전진/정지 제어 용이)

  - RandomMoveStrategy.java 
    - camp.nextstep.edu.missionutils.Randoms를 사용한 랜덤 이동 전략 
    - 0~9 범위의 난수 중 4 이상일 경우 전진

  - Race.java 
    - 전체 레이스를 관리 
    - 매 라운드 실행 및 우승자 계산 수행

  - RoundResult.java 
  - 각 라운드별 실행 결과(자동차 이름별 위치)를 스냅샷 형태로 저장

**ui**
  - InputView.java 
    - 사용자 입력 안내 및 입력값 읽기 담당 
    - 자동차 이름 목록과 시도 횟수를 입력받음
 
  - OutputView.java 
    - 콘솔 출력 담당 
      실행 결과, 라운드별 상태, 우승자 출력

**Application.java**
  - 프로그램 진입점 (main() 메서드)
  - GameRunner 실행을 통해 전체 게임을 시작
