package racingcar.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Race {
    private final List<Car> cars;
    private final MoveStrategy moveStrategy;

    public Race(final List<Car> cars, final MoveStrategy moveStrategy) {
        this.cars = requireNonEmpty(cars);
        this.moveStrategy = moveStrategy;
    }

    public RoundResult runOneRound() {
        moveAllCars();
        Map<String, Integer> snapshot = snapshotPositions();
        return new RoundResult(snapshot);
    }

    public List<String> findWinners() {
        int max = findMaxPosition();
        return extractWinners(max);
    }

    private void moveAllCars() {
        cars.forEach(car -> car.moveIf(moveStrategy));
    }

    private Map<String, Integer> snapshotPositions() {
        return cars.stream().collect(Collectors.toMap(Car::name, Car::position));
    }

    private List<String> extractWinners(int maxPosition) {
        return cars.stream()
                .filter(car -> car.position() == maxPosition)
                .map(Car::name)
                .toList();
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::position)
                .max()
                .orElse(0);
    }

    private static List<Car> requireNonEmpty(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("자동차가 1대 이상 필요합니다.");
        }
        return cars;
    }
}