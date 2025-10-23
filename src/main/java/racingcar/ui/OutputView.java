package racingcar.ui;

import java.util.List;
import java.util.Map;
import racingcar.domain.RoundResult;

public class OutputView {

    public void printStart() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printRound(RoundResult roundResult) {
        for (Map.Entry<String,Integer> e : roundResult.snapshot().entrySet()) {
            System.out.println(e.getKey() + " : " + "-".repeat(e.getValue()));
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}