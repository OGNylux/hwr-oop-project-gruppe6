package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketTest {
    @Test
    void setTitle() {
        Bucket bucket = new Bucket("");
        bucket.setBucket("testTitle");
        String testName = bucket.getBucket();
        assertThat(testName).isEqualTo("testTitle");
    }

    @Test
    void getTitle() {
        Bucket bucket = new Bucket("testTitle");
        String testName = bucket.getBucket();
        assertThat(testName).isEqualTo("testTitle");
    }
}
