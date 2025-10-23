package racingcar.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ASK_ATTEMPT_COUNTS_MESSAGE = "시도할 횟수는 몇 회인가요?";

    public List<String> readCarNames() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);
        String line = Console.readLine();
        validateCarNamesInput(line);
        return parseCarNames(line);
    }

    public int readAttemptCount() {
        System.out.println(ASK_ATTEMPT_COUNTS_MESSAGE);
        String line = Console.readLine();
        return parseAttemptCount(line);
    }

    private static List<String> parseCarNames(String line) {
        return Arrays.stream(line.split(",")).map(String::trim).toList();
    }

    private static void validateCarNamesInput(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }

    private static int parseAttemptCount(String line) {
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
