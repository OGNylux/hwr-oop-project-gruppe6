package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;
import hwr.oop.ToDoList;
import hwr.oop.handler.SortHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class SortHandlerTest {
    @Test
    void handleUserCommandPrioAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 2", "Description 2", "Bucket 1", Priority.LOW));
        toDoList.add(new ToDoItem("Task 3", "Description 3", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 4", "Description 4", "Bucket 1", Priority.HIGH));
        String[] args = {"gtd", "sort", "prio", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems()[i].getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 2", "Task 1", "Task 3", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandPrioDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 2", "Description 2", "Bucket 1", Priority.LOW));
        toDoList.add(new ToDoItem("Task 3", "Description 3", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 4", "Description 4", "Bucket 1", Priority.HIGH));
        String[] args = {"gtd", "sort", "prio", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems()[i].getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 4", "Task 1", "Task 3", "Task 2"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandCreatedAtAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 2", "Description 2", "Bucket 1", Priority.LOW));
        toDoList.add(new ToDoItem("Task 3", "Description 3", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 4", "Description 4", "Bucket 1", Priority.HIGH));
        String[] args = {"gtd", "sort", "create", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems()[i].getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 1", "Task 2", "Task 3", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandCreatedAtDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 2", "Description 2", "Bucket 1", Priority.LOW));
        toDoList.add(new ToDoItem("Task 3", "Description 3", "Bucket 1", Priority.MEDIUM));
        toDoList.add(new ToDoItem("Task 4", "Description 4", "Bucket 1", Priority.HIGH));
        String[] args = {"gtd", "sort", "create", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems()[i].getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 4", "Task 3", "Task 2", "Task 1"});
        // Add more assertions to verify the behavior of the add command
    }
}
