package racingcar.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("parseCarNames: 쉼표로 구분된 문자열을 공백 제거 후 리스트로 변환한다")
    void parseCarNames_shouldSplitAndTrim() {
        // given
        String input = " pobi, woni ,jun ";

        // when
        List<String> names = InputParser.parseCarNames(input);

        // then
        assertEquals(List.of("pobi", "woni", "jun"), names);
    }

    @Test
    @DisplayName("parseCarNames: 빈 문자열 입력 시 예외를 발생시킨다")
    void parseCarNames_shouldThrow_whenBlank() {
        // given
        String input = "   ";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseCarNames(input));
    }

    @Test
    @DisplayName("parseAttemptCount: 문자열을 정수로 변환한다")
    void parseAttemptCount_shouldParsePositiveInteger() {
        // given
        String input = "5";

        // when
        int count = InputParser.parseAttemptCount(input);

        // then
        assertEquals(5, count);
    }

    @Test
    @DisplayName("parseAttemptCount: 앞뒤 공백이 있어도 정상 파싱된다")
    void parseAttemptCount_shouldTrimWhitespace() {
        // given
        String input = "   7  ";

        // when
        int count = InputParser.parseAttemptCount(input);

        // then
        assertEquals(7, count);
    }

    @Test
    @DisplayName("parseAttemptCount: 0 이하 입력 시 예외를 발생시킨다")
    void parseAttemptCount_shouldThrow_whenNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseAttemptCount("0"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseAttemptCount("-3"));
    }

    @Test
    @DisplayName("parseAttemptCount: 정수가 아닌 입력 시 예외를 발생시킨다")
    void parseAttemptCount_shouldThrow_whenNotInteger() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseAttemptCount("abc"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseAttemptCount("3.14"));
    }
}