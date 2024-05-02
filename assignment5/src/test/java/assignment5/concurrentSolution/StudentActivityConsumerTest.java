package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentActivityConsumerTest {
  private StudentActivityConsumer consumer;
  private ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap;

  @BeforeEach
  void setUp() {
    BlockingQueue<List<String>> mockQueue = mock(BlockingQueue.class);
    summaryDataMap = new ConcurrentHashMap<>();
    consumer = new StudentActivityConsumer(mockQueue, summaryDataMap);
  }

  @Test
  void processRecord_AddsNewKeyToSummaryDataMap() {
    consumer.processRecord(List.of("CS101", "2021F", "1", "1", "7", "89"));
    assertTrue(summaryDataMap.containsKey("CS101_2021F"), "SummaryDataMap should contain key 'CS101_2021F'.");
  }

  @Test
  void processRecord_CreatesDateClicksMapWithCorrectDate() {
    consumer.processRecord(List.of("CS101", "2021F", "1", "1", "7", "89"));
    Map<Integer, Integer> dateClicksMap = summaryDataMap.get("CS101_2021F");
    assertTrue(dateClicksMap.containsKey(7), "DateClicksMap should contain date '7'.");
  }

  @Test
  void processRecord_UpdatesClicksForDateCorrectly() {
    consumer.processRecord(List.of("CS101", "2021F", "1", "1", "7", "89"));
    Map<Integer, Integer> dateClicksMap = summaryDataMap.get("CS101_2021F");
    assertEquals(89, dateClicksMap.get(7), "DateClicksMap should record 89 clicks for date '7'.");
  }

  @Test
  void processRecord_UpdatesSummaryDataMapSizeCorrectly() {
    consumer.processRecord(List.of("CS101", "2021F", "1", "1", "7", "89"));
    assertEquals(1, summaryDataMap.size(), "SummaryDataMap should contain 1 entry.");
  }

  @Test
  void toString_StartsWithExpectedString() {
    String expectedStart = "StudentActivityConsumer{queue=";
    assertTrue(consumer.toString().startsWith(expectedStart), "toString should start with the expected string.");
  }
}