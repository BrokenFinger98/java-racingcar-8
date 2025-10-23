package racingcar.domain;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private InputParser() {

    }

    public static List<String> parseCarNames(String line) {
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
}
