package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;

public class RandomMoveStrategy implements MoveStrategy {
    private static final List<Integer> NUMBERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Override
    public boolean shouldMove() {
        return Randoms.pickNumberInList(NUMBERS) >= 4;
    }
}
