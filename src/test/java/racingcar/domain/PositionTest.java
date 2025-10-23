package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("Position을 생성한다.")
    void position() {
        // given
        int value = 1;

        // when
        Position position = new Position(value);

        // then
        assertEquals(value, position.value());
    }

    @Test
    @DisplayName("Position 생성시, value가 음수면 IllegalArgumentException 발생")
    void position_shouldThrow_whenNonPositiveValue() {
        // given
        int value = -1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Position(value));
    }

    @Test
    @DisplayName("Position을 1만큼 증가시킨다.")
    void forward() {
        // given
        int value = 1;
        Position position = new Position(value);

        // when
        position = position.forward();

        // then
        assertEquals(value + 1, position.value());
    }
}