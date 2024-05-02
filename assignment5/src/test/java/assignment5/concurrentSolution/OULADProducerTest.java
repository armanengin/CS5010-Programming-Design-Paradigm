package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OULADProducerTest {

  private static class TestableOULADProducer extends OULADProducer {

    public TestableOULADProducer(String csvFilePath, BlockingQueue<List<String>> queue) {
      super(csvFilePath, queue);
    }
  }

  private TestableOULADProducer producer;
  @Mock
  private BlockingQueue<List<String>> mockQueue;

  private Path csvFilePath = Paths.get(
      "src" + File.separator + "test" + File.separator + "resources" + File.separator
          + "StudentVle.csv");

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    producer = new TestableOULADProducer(csvFilePath.toString(), mockQueue);
  }

  @Test
  void run_PutsRecordsIntoQueue() throws InterruptedException {
    String s = csvFilePath.toAbsolutePath().toString();

    producer.run();

    verify(mockQueue, times(2085)).put(any(List.class));
  }

  @Test
  void run_PutsStopSignalIntoQueue() throws InterruptedException {
    producer.run();

    ArgumentCaptor<List<String>> argumentCaptor = ArgumentCaptor.forClass(List.class);
    verify(mockQueue, times(2085)).put(argumentCaptor.capture());

    assertFalse(argumentCaptor.getAllValues().get(2).isEmpty(),
        "The stop signal should be an empty list.");
  }

  @Test
  void equals_ReturnsTrueForSameObject() {
    assertEquals(producer, producer, "equals should return true for the same object");
  }

  @Test
  void equals_ReturnsTrueForEqualObjects() {
    OULADProducer otherProducer = new TestableOULADProducer(csvFilePath.toString(), mockQueue);
    assertEquals(producer, otherProducer,
        "equals should return true for two equal OULADProducer objects");
  }

  @Test
  void equals_ReturnsFalseForDifferentObjects() {
    OULADProducer otherProducer = new TestableOULADProducer("different/path/to/file.csv",
        mockQueue);
    assertNotEquals(producer, otherProducer,
        "equals should return false for two different OULADProducer objects");
  }

  @Test
  void hashCode_IsConsistent() {
    assertEquals(producer.hashCode(), producer.hashCode(),
        "hashCode should be consistent and return the same value when called multiple times");
  }

  @Test
  void hashCode_EqualsForEqualObjects() {
    OULADProducer otherProducer = new TestableOULADProducer(csvFilePath.toString(), mockQueue);
    assertEquals(producer.hashCode(), otherProducer.hashCode(),
        "hashCode should be equal for two equal OULADProducer objects");
  }

  @Test
  void toString_ContainsClassName() {
    assertTrue(producer.toString().startsWith("TestableOULADProducer{"),
        "toString should start with the class name");
  }

  @Test
  void toString_ContainsCsvFilePath() {
    assertTrue(producer.toString().contains(csvFilePath.toString()),
        "toString should contain the csvFilePath");
  }

  @Test
  void toString_ContainsQueueReference() {
    assertTrue(producer.toString().contains("queue="),
        "toString should contain reference to 'queue'");
  }
}