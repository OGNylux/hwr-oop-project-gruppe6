package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {
    @Test
    void setTitle() {
        Project project = new Project("");
        project.setTitle("testTitle");
        String testName = project.getTitle();
        assertThat(testName).isEqualTo("testTitle");
    }

    @Test
    void getTitle() {
        Project project = new Project("testTitle");
        String testName = project.getTitle();
        assertThat(testName).isEqualTo("testTitle");
    }
}
