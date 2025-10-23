package racingcar.application;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.*;
import racingcar.ui.InputParser;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;

public class GameRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final MoveStrategy moveStrategy;

    public GameRunner(InputView inputView, OutputView outputView) {
        this(inputView, outputView, new RandomMoveStrategy(new MissionUtilsNumberPicker()));
    }

    public GameRunner(InputView inputView, OutputView outputView, MoveStrategy moveStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.moveStrategy = moveStrategy;
    }

    public void run() {
        try {
            List<String> carNames = readCarNames();
            int attemptCount = readAttemptCount();
            List<Car> cars = createCars(carNames);

            Race race = new Race(cars, moveStrategy);

            outputView.printStart();
            playRounds(race, attemptCount);
            outputView.printWinners(race.findWinners());
        } finally {
            Console.close();
        }
    }

    private List<String> readCarNames() {
        inputView.printAskCarNamesMessage();
        String line = Console.readLine();
        return InputParser.parseCarNames(line);
    }

    private int readAttemptCount() {
        inputView.printAskAttemptCountMessage();
        String line = Console.readLine();
        return InputParser.parseAttemptCount(line);
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void playRounds(Race race, int attemptCount) {
        for (int i = 0; i < attemptCount; i++) {
            RoundResult result = race.runOneRound();
            outputView.printRound(result);
        }
    }
}