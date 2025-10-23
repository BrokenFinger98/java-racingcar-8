package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaceTest {

    @Test
    @DisplayName("Race 생성: 정상적인 자동차 목록으로 생성된다")
    void race_shouldCreate_whenValidCars() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new MissionUtilsNumberPicker());
        Car car1 = car("pobi");
        Car car2 = car("woni");

        // when & then
        assertDoesNotThrow(() -> new Race(List.of(car1, car2), moveStrategy));
    }

    @Test
    @DisplayName("Race 생성: 자동차 목록이 비어 있으면 예외 발생")
    void race_shouldThrow_whenCarsEmpty() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new MissionUtilsNumberPicker());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Race(List.of(), moveStrategy));
    }

    @Test
    @DisplayName("runOneRound: 모든 자동차가 이동 조건을 만족하면 전진한다")
    void runOneRound_shouldMoveAllCars_whenConditionTrue() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new FixedNumberPicker(4));
        Car car1 = car("pobi");
        Car car2 = car("woni");
        Race race = new Race(List.of(car1, car2), moveStrategy);

        // when
        RoundResult roundResult = race.runOneRound();

        // then
        Map<String, Integer> snapshot = roundResult.snapshot();
        assertEquals(2, snapshot.size());
        assertEquals(1, snapshot.get(car1.name()));
        assertEquals(1, snapshot.get(car2.name()));
    }

    @Test
    @DisplayName("runOneRound: 일부 자동차만 이동 조건을 만족하면 해당 자동차만 전진한다")
    void runOneRound_shouldMoveOnlyMatchingCars_whenMixedConditions() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new SequenceNumberPicker(4, 3));
        Car car1 = car("pobi");
        Car car2 = car("woni");
        Race race = new Race(List.of(car1, car2), moveStrategy);

        // when
        RoundResult roundResult = race.runOneRound();

        // then
        Map<String, Integer> snapshot = roundResult.snapshot();
        assertEquals(2, snapshot.size());
        assertEquals(1, snapshot.get(car1.name()));
        assertEquals(0, snapshot.get(car2.name()));
    }

    @Test
    @DisplayName("findWinners: 모든 자동차가 같은 위치일 경우 공동 우승자를 반환한다")
    void findWinners_shouldReturnMultipleWinners_whenTie() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new FixedNumberPicker(4));
        Car car1 = car("pobi");
        Car car2 = car("woni");
        Race race = new Race(List.of(car1, car2), moveStrategy);

        race.runOneRound();

        // when
        List<String> winners = race.findWinners();

        // then
        assertEquals(2, winners.size());
        assertTrue(winners.containsAll(List.of(car1.name(), car2.name())));
    }

    @Test
    @DisplayName("findWinners: 최고 위치의 자동차가 하나일 경우 단일 우승자를 반환한다")
    void findWinners_shouldReturnSingleWinner_whenOnlyOneLeads() {
        // given
        MoveStrategy moveStrategy = new RandomMoveStrategy(new SequenceNumberPicker(4, 3));
        Car car1 = car("pobi");
        Car car2 = car("woni");
        Race race = new Race(List.of(car1, car2), moveStrategy);

        race.runOneRound();

        // when
        List<String> winners = race.findWinners();

        // then
        assertEquals(1, winners.size());
        assertEquals(car1.name(), winners.getFirst());
    }

    private static class FixedNumberPicker implements NumberPicker {
        private final int value;

        FixedNumberPicker(int value) {
            this.value = value;
        }

        @Override
        public int pickNumberInRange(int min, int max) {
            return value;
        }
    }

    private static class SequenceNumberPicker implements NumberPicker {
        private final int[] values;
        private int idx = 0;

        SequenceNumberPicker(int... values) {
            this.values = values;
        }

        @Override
        public int pickNumberInRange(int min, int max) {
            int v = values[idx % values.length];
            idx++;
            return v;
        }
    }

    private static Car car(String name) {
        return new Car(new CarName(name));
    }
}