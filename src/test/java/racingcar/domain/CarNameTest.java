package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarNameTest {

    @Test
    @DisplayName("CarName 생성: 정상 입력 시 객체를 생성한다")
    void carName_shouldCreate_whenValidName() {
        // given
        String pobi = "pobi";

        // when
        CarName carName = new CarName(pobi);

        // then
        assertEquals(pobi, carName.value());
    }

    @Test
    @DisplayName("CarName 생성: null 입력 시 예외를 발생시킨다")
    void carName_shouldThrow_whenNull() {
        // given
        String pobi = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(pobi));
    }

    @Test
    @DisplayName("CarName 생성: 공백 입력 시 예외를 발생시킨다")
    void carName_shouldThrow_whenBlank() {
        // given
        String pobi = "";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(pobi));
    }

    @Test
    @DisplayName("CarName 생성: 이름이 5자 초과 시 예외를 발생시킨다")
    void carName_shouldThrow_whenLengthExceedsLimit() {
        // given
        String name = "abcdef";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(name));
    }
}