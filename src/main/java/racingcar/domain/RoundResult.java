package racingcar.domain;

import java.util.Map;

public record RoundResult(
        Map<String, Integer> snapshot
) {

    public RoundResult {
        if (snapshot == null || snapshot.isEmpty()) {
            throw new IllegalArgumentException("라운드 결과는 비어 있을 수 없습니다.");
        }
        snapshot = Map.copyOf(snapshot);
    }
}
