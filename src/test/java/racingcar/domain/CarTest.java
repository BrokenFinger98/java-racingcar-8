package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    private RandomMoveStrategy randomMoveStrategy;

    private static class FakeNumberPicker implements NumberPicker {
        private int value;

        public FakeNumberPicker(int value) {
            this.value = value;
        }

        @Override
        public int pickNumberInRange(int min, int max) {
            return value;
        }
    }

    @Test
    @DisplayName("Car 생성: 이름이 주어지면 초기 위치 0으로 생성된다")
    void car_shouldCreate_withInitialPositionZero() {
        // given
        CarName carName = new CarName("pobi");

        // when
        Car car = new Car(carName);

        // then
        assertEquals(carName.value(), car.name());
        assertEquals(0, car.position());
    }

    @Test
    @DisplayName("moveIf: 이동 조건이 true일 경우 위치가 1 증가한다")
    void moveIf_shouldIncreasePosition_whenConditionTrue() {
        // given
        FakeNumberPicker fakeNumberPicker = new FakeNumberPicker(4);
        randomMoveStrategy = new RandomMoveStrategy(fakeNumberPicker);
        CarName carName = new CarName("pobi");
        Car car = new Car(carName);

        // when
        car.moveIf(randomMoveStrategy);

        // then
        assertEquals(carName.value(), car.name());
        assertEquals(1, car.position());
    }

    @Test
    @DisplayName("moveIf: 이동 조건이 false일 경우 위치가 유지된다")
    void moveIf_shouldNotChangePosition_whenConditionFalse() {
        // given
        FakeNumberPicker fakeNumberPicker = new FakeNumberPicker(3);
        randomMoveStrategy = new RandomMoveStrategy(fakeNumberPicker);
        CarName carName = new CarName("pobi");
        Car car = new Car(carName);

        // when
        car.moveIf(randomMoveStrategy);

        // then
        assertEquals(carName.value(), car.name());
        assertEquals(0, car.position());
    }
}