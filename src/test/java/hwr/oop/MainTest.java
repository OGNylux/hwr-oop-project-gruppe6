package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void getEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        if (env == null) {
            System.out.println("No environment variables found");
        } else {
            for (String envVar : env) {
                System.out.format("%s=%s%n",
                        envVar,
                        System.getenv(envVar));
            }
        }
    }

    @Test
    void setEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        testEnvProgram.setEnvironmentVariables("data.json", "MyList");
        String[] env = testEnvProgram.getEnvironmentVariables();
        assertThat(env).contains("data.json");
        assertThat(env).contains("MyList");
    }
    @Test
    void testWelcome() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "0\n" + "\n" + "data.json\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            ToDoList toDoList = Main.welcome();
            // Check the program output
            String expectedOutput;
            if (env == null) {
                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n" +
                        "Looks Like it is your first time using this program.\n" +
                        "Lets set you up first.\n" +
                        "Please enter a name for your list\n" +
                        "> " +
                        "Please provide a filePath to an existing .json file to Load your list from.\n" +
                        "If you don't have one press enter to create specify your path.\n" +
                        "> " +
                        "Please enter your a path to a file to save your list to.\n" +
                        "> ";
            } else {
                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n";
            }

            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void helpTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            Main.help();
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "gtd [command] [arguments]\n" +
                    "Commands:\n" +
                    "  help                -  print this help\n" +
                    "  add [Item Index]    -  add a new task\n" +
                    "  remove [Item Index] -  remove a task\n" +
                    "  done [Item Index]   -  mark a task as done\n" +
                    "  edit [Item Index]   -  edit a task\n" +
                    "  list                -  list all tasks\n" +
                    "  createBucket [bucket name]      -  create a bucket for tasks\n" +
                    "  editBuckets [index] [new name]  -  changes bucket name\n" +
                    "  showBuckets                     -  show buckets for tasks \n" +
                    "  sort                -  sort your tasks\n" +
                    "  clear               -  clear all tasks\n" +
                    "  exit                -  exit the program\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void addNoFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            String userInput = "Title\nDescription\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            ToDoList toDoList = new ToDoList("MyList");
            Main.add(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Create a new task\n" +
                    "Please enter a title for your task\n" +
                    "Please enter a description for your task\n" +
                    "Please enter a priority for your task\n" +
                    "\u001B[1;34m1 - LOW, \u001B[1;33m2 - MEDIUM, \u001B[1;31m3 - HIGH\u001B[0m\n" +
                    "Add a Tag to group your tasks\n" +
                    "\u001B[1;32mTask Created Successfully!\u001B[0m\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("Title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void addFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            String userInput = "Title\nDescription\n3\nTag\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            ToDoList toDoList = new ToDoList("MyList", "addTestFile");
            Main.add(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Create a new task\n" +
                        "Please enter a title for your task\n" +
                        "Please enter a description for your task\n" +
                        "Please enter a priority for your task\n" +
                        "\u001B[1;34m1 - LOW, \u001B[1;33m2 - MEDIUM, \u001B[1;31m3 - HIGH\u001B[0m\n" +
                        "Add a Bucket to group your tasks\n" +
                        "\u001B[1;32mTask Created Successfully!\u001B[0m\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("Title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void editNotFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "MyList\nDescription\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            ToDoList toDoList = new ToDoList("MyList");
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            Main.edit(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Editing task at index 0:\n" +
                    "⏭\uFE0F Test\n" +
                    "Test\n" +
                    "<\u001B[1;36mTest\u001B[0m> \u001B[1;34mLOW\u001B[0m\n" +
                    "Enter new Title or press enter to skip\n" +
                    "Enter new Description or press enter to skip\n" +
                    "Enter new Priority or press enter to skip\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Enter new Bucket or press enter to skip\n" +
                    "Task Edited Successfully!\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("MyList");
            assertThat(toDoList.getListToDos().get(0).getDescription()).isEqualTo("Description");
            assertThat(toDoList.getListToDos().get(0).getPriority()).isEqualTo(Priority.HIGH);
            assertThat(toDoList.getListToDos().get(0).getBucket()).isEqualTo("Tag");
            assertThat(env).isNotNull();

        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void editFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "MyList\nDescription\n3\nTag\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            ToDoList toDoList = new ToDoList("MyList", "editTestFile");
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            Main.edit(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Editing task at index 0:\n" +
                    "⏭\uFE0F Test\n" +
                    "Test\n" +
                    "<\u001B[1;36mTest\u001B[0m> \u001B[1;34mLOW\u001B[0m\n" +
                    "Enter new Title or press enter to skip\n" +
                    "Enter new Description or press enter to skip\n" +
                    "Enter new Priority or press enter to skip\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Enter new Bucket or press enter to skip\n" +
                    "Task Edited Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("MyList");
            assertThat(toDoList.getListToDos().get(0).getDescription()).isEqualTo("Description");
            assertThat(toDoList.getListToDos().get(0).getPriority()).isEqualTo(Priority.HIGH);
            assertThat(toDoList.getListToDos().get(0).getBucket()).isEqualTo("Bucket");
            assertThat(env).isNotNull();

        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void listTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        toDoItems.add(new ToDoItem("Test2", "Test2", "Test2", Priority.LOW));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setListToDos(toDoItems);

        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.list(toDoList);
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    toDoList.getListToDos().get(0).toString() + "\n" +
                    toDoList.getListToDos().get(1).toString() + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void listEmptyTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ToDoList toDoList = new ToDoList("MyList");
        try {
            String userInput = "MyList\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.list(toDoList);
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    "👀Looks Empty here... Add some tasks!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void removeFileNotSpecifiedTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        java.util.ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        toDoItems.add(new ToDoItem("Test2", "Test2", "Test2", Priority.LOW));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setListToDos(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getListToDos().size()).isEqualTo(2);
            Main.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Task Removed Successfully!\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().size()).isEqualTo(1);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("Test2");
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void removeFileSpecifiedTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        java.util.ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        toDoItems.add(new ToDoItem("Test2", "Test2", "Test2", Priority.LOW));

        ToDoList toDoList = new ToDoList("MyList", "removeTestFile");
        toDoList.setListToDos(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getListToDos().size()).isEqualTo(2);
            Main.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().size()).isEqualTo(1);
            assertThat(toDoList.getListToDos().get(0).getTitle()).isEqualTo("Test2");
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void successTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.success("greatsuccess");
            String expectedOutput;
            expectedOutput = ConsoleColors.GREEN_BOLD + "greatsuccess" + ConsoleColors.RESET +"\n";
            String actualOutput = outBuffer.toString();
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
            // funktion Main.success aufrufen
            Main.error("nogreatsuccess");
            String expectedOutput;
            // Output den du erwartest
            expectedOutput = ConsoleColors.RED_BOLD + "nogreatsuccess" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);

        }
    }
    @Test
    void handleBadIndexTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream("y\n1\n".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            int index = Main.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Test Message.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(1);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
        try {
            System.setIn(new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            int index = Main.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Okay, I'll leave you alone then. 👋\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(-1);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void doneTest() {
        ToDoList list = new ToDoList("MyList", "listTest.json");
        list.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(list.getListToDos().get(0).isDone()).isFalse();
        Main.done(list, 0);
        assertThat(list.getListToDos().get(0).isDone()).isTrue();
    }

    @Test
    void sortHelpTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.sortHelp();
            String expectedOutput;
            expectedOutput =
                    "gtd sort [option]\n" +
                    "Options:\n" +
                    "  priority        - sort by priority\n" +
                    "  createdAt       - sort by creation date\n" +
                    "  dueDate         - sort by due date\n" +
                    "  bucket [Bucket] - sort by bucket\n" +
                    "  title           - sort by title\n" +
                    "  done            - sort by done\n" +
                    "  help            - print this help\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void handleSortTest() {
        String[] commandArray = {"gtd", "sort", "prio", "asc"};
        ToDoList list = new ToDoList("MyList");
        list.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        list.getListToDos().get(0).setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0));
        list.add(new ToDoItem("Cucumber", "Water", "Vegetable", Priority.LOW));
        list.getListToDos().get(1).setCreatedAt(LocalDateTime.of(2020, 1, 2, 0, 0));
        list.add(new ToDoItem("Banana", "Minions", "Fruit", Priority.HIGH));

        // Priority Test
        list.sortByPriority("asc");
        assertThat(list.getListToDos().get(0).getTitle()).isEqualTo("Cucumber");
        list.sortByPriority("desc");
        assertThat(list.getListToDos().get(0).getTitle()).isEqualTo("Banana");
        list.sortByCreatedAt("asc");
        assertThat(list.getListToDos().get(0).getTitle()).isEqualTo("Apple");
        list.sortByCreatedAt("desc");
        assertThat(list.getListToDos().get(0).getTitle()).isEqualTo("Banana");
        list.bubbleUpBucket(commandArray[3]);
        assertThat(list.getListToDos().get(0).getTitle()).isEqualTo("Banana");
    }

    @Test
    void clearTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList list = new ToDoList("MyList");
        list.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(list.getListToDos().size()).isEqualTo(1);
            Main.clear(list);
            assertThat(list.getListToDos() == null).isTrue();
        } finally {
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void exitTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList list = new ToDoList("MyList");
        list.setFileName("listTest.json");
        list.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.exit(list);
            // Check the program output
            String expectedOutput;
            expectedOutput = "exiting...\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            ToDoList testList = new Program().loadList("listTest.json");
            assertThat(testList.getListToDos().get(0).getTitle()).isEqualTo("Apple");
        } finally {
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void createBucketsTest() {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList list = new ToDoList("My List");
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.add(list);
            List<Bucket> TestBuckets = list.getBuckets();

            assertThat(TestBuckets.get(0)).isEqualTo("Bucket");
        }finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }

    }

    @Test
    void showBucketsTest() {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList list = new ToDoList("My List");
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.add(list);
            String userInput2 = "Title\nDescription\n12.12.12\n3\nBucket2\n";
            System.setIn(new ByteArrayInputStream(userInput2.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer2));
            Main.add(list);
            String userInput3 = "Title\nDescription\n12.12.12\n3\nBucket3\n";
            System.setIn(new ByteArrayInputStream(userInput3.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer3 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer3));
            Main.add(list);
            List<Bucket> TestBuckets = list.getBuckets();
            assertThat(TestBuckets).isEqualTo(list.getBuckets());
        }finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }

    }

    @Test
    void editBucketTest() {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList list = new ToDoList("My List");
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            list.addBucket("Bucket");
            Main.add(list);
            list.editBucket(0, "Test");
            assertThat(list.getBuckets().get(0).getBucket()).isEqualTo("Test");
        } finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }
    }
}
