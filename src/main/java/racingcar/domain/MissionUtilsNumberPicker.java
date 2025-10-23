package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class MissionUtilsNumberPicker implements NumberPicker {
    @Override
    public int pickNumberInRange(int min, int max) {
        return Randoms.pickNumberInRange(min, max);
    }
}
