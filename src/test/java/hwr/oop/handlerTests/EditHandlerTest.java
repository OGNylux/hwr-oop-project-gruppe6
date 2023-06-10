package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.handler.EditHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EditHandlerTest {
    @Test
    void editHandlerTest(){
        EditHandler editHandler = new EditHandler();
        assertThat(editHandler).isNotNull();
    }
    @Test
    void testEditTasksIndexOutOfBounds() throws ConsoleUserInterface.CouldNotReadInputException {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.add(new ToDoItem("Title", "Description", new Bucket("Bucket"), Priority.MEDIUM, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("n\n".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        EditHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "edit", "1"});
        String expected = "\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testEditTasks() throws ConsoleUserInterface.CouldNotReadInputException {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.add(new ToDoItem("Title", "Description", new Bucket("Bucket"), Priority.MEDIUM, LocalDate.of(2020,1,1)));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("TitleNew\nNewDescription\n1\nBucket1\n1.2.2020".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        EditHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "edit", "0"});
        String expected = "Editing task at index 0:\n" +
                "⏭️ Title\n" +
                "Description\n" +
                "<\u001B[1;36mBucket\u001B[0m> \u001B[1;33mMEDIUM\u001B[0m 2020-01-01\n" +
                "Enter new Title or press enter to skip\n" +
                "Enter new Description or press enter to skip\n" +
                "Enter new Priority or press enter to skip\n" +
                "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                "Enter new Bucket or press enter to skip\n" +
                "Enter new Due Date or press enter to skip\n" +
                "\u001B[1;32mTask Edited Successfully!\u001B[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("TitleNew");
        assertThat(toDoList.getItems().get(0).getDescription()).isEqualTo("NewDescription");
        assertThat(toDoList.getItems().get(0).getPriority()).isEqualTo(Priority.LOW);
        assertThat(toDoList.getItems().get(0).getBucket().getBucketName()).isEqualTo("Bucket1");
        assertThat(toDoList.getItems().get(0).getDueDate()).isEqualTo(LocalDate.of(2020, 2, 1));
    }
    @Test
    void testToLittleCommands() throws ConsoleUserInterface.CouldNotReadInputException {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.add(new ToDoItem("Title", "Description", new Bucket("Bucket"), Priority.MEDIUM, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("n\n".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        EditHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "edit"});
        String expected = "Invalid Command.\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testToLittleArguments() throws ConsoleUserInterface.CouldNotReadInputException {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.add(new ToDoItem("Title", "Description", new Bucket("Bucket"), Priority.MEDIUM, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("n\n".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        EditHandler.handleUserCommand(toDoList, cui, new String[]{"gtd"});
        String expected = "\u001B[1;31mPlease provide a valid Command.\u001B[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testEditTasksIndexOutOfBoundsNewIndex() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.add(new ToDoItem("Title", "Description", new Bucket("Bucket"), Priority.MEDIUM, LocalDate.of(2023,1,1)));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("y\n0\n".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        try {
            EditHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "edit", "1"});
        } catch (ConsoleUserInterface.CouldNotReadInputException e) {
            String expected = "\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                    "Try again? (y/n)\n" +
                    "Please enter the index of the task you want to edit.\n" +
                    "Editing task at index 0:\n" +
                    "⏭️ Title\n" +
                    "Description\n" +
                    "<\u001B[1;36mBucket\u001B[0m> \u001B[1;33mMEDIUM\u001B[0m 2023-01-01\n" +
                    "Enter new Title or press enter to skip\n";
            String actual = outBuffer.toString();
            assertThat(actual).contains(expected);
        }

    }
}
