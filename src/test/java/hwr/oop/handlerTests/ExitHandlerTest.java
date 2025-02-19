package hwr.oop.handlerTests;

import hwr.oop.consoleuserinterface.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.ExitHandler;
import hwr.oop.persistence.PersistenceFileNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ExitHandlerTest {
    @Test
    void exitHandlerTest(){
        ExitHandler exitHandler = new ExitHandler();
        assertThat(exitHandler).isNotNull();
    }
    @Test
    void handleUserCommandCallExit(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        ToDoList toDoList = new ToDoList("MyList");
        String[] args = {"gtd", "exit"};
        try {
            new ExitHandler().handleUserCommand(toDoList, cui, args);
        } catch (PersistenceFileNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Goodbye!");
        }
    }
    @Test
    void handleUserCommandTooManyArgsTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        ToDoList toDoList = new ToDoList("MyList");
        String[] args = {"gtd", "exit", "Uni"};
        try {
            new ExitHandler().handleUserCommand(toDoList, cui, args);
        } catch (PersistenceFileNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Goodbye!");
        }
        assertThat(outBuffer.toString().replace("\r", "")).hasToString("\u001B[1;31mCannot process additional arguments.\u001B[0m\n");
    }
    @Test
    void handleUserCommandFailedTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        ToDoList toDoList = new ToDoList("MyList");
        String[] args = {"gtd", "ex"};
        try {
            new ExitHandler().handleUserCommand(toDoList, cui, args);
        } catch (PersistenceFileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expected = "\u001B[1;31mCould not exit... If that is what you wanted to do, try 'gtd exit'\u001B[0m\n";
        String actual = outBuffer.toString().replace("\r", "");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void exitTest() {
        try {
            new ExitHandler().exit(new ToDoList("MyList"), new ConsoleUserInterface(System.out, System.in));
        } catch (ExitHandler.ExitProgramException e) {
            assertThat(e.getMessage()).isEqualTo("Goodbye!");
        }
    }
    @Test
    void testExitProgramException() {
        ExitHandler.ExitProgramException exitProgramException = new ExitHandler.ExitProgramException();
        assertThat(exitProgramException.getMessage()).isEqualTo("Goodbye!");
    }
}
