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
    @DisplayName("Car를 생성한다.")
    void car() {
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
    void moveIf_shouldMove_whenConditionIsTrue() {
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
    @DisplayName("moveIf: 이동 조건이 false일 경우 위치가 그대로 유지된다")
    void moveIf_shouldNotMove_whenConditionIsFalse() {
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