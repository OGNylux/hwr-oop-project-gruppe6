package hwr.oop;

import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUITest {

    @Test
    void getInputStreamTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        assertThat(testConsole.getInputStream()).isEqualTo(inputStream);
    }

    @Test
    void getOutputStreamTest() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        assertThat(testConsole.getOutputStream()).isEqualTo(outBuffer);
    }

    @Test
    void printTest() {
        LogMode mode = LogMode.SUCCESS;
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        String expectedOutput = mode.getColor() + "Test" + ConsoleColors.RESET + "\n";
        testConsole.print(mode, "Test");
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void sayTest() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        String expectedOutput = "Test\n";
        testConsole.say("Test");
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void listTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", new Bucket("Test"), Priority.LOW, LocalDate.now()));
        toDoItems.add(new ToDoItem("Test2", "Test2", new Bucket("Test"), Priority.LOW, LocalDate.now()));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            String userInput = "MyList\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            testConsole.list(toDoList);
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
            testConsole.list(toDoList);
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
    @Test
    void handleSortTest() {
        ToDoList toDoList = new ToDoList("MyList");
        toDoList.add(new ToDoItem("Apple", "Computers", new Bucket("Fruit"), Priority.MEDIUM, LocalDate.now()));
        toDoList.getItems().get(0).setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0));
        toDoList.add(new ToDoItem("Cucumber", "Water", new Bucket("Vegetable"), Priority.LOW, LocalDate.now().plusDays(1)));
        toDoList.getItems().get(1).setCreatedAt(LocalDateTime.of(2020, 1, 2, 0, 0));
        toDoList.add(new ToDoItem("Banana", "Minions", new Bucket("Weapon"), Priority.HIGH, LocalDate.now().plusDays(2)));

        // Priority Test
        toDoList.sortByPriority("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");
        toDoList.sortByPriority("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByCreatedAt("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByCreatedAt("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.bubbleUpBucket("Weapon");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByTitle("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByTitle("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");

        toDoList.getItems().get(1).setDone();
        toDoList.sortByDone("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");
        toDoList.sortByDone("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByDueDate("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByDueDate("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");
    }

    @Test
    void removeTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", new Bucket("Test"), Priority.LOW, LocalDate.now()));
        toDoItems.add(new ToDoItem("Test2", "Test2", new Bucket("Test2"), Priority.LOW, LocalDate.now()));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getItems()).hasSize(2);
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()).hasSize(1);
            assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Test2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void successTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.print(LogMode.SUCCESS, "great success");
            String expectedOutput;
            expectedOutput = ConsoleColors.GREEN_BOLD + "great success" + ConsoleColors.RESET +"\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void errorTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            // funktion Main.success aufrufen
            testConsole.print(LogMode.ERROR, "Error Message");
            String expectedOutput;
            // Output den du erwartest
            expectedOutput = ConsoleColors.RED_BOLD + "Error Message" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void sortHelpTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.sortHelp();
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "gtd sort [option]\n" +
                            "Options:\n" +
                            "  priority - sort by priority\n" +
                            "  createdAt- sort by creation date\n" +
                            "  dueDate  - sort by due date\n" +
                            "  bucket [bucket]- sort by bucket\n" +
                            "  title    - sort by title\n" +
                            "  done     - sort by done\n" +
                            "  help     - print this help\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void showBucketsTestEmpty() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        ToDoList toDoList = new ToDoList("Test");
        toDoList.getBuckets();
        String expectedOutput = "\uD83D\uDC40Looks Empty here... Add some buckets!";
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void showBucketsTestElement() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        ToDoList toDoList = new ToDoList("Test");
        toDoList.addBucket(new Bucket("Test"));
        testConsole.showBuckets(toDoList);
        String expectedOutput = "🪣Test\n";
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}
