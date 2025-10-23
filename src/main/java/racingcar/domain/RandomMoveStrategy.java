package racingcar.domain;

public class RandomMoveStrategy implements MoveStrategy {
    private final NumberPicker picker;

    public RandomMoveStrategy(NumberPicker picker) {
        this.picker = picker;
    }

    @Override
    public boolean shouldMove() {
        return picker.pickNumberInRange(0, 9) >= 4;
    }
}
