package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static hwr.oop.Workflow.createWorkflow;
import static org.assertj.core.api.Assertions.assertThat;

class workflow {
    @Test
     void getEstimatedTime(){
        List list = new List("myList");
        ToDoItem item = new ToDoItem ("Finish OOP Project","Finish the workflow task", "Uni", false, Priority.HIGH, EstimatedTime.LONG, new Project(""));
        list.add(item);
        ToDoItem.getEstimatedTime();
        assertThat(ToDoItem.getEstimatedTime()).isEqualTo(EstimatedTime.LONG);
    }

    @Test
     void createWorkflowShort () {
        List list = new List("testList");
        ToDoItem item = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.XLONG, new Project(("")));
        ToDoItem item1 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.LONG, new Project(("")));
        ToDoItem item2 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item3 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item4 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        ToDoItem item5 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        Workflow workflow1 = new Workflow();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader());
        createWorkflow(inputReader);
        String expectedOutput = "Learn for the exams\n" + "Learn for the exams\n";
        assertThat(expectedOutput).isEqualTo(createWorkflow(1););


    }

    @Test
    void createWorkflowMedium () {
        List list = new List("testList");
        ToDoItem item = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.XLONG, new Project(("")));
        ToDoItem item1 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.LONG, new Project(("")));
        ToDoItem item2 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item3 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item4 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        ToDoItem item5 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        String expectedOutput = "Learn for the exams\n" + "Learn for the exams\n";
        assertThat(expectedOutput).isEqualTo(createWorkflow(2););
    }
    @Test
    void createWorkflowLong () {
        List list = new List("testList");
        ToDoItem item = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.XLONG, new Project(("")));
        ToDoItem item1 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.LONG, new Project(("")));
        ToDoItem item2 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item3 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item4 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        ToDoItem item5 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        String expectedOutput = "Learn for the exams\n" + "Learn for the exams\n";
        assertThat(expectedOutput).isEqualTo(createWorkflow(3););
    }
    @Test
    void createWorkflowXlong () {
        List list = new List("testList");
        ToDoItem item = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.XLONG, new Project(("")));
        ToDoItem item1 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.LONG, new Project(("")));
        ToDoItem item2 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item3 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.MEDIUM, new Project(("")));
        ToDoItem item4 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        ToDoItem item5 = new ToDoItem("Learn for the exams", "Do all math worksheets", "UNI", false, Priority.HIGH, EstimatedTime.SHORT, new Project(("")));
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
    }
}
