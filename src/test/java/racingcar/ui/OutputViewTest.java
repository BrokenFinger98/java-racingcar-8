package racingcar.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.RoundResult;

class OutputViewTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    private static String ls() {
        return System.lineSeparator();
    }

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("printStart: '실행 결과' 헤더를 줄바꿈과 함께 출력한다")
    void printStart_shouldPrintHeaderWithNewLine() {
        // given
        OutputView view = new OutputView();

        // when
        view.printStart();

        // then
        String stdout = out.toString();
        assertTrue(stdout.contains(ls() + "실행 결과"));
    }

    @Test
    @DisplayName("printRound: RoundResult 스냅샷을 입력 순서대로 출력한다")
    void printRound_shouldPrintSnapshotInInputOrder() {
        // given
        Map<String, Integer> snap = new LinkedHashMap<>();
        snap.put("pobi", 2);
        snap.put("woni", 0);
        snap.put("john", 3);
        RoundResult result = new RoundResult(snap);

        OutputView view = new OutputView();

        // when
        view.printRound(result);

        // then
        String stdout = out.toString();

        String expected = "pobi : --" + ls() + "woni : " + ls() + "john : ---" + ls() + ls();

        assertEquals(expected, stdout);
    }

    @Test
    @DisplayName("printWinners: 우승자를 쉼표로 구분하여 출력한다")
    void printWinners_shouldPrintWinnersJoinedByComma() {
        // given
        OutputView view = new OutputView();
        List<String> winners = List.of("pobi", "john");

        // when
        view.printWinners(winners);

        // then
        String stdout = out.toString();
        assertEquals("최종 우승자 : pobi, john" + ls(), stdout);
    }
}