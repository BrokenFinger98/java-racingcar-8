package racingcar.domain;

public class Car {
    private final CarName name;
    private Position position = new Position(0);

    public Car(CarName name) {
        this.name = name;
    }

    public void moveIf(MoveStrategy strategy) {
        if (strategy.shouldMove()) {
            position = position.forward();
        }
    }

    public String name() {
        return name.value();
    }

    public int position() {
        return position.value();
    }
}
