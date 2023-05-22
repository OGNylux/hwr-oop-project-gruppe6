package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoListJsonTest {
    @Test
    public void listToJsonTest() {
        ToDoList assertList = new ToDoList("myList", "listTest");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        assertList.add(item);
        assertList.add(item2);
        assertList.add(item3);
        assertList.writeToJSON("listTest.mp3");
        try {
            StringBuilder jsonIn;
            try (FileReader reader = new FileReader("listTest.json")) {
                int character;
                jsonIn = new StringBuilder();
                while ((character = reader.read()) != -1) {
                    jsonIn.append((char) character);
                }
            }
            String testDate = item.getCreatedAt();
            String testDate2 = item2.getCreatedAt();
            String testDate3 = item3.getCreatedAt();
            assertThat(jsonIn.toString()).hasToString("{\"name\":\"myList\",\"listToDos\":[{\"title\":\"Finish Math homework\",\"description\":\"I need to do tasks 5 - 10b.\",\"bucket\":{\"bucket\":\"Uni\"},\"priority\":\"HIGH\",\"createdAt\":\""+ testDate +"\",\"state\":\"TODO\"},{\"title\":\"Calculate Something\",\"description\":\"More Math over here\",\"bucket\":{\"bucket\":\"Math\"},\"priority\":\"MEDIUM\",\"createdAt\":\"" + testDate2 + "\",\"state\":\"TODO\"},{\"title\":\"Be Amazing\",\"description\":\"Just Do It\",\"bucket\":{\"bucket\":\"Personal\"},\"priority\":\"LOW\",\"createdAt\":\"" + testDate3 + "\",\"state\":\"TODO\"}],\"fileName\":\"listTest\",\"buckets\":[]}");
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }
    }
    @Test
    public void jsonToListTest() {
        Program program = new Program();
        ToDoList assertList = program.loadList("listTest.ipynb");
        assertThat(assertList.getName()).isEqualTo("myList");
    }
}

