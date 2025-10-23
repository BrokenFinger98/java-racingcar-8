package racingcar.ui;

public class InputView {

    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ASK_ATTEMPT_COUNTS_MESSAGE = "시도할 횟수는 몇 회인가요?";

    public void printAskCarNamesMessage() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);
    }

    public void printAskAttemptCountMessage() {
        System.out.println(ASK_ATTEMPT_COUNTS_MESSAGE);
    }
}
