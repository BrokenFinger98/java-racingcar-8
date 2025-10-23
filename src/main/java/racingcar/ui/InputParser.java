package racingcar.ui;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private InputParser() {

    }

    public static List<String> parseCarNames(String line) {
        validateCarNamesInput(line);
        return Arrays.stream(line.split(",")).map(String::trim).toList();
    }

    public static int parseAttemptCount(String line) {
        try {
            int n = Integer.parseInt(line.trim());
            if (n <= 0) {
                throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 정수여야 합니다.");
        }
    }

    private static void validateCarNamesInput(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }
}
