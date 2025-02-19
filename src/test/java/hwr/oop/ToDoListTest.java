package hwr.oop;

import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static hwr.oop.ToDoList.linkToCorrectBucket;
import static org.assertj.core.api.Assertions.assertThat;

class ToDoListTest {
    @Test
    void setNameTest() {
        ToDoList list = new ToDoList("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void getNameTest() {
        ToDoList list = new ToDoList("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void getBucketsTest() {
        ToDoList list = new ToDoList("myList");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT));
        list.addBucket(new Bucket("Uni"));
        Set<Bucket> testBuckets = list.getBuckets();
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(testBuckets, 0)).getBucketName()).isEqualTo("Uni");
    }

    @Test
    void setItemTest() {
        ToDoList list = new ToDoList("TestSetItem");
        list.add(new ToDoItem("t","b",new Bucket("t"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        ToDoItem item = new ToDoItem("b","a",new Bucket("a"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT);
        List<ToDoItem> items = new ArrayList<>();
        items.add(item);
        list.setItems(items);
        assertThat(list.getItems()).isEqualTo(items);
    }

    @Test
    void addTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void getItemsTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ArrayList<ToDoItem> itemList = new ArrayList<>();
        list.add(item);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void findBucketTest() {
        ToDoList list = new ToDoList("myList");
        list.addBucket(new Bucket("Test1"));
        list.addBucket(new Bucket("Test2"));
        assertThat(list.findBucket("Test1").getBucketName()).isEqualTo("Test1");
    }

    @Test
    void findBucketNullTest() {
        ToDoList list = new ToDoList("myList");
        assertThat(list.findBucket("Test")).isNull();
    }

    @Test
    void renameBucketTest() {
        ToDoList list = new ToDoList("Test");
        list.add(new ToDoItem("t","b",new Bucket("Hallo"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("a","b",new Bucket("Hallo"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("a","b",new Bucket("A"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        list.addBucket(new Bucket("Hello"));
        list.addBucket(new Bucket("Test"));
        list.renameBucket(0,"chai");
        assertThat(list.findBucket("chai").getBucketName()).isEqualTo("chai");
    }


    @Test
    void removeTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(new ToDoItem("t","t",new Bucket("d"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        list.remove(1);
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void addBucketTest() {
        ToDoList list =  new ToDoList("myList");
        list.addBucket(new Bucket("Test"));
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(list.getBuckets(), 0)).getBucketName()).isEqualTo("Test");
    }

    @Test
    void editBucketTest() {
        ToDoList list = new ToDoList("myList");
        list.addBucket(new Bucket("Test"));
        list.renameBucket(0, "Boo");
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(list.getBuckets(), 0)).getBucketName()).isEqualTo("Boo");
    }

    @Test
    void sortByPriorityAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByPriorityDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.bubbleUpBucket("Math");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketSameTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Uni"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item2);
        list.add(item);
        list.add(item3);
        list.bubbleUpBucket("Math");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        ToDoList list = new ToDoList("sortByCreatedAtTest");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT));
        list.getItems().get(0).setCreatedAt(LocalDate.now().minusDays(2));
        list.getItems().get(1).setCreatedAt(LocalDate.now().minusDays(5));
        list.getItems().get(2).setCreatedAt(LocalDate.now().minusDays(1));
        List<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.add(list.getItems().get(1));
        sortedExpected.add(list.getItems().get(0));
        sortedExpected.add(list.getItems().get(2));
        list.sortByCreatedAt("asc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByCreatedAtDescTest() {
        ToDoList list = new ToDoList("sortByCreatedAtTest");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT));
        list.add(new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT));
        list.getItems().get(0).setCreatedAt(LocalDate.now().minusDays(2));
        list.getItems().get(1).setCreatedAt(LocalDate.now().minusDays(5));
        list.getItems().get(2).setCreatedAt(LocalDate.now().minusDays(1));
        List<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.add(list.getItems().get(2));
        sortedExpected.add(list.getItems().get(0));
        sortedExpected.add(list.getItems().get(1));
        list.sortByCreatedAt("desc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByTitleAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByTitle("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByTitleDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByTitle("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByDoneAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        item2.setDone();
        list.sortByDone("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item3);
        itemList.add(item2);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByDoneDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now(), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        item2.setDone();
        list.sortByDone("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByDueDateAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now().plusDays(1), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now().plusDays(2), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByDueDate("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByDueDateDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now().plusDays(1), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now().plusDays(2), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByDueDate("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item);
        itemList.add(item2);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByEstimatedTimeAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now().plusDays(1), EstimatedTime.LONG);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now().plusDays(2), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByEstimatedTime("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByEstimatedTimeDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.now().plusDays(1), EstimatedTime.LONG);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.now(), EstimatedTime.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.now().plusDays(2), EstimatedTime.SHORT);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByEstimatedTime("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void pruneUnusedBucketsTest() {
        ToDoList testList = new ToDoList("TestList");
        testList.add(new ToDoItem("t","t",new Bucket("t"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("b","s",new Bucket("a"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("c","a",new Bucket("d"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("c","a",new Bucket("t"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("c","a",new Bucket("z"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("c","a",new Bucket("u"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.addBucket(new Bucket("e"));
        testList.pruneUnusedBuckets();
        Set<Bucket> expectedBucket = new HashSet<>();
        assertThat(testList.getBuckets()).isEqualTo(expectedBucket);
    }
    @Test
    void linkToCorrectBucketTest() {
        ToDoList testList = new ToDoList("TestList");
        testList.add(new ToDoItem("t","t",new Bucket("t"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("b","s",new Bucket("a"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        testList.add(new ToDoItem("b","s",new Bucket("a"),Priority.LOW,LocalDate.now(), EstimatedTime.SHORT));
        linkToCorrectBucket(testList);
        assertThat(testList.getBuckets().toString()).contains("🪣a").contains("🪣t");
    }
}
