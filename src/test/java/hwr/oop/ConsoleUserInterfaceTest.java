package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleUserInterfaceTest {

    @Test
    void consoleUI_TypeThreeAndFour_OutputIsSeven() {
        // given
        InputStream inputStream = createInputStreamForInput("3\n4\n");
        OutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.getLogger(ConsoleUserInterface.class.getName());
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(logger, inputStream);
        logger.addHandler(new StreamHandler(outputStream, new SimpleFormatter()));
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
