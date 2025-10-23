package racingcar.domain;

public class CarName {
    private static final int MAX = 5;
    private final String value;

    public CarName(String raw) {
        validateNotBlank(raw);
        String value = raw.trim();
        validateLength(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private static void validateNotBlank(String raw) {
        if (raw == null || raw.isBlank()) throw new IllegalArgumentException("자동차 이름이 비었습니다.");
    }

    private static void validateLength(String value) {
        if (value.length() > MAX) throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다: " + value);
    }
}
