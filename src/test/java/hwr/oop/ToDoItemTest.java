package hwr.oop;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoItemTest {
    @Test
    void getLocalDateTest() {
        String result = ToDoItem.getLocalDate();
        System.out.println(result);
        assertThat(result).isEqualTo(LocalDate.now().toString());
    }
    @Test
    void getIdTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        int result = item.getId();
        assertThat(result).isEqualTo(0);
    }
    @Test
    void getTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }

    @Test
    void getDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc"+ "\nCreated " + ToDoItem.getLocalDate());
    }

    @Test
    void getTagTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        String result = item.getTag();
        assertThat(result).isEqualTo("testTag");
    }

    @Test
    void isDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        boolean result = item.isDone();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }
    @Test
    void getDeadline() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        LocalDate result = item.getDeadline();
        assertThat(result).isEqualTo(LocalDate.now());
    }
    @Test
    void setTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }
    @Test
    void setDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    void setDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        item.setDone(true);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(true);
    }
    @Test
    void setPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }
    @Test
    void setTagTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        item.setTag("Tag");
        String testTag = item.getTag();
        assertThat(testTag).isEqualTo("Tag");
    }
    @Test
    void toStringTest() {
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, LocalDate.now());
        String result = item.toString();
        System.out.println(result);
        assertThat(result).isEqualTo("❌ " + item.getTitle() + '\n' +
                item.getDescription() +  '\n' +
                "<" + item.getTag() + ">" + ' ' +
                item.getPriority());
    }
    @Test
    void getCreatedAtTest() {
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, LocalDate.now());
        String result = item.getCreatedAt();
        System.out.println(result);
        assertThat(item.getDescription()).contains(result);
    }
    @Test
    void setDeadlineTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW, LocalDate.now());
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        item.setDeadline(tomorrow);
        LocalDate testDeadline = item.getDeadline();
        assertThat(testDeadline).isEqualTo(tomorrow);
    }
}
