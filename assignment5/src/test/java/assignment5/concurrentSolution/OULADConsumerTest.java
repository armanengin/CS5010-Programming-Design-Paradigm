package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OULADConsumerTest {

  private static class DummyOULADConsumer extends OULADConsumer {

    public DummyOULADConsumer(BlockingQueue<List<String>> queue,
        ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap) {
      super(queue, summaryDataMap);
    }

    @Override
    protected void processRecord(List<String> record) {
      // Minimal implementation for testing
    }
  }

  private DummyOULADConsumer consumer;
  private BlockingQueue<List<String>> queue;
  private ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap;

  @BeforeEach
  void setUp() {
    queue = new LinkedBlockingQueue<>();
    summaryDataMap = new ConcurrentHashMap<>();
    consumer = new DummyOULADConsumer(queue, summaryDataMap);
  }

  @Test
  void run_ConsumesUntilEmptyRecord() throws InterruptedException {
    List<String> stopSignal = Arrays.asList(); // Use an empty list as a stop signal
    queue.put(Arrays.asList("record1"));
    queue.put(stopSignal);

    Thread thread = new Thread(consumer);
    thread.start();
    thread.join();

    assertTrue(queue.contains(stopSignal),
        "Queue should contain the stop signal after consumer stops.");
  }

  @Test
  void equals_SameObject_ReturnsTrue() {
    assertEquals(consumer, consumer, "Consumer should be equal to itself.");
  }

  @Test
  void equals_DifferentObjectWithSameState_ReturnsTrue() {
    DummyOULADConsumer anotherConsumer = new DummyOULADConsumer(queue, summaryDataMap);
    assertEquals(consumer, anotherConsumer, "Two consumers with the same state should be equal.");
  }

  @Test
  void hashCode_SameStateObjects_ReturnSameHashCode() {
    DummyOULADConsumer anotherConsumer = new DummyOULADConsumer(queue, summaryDataMap);
    assertEquals(consumer.hashCode(), anotherConsumer.hashCode(),
        "Two consumers with the same state should have the same hashCode.");
  }

  @Test
  void toString_ReturnsCorrectFormat() {
    String expectedStart = "DummyOULADConsumer{queue=";
    assertTrue(consumer.toString().startsWith(expectedStart),
        "toString should start with the correct format.");
  }

  @Test
  void testEquals() {
    assertNotEquals(consumer, null);
    assertNotEquals(consumer, new Object());
  }
}