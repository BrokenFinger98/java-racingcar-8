package racingcar.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;

class InputViewTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("printAskCarNamesMessage: 자동차 이름 입력 안내 문구를 출력한다")
    void printAskCarNamesMessage_shouldPrintPrompt() {
        // given
        InputView view = new InputView();

        // when
        view.printAskCarNamesMessage();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"));
    }

    @Test
    @DisplayName("printAskAttemptCountMessage: 시도 횟수 입력 안내 문구를 출력한다")
    void printAskAttemptCountMessage_shouldPrintPrompt() {
        // given
        InputView view = new InputView();

        // when
        view.printAskAttemptCountMessage();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("시도할 횟수는 몇 회인가요?"));
    }
}