package racingcar;

import racingcar.application.GameRunner;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        GameRunner gameRunner = new GameRunner(new InputView(), new OutputView());
        gameRunner.run();
    }
}
