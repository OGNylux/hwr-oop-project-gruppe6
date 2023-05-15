package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        list.add(item);
        java.util.ArrayList<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void removeTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        list.add(item);
        list.remove(0);
        java.util.ArrayList<ToDoItem> itemList = new ArrayList<>();
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityAscTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("asc");
        java.util.ArrayList<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item3);
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityDescTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("desc");
        java.util.ArrayList<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.bubbleUpBucket("Personal");
        java.util.ArrayList<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item);
        itemList.add(item2);
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        java.util.ArrayList<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.set(2, list.getListToDos().get(0));
        sortedExpected.set(1, list.getListToDos().get(2));
        sortedExpected.set(0, list.getListToDos().get(1));
        list.sortByCreatedAt("asc");
        assertThat(list.getListToDos()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByCreatedAtDescTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        java.util.ArrayList<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.set(2, list.getListToDos().get(1));
        sortedExpected.set(1, list.getListToDos().get(2));
        sortedExpected.set(0, list.getListToDos().get(0));
        list.sortByCreatedAt("desc");
        assertThat(list.getListToDos()).isEqualTo(sortedExpected);
    }

    @Test
    void getBucketsTest() {
        List list = new List("myList");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH));
        list.addBucket("Uni");
        java.util.ArrayList<Bucket> testbuckets;
        testbuckets = new ArrayList<>();
        testbuckets = list.getBuckets();
        assertThat(testbuckets.get(0).getBucket()).isEqualTo("Uni");
    }
    /*@Test
    void updateBucketsTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        //list.updateBuckets();
        java.util.List<Bucket> testbuckets = list.getBuckets();
        assertThat(testbuckets.get(0).getBucket()).isEqualTo("Uni");
        assertThat(testbuckets.get(1).getBucket()).isEqualTo("Math");
        assertThat(testbuckets.get(2).getBucket()).isEqualTo("Personal");
    }*/


    @Test
    void addBucketTest() {
        List list =  new List("myList");
        list.addBucket("Test");
        assertThat(list.getBuckets().get(0).getBucket()).isEqualTo("Test");
    }

    @Test
    void editBucketTest() {
        List list = new List("myList");
        list.addBucket("Test");
        list.editBucket(0, "Boo");
        assertThat(list.getBuckets().get(0).getBucket()).isEqualTo("Boo");
    }
}
