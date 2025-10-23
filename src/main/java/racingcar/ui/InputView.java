package racingcar.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import racingcar.domain.InputParser;

public class InputView {

    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ASK_ATTEMPT_COUNTS_MESSAGE = "시도할 횟수는 몇 회인가요?";

    public List<String> readCarNames() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);
        String line = Console.readLine();
        validateCarNamesInput(line);
        return InputParser.parseCarNames(line);
    }

    public int readAttemptCount() {
        System.out.println(ASK_ATTEMPT_COUNTS_MESSAGE);
        String line = Console.readLine();
        return InputParser.parseAttemptCount(line);
    }

    private static void validateCarNamesInput(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }
}
