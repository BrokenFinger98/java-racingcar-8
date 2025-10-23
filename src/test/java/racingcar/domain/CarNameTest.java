package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarNameTest {

    @Test
    @DisplayName("CarName을 생성한다.")
    void carName() {
        // given
        String pobi = "pobi";

        // when
        CarName carName = new CarName(pobi);

        // then
        assertEquals(pobi, carName.value());
    }
    
    @Test
    @DisplayName("이름이 null이면 IllegalArgumentException 발생")
    void carName_shouldThrow_whenNull() {
        // given
        String pobi = null;
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(pobi));
    }
    
    @Test
    @DisplayName("이름이 Blank이면 IllegalArgumentException 발생")
    void carName_shouldThrow_whenBlank() {
        // given
        String pobi = "";
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(pobi));
    }
    
    @Test
    @DisplayName("이름이 5자 초과면 IllegalArgumentException 발생")
    void carName_shouldThrow_whenLengthGreaterThan5() {
        // given
        String name = "abcdef";
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CarName(name));
    }
}