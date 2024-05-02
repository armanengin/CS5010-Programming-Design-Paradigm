package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseConsumerTest {

  private CourseConsumer courseConsumer;
  private BlockingQueue<List<String>> mockQueue;
  private ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap;

  @BeforeEach
  void setUp() {
    mockQueue = mock(BlockingQueue.class);
    summaryDataMap = new ConcurrentHashMap<>();
    courseConsumer = new CourseConsumer(mockQueue, summaryDataMap);
  }

  @Test
  void processRecord_ShouldAddRecordToSummaryDataMap() throws InterruptedException {
    List<String> record = Arrays.asList("CS101", "2021F");
    when(mockQueue.take()).thenReturn(record,
        new ArrayList<>()); // Simulate queue behavior with a single record and then termination

    Thread consumerThread = new Thread(() -> {
      try {
        courseConsumer.run();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    consumerThread.start();
    consumerThread.join(); // Ensure the thread finishes before assertion

    String expectedKey = "CS101_2021F";
    assertTrue(summaryDataMap.containsKey(expectedKey),
        "The summaryDataMap should contain the key for the processed record.");
  }

  @Test
  void toString_ShouldIncludeQueueRepresentation() {
    String actual = courseConsumer.toString();
    assertTrue(actual.contains("queue=Mock for BlockingQueue"),
        "The toString output should include the representation of the mock queue.");
  }

  @Test
  void toString_ShouldIncludeSummaryDataMapRepresentation() {
    String actual = courseConsumer.toString();
    assertTrue(actual.contains("summaryDataMap={}"),
        "The toString output should include the representation of the summaryDataMap.");
  }
}