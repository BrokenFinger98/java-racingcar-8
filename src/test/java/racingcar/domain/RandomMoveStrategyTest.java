package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMoveStrategyTest {

    private static class FakeNumberPicker implements NumberPicker {
        private int value;

        public FakeNumberPicker(int value) {
            this.value = value;
        }

        @Override
        public int pickNumberInRange(int min, int max) {
            return value;
        }
    }

    @Test
    @DisplayName("랜덤 숫자가 4 이상이면 true를 반환한다.")
    void shouldReturnTrueWhenNumberIsGreaterOrEqualTo4() {
        // given
        FakeNumberPicker fakeNumberPicker = new FakeNumberPicker(4);
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy(fakeNumberPicker);

        // when
        boolean result = randomMoveStrategy.shouldMove();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("랜덤 숫자가 4 미만이면 false를 반환한다.")
    void shouldReturnFalseWhenNumberIsLessThan4() {
        // given
        FakeNumberPicker fakeNumberPicker = new FakeNumberPicker(3);
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy(fakeNumberPicker);

        // when
        boolean result = randomMoveStrategy.shouldMove();

        // then
        assertFalse(result);
    }
}