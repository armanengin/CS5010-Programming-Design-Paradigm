package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseProducerTest {

  private CourseProducer courseProducer;
  private BlockingQueue<List<String>> queue;
  private String result;

  @BeforeEach
  void setUp() {
    String csvFilePath = "path/to/sample.csv";
    queue = new LinkedBlockingQueue<>();
    courseProducer = new CourseProducer(csvFilePath, queue);
    result = courseProducer.toString();
  }

  @Test
  void toString_IncludesClassName() {
    assertTrue(result.startsWith("CourseProducer{"), "toString should start with 'CourseProducer{'");
  }

  @Test
  void toString_IncludesCsvFilePath() {
    assertTrue(result.contains("csvFilePath='path/to/sample.csv'"), "toString should include the 'csvFilePath'");
  }

  @Test
  void toString_IncludesQueueReference() {
    assertTrue(result.contains(", queue="), "toString should include reference to 'queue'");
  }
}