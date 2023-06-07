package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListClassTest {

    @Test
    void setListName() {
        List list = new List("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void addTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, EstimatedTime.LONG, new Project(""));
        list.add(item);
        ToDoItem[] itemList = new ToDoItem[1];
        itemList[0] = item;
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void removeTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, EstimatedTime.LONG, new Project(""));
        list.add(item);
        list.remove(0);
        ToDoItem[] itemList = new ToDoItem[0];
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityAscTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, EstimatedTime.SHORT, new Project(""));
        ToDoItem item2 = new ToDoItem("Finish Math homework","I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM, EstimatedTime.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Finish Math homework","I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.LONG, new Project(""));
        ToDoItem item4 = new ToDoItem("Finish Math homework","I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.XLONG, new Project(""));
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.sortByPriority("asc");
        ToDoItem[] itemList = new ToDoItem[4];
        itemList[0] = item3;
        itemList[1] = item2;
        itemList[2] = item;
        itemList[3] = item4;
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityDescTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH, EstimatedTime.SHORT, new Project(""));
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM, EstimatedTime.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.LONG, new Project(""));
        ToDoItem item4 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.XLONG, new Project(""));
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.sortByPriority("desc");
        ToDoItem[] itemList = new ToDoItem[4];
        itemList[0] = item;
        itemList[1] = item2;
        itemList[2] = item3;
        itemList[3] = item4;
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void sortByEstimatedTimeDescTest(){
        List list = new List("mylist");
        ToDoItem item = new ToDoItem("Finish OOP Project", "implement workflow feature", "Uni", false, Priority.MEDIUM, EstimatedTime.SHORT, new Project(""));
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM, EstimatedTime.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.LONG, new Project(""));
        ToDoItem item4 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.XLONG, new Project(""));
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.sortByEstimatedTime("desc");
        ToDoItem[] itemList = new ToDoItem[4];
        itemList[0] = item4;
        itemList[1] = item3;
        itemList[2] = item2;
        itemList[3] = item;
    }
    @Test
    void sortByEstimatedTimeAscTest(){
        List list = new List("mylist");
        ToDoItem item = new ToDoItem("Finish OOP Project", "implement worflow feature", "Uni", false, Priority.MEDIUM, EstimatedTime.SHORT, new Project(""));
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM, EstimatedTime.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.LONG, new Project(""));
        ToDoItem item4 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW, EstimatedTime.XLONG, new Project(""));
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.sortByEstimatedTime("desc");
        ToDoItem[] itemList = new ToDoItem[4];
        itemList[0] = item;
        itemList[1] = item2;
        itemList[2] = item3;
        itemList[3] = item4;
    }
    @Test
    void bubbleUpTagTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH, EstimatedTime.SHORT, new Project(""));
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM, EstimatedTime.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW, EstimatedTime.LONG, new Project(""));
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.bubbleUpTag("Personal");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item3;
        itemList[1] = item;
        itemList[2] = item2;
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[0] = list.getListToDos()[0];
        sortedExpected[1] = list.getListToDos()[2];
        sortedExpected[2] = list.getListToDos()[1];
        list.sortByCreatedAt("asc");
        assertThat(list.getListToDos()).isEqualTo(sortedExpected);
    }

    @Test // not needed anymore
    void startWorkflow(){
        List list = new List("myList");
        ToDoItem item = new ToDoItem ("do math tasks", "Finish the latest worksheet", "Uni", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(""));

    }

    @Test
    void sortByCreatedAtDescTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[2] = list.getListToDos()[0];
        sortedExpected[1] = list.getListToDos()[2];
        sortedExpected[0] = list.getListToDos()[1];
        list.sortByCreatedAt("desc");
        assertThat(list.getListToDos()).isEqualTo(sortedExpected);
    }
}
