package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("Position 생성: 양수 값으로 생성된다")
    void position_shouldCreate_whenPositiveValue() {
        // given
        int value = 1;

        // when
        Position position = new Position(value);

        // then
        assertEquals(value, position.value());
    }

    @Test
    @DisplayName("Position 생성: 음수 입력 시 예외를 발생시킨다")
    void position_shouldThrow_whenNegativeValue() {
        // given
        int value = -1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Position(value));
    }

    @Test
    @DisplayName("forward: 현재 위치를 1 증가시킨다")
    void forward_shouldIncreasePositionByOne() {
        // given
        int value = 1;
        Position position = new Position(value);

        // when
        position = position.forward();

        // then
        assertEquals(value + 1, position.value());
    }
}