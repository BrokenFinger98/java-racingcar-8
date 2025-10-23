package racingcar.domain;

public class Position {
    private final int value;

    public Position(int value) {
        validateNonNegative(value);
        this.value = value;
    }

    public Position forward() {
        return new Position(value + 1);
    }

    public int value() {
        return value;
    }

    private static void validateNonNegative(int value) {
        if(value < 0){
            throw new IllegalArgumentException("위치는 음수가 될 수 없습니다.");
        }
    }
}
