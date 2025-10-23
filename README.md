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
racingcar
- application
    - GameRunner.java
- domain
    - Car.java
    - CarName.java
    - Position.java
    - MoveStrategy.java
    - RandomMoveStrategy.java
    - Race.java
    - RoundResult.java
- ui
    - InputView.java
    - OutputView.java
- Application.java

---

## 클래스 설명

### application

#### `GameRunner`
- **역할:** 애플리케이션 전체 실행 흐름을 담당하는 오케스트레이터  
  (입력 → 레이스 진행 → 결과 출력)
- **책임:**
    - 사용자 입력 처리 (`readCarNames`, `readAttemptCount`)
    - 자동차 객체 생성 (`createCars`)
    - 라운드 반복 실행 (`playRounds`)
    - 최종 우승자 출력
- `MoveStrategy`를 의존성 주입 방식으로 받아 테스트 용이성 확보
- `Console.close()`를 `finally` 블록에서 수행하여 리소스 안전하게 종료

---

### domain

#### `Car`
- **역할:** 하나의 자동차를 표현하는 도메인 엔티티
- **책임:**
    - 불변 객체 `CarName`을 통해 이름 관리
    - `Position` 객체를 통해 현재 위치 관리
    - `moveIf(MoveStrategy)`로 주어진 전략에 따라 이동 여부 결정

#### `CarName`
- **역할:** 자동차 이름을 표현하는 값 객체 (Value Object)
- **검증 규칙:**
    - `null` 또는 공백 문자열 금지
    - 최대 길이 5자 제한 (`"자동차 이름은 5자 이하여야 합니다."`)

#### `Position`
- **역할:** 자동차의 현재 위치를 표현하는 값 객체
- **검증 규칙:**
    - 음수 값 불가 (`"위치는 음수가 될 수 없습니다."`)
- **동작:**
    - `forward()` 호출 시 1 증가된 새로운 `Position` 반환

#### `MoveStrategy`
- **역할:** 자동차의 이동 여부를 결정하는 전략 인터페이스
- **특징:** 테스트에서 더미(Fake) 구현체로 교체 가능하여 전진/정지 제어 용이

#### `RandomMoveStrategy`
- **역할:** MissionUtils Randoms를 이용해 난수를 생성하고 이동 여부 결정
- **로직:** 0~9 사이 난수를 생성하여 `4 이상일 경우 이동(true)`

#### `NumberPicker`
- **역할:** 난수 생성에 대한 추상화 계층
- **용도:** 테스트에서 고정값(`FakeNumberPicker`, `FixedNumberPicker`)으로 대체 가능

#### `MissionUtilsNumberPicker`
- **역할:** `camp.nextstep.edu.missionutils.Randoms.pickNumberInRange()`를 사용하는 구현체
- **범위:** 0~9 정수 반환

#### `Race`
- **역할:** 전체 레이스를 관리하는 핵심 클래스
- **책임:**
    - 자동차 리스트를 받아 매 라운드 진행 (`runOneRound()`)
    - `RoundResult`를 반환하여 각 라운드 상태를 스냅샷 형태로 제공
    - 우승자 계산 (`findWinners()`): 최대 위치를 기준으로 공동 우승자 허용
- **유효성 검증:** 자동차 리스트가 비어 있을 경우 예외 발생

#### `RoundResult`
- **역할:** 단일 라운드의 결과를 자동차별 위치로 저장하는 불변 객체
- **특징:**
    - 내부 `Map<String, Integer>`를 `Collections.unmodifiableMap(new LinkedHashMap<>(snapshot))`으로 래핑
    - 입력 순서 유지 (`LinkedHashMap`)
    - 외부에서 Map 수정 불가능 (불변성 보장)

---

### ui

#### `InputParser`
- **역할:** 문자열 입력값을 유효성 검증 및 변환하는 유틸 클래스
- **책임:**
    - 자동차 이름 문자열을 `List<String>`으로 분리 (`split(",")`)
    - 시도 횟수 문자열을 정수로 변환 및 유효성 검증 (`> 0`)

#### `InputView`
- **역할:** 사용자에게 입력을 요청하고 메시지를 출력하는 View 계층
- **출력 메시지:**
    - `"경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"`
    - `"시도할 횟수는 몇 회인가요?"`

#### `OutputView`
- **역할:** 콘솔 출력을 담당하는 View 계층
- **책임:**
    - `"실행 결과"` 헤더 출력
    - 각 라운드 결과를 자동차 이름 순서대로 출력
    - 우승자 목록을 `"최종 우승자 : pobi, woni"` 형식으로 출력
- **특징:**
    - `LinkedHashMap` 기반으로 입력 순서 보장
    - 테스트에서 `System.out` 캡처를 통해 출력 검증 가능

---

### Application
- **역할:** 프로그램의 진입점 (`main` 메서드)
- **책임:**
    - `InputView` / `OutputView` 객체 생성
    - `GameRunner` 실행을 통해 전체 게임 시작

---