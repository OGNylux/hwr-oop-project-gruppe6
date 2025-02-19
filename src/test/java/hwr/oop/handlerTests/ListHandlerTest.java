package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.consoleuserinterface.ConsoleUserInterface;
import hwr.oop.handler.ListHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
class ListHandlerTest {
    @Test
    void listHandlerTest(){
        ListHandler listHandler = new ListHandler();
        assertThat(listHandler).isNotNull();
    }
    @Test
    void handleUserCommandTestWrongNumberOfArgs(){
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBuffer);
        new ListHandler().handleUserCommand(new ToDoList("Test"), new ConsoleUserInterface(out, in), new String[]{"gtd", "list", "test"});
        assertThat(outBuffer.toString().replace("\r", "")).hasToString("\u001B[1;33mCould not list... If that is what you wanted to do, try 'gtd list'\u001B[0m\n");
    }
    @Test
    void listTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", new Bucket("Test"), Priority.LOW, LocalDate.now(), EstimatedTime.MEDIUM));
        toDoItems.add(new ToDoItem("Test2", "Test2", new Bucket("Test"), Priority.LOW, LocalDate.now(), EstimatedTime.MEDIUM));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            String userInput = "MyList\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            new ListHandler().list(toDoList, testConsole);
            // Check the program output
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    toDoList.getItems().get(0).toString() + "\n" +
                    toDoList.getItems().get(1).toString() + "\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void listEmptyTest() {
        ToDoList toDoList = new ToDoList("MyList");
        try {
            String userInput = "MyList\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            new ListHandler().list(toDoList, testConsole);
            // Check the program output
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    "👀Looks Empty here... Add some tasks!\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
