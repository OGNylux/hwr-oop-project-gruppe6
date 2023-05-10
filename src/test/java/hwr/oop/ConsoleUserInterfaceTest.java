package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleUserInterfaceTest {

    @Test
    void consoleUI_TypeThreeAndFour_OutputIsSeven() {
        // given
        InputStream inputStream = createInputStreamForInput("3\n4\n");
        OutputStream outputStream = new ByteArrayOutputStream();
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(new PrintStream(outputStream), inputStream);
        // when
        consoleUI.error("ErrorMessage");
        // then
        String output = outputStream.toString();
        assertThat(output).isEqualTo("ErrorMessage\n");
    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
