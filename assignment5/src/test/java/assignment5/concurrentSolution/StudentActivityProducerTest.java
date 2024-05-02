package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;

class StudentActivityProducerTest {

  private StudentActivityProducer producer;
  private BlockingQueue<List<String>> mockQueue;

  @TempDir
  Path tempDir;

  @BeforeEach
  void setUp() throws Exception {
    mockQueue = mock(BlockingQueue.class);
    Path csvFilePath = tempDir.resolve("studentActivity.csv");
    // Example CSV content
    String csvContent = "code_module,code_presentation,id_student,id_site,date,sum_click\n" +
        "CS101,2021F,1,1,7,89\n" +
        "CS102,2021F,2,2,8,100\n";
    Files.writeString(csvFilePath, csvContent);
    producer = new StudentActivityProducer(csvFilePath.toString(), mockQueue);
  }

  @Test
  void run_PutsTwoRecordsIntoQueue() throws InterruptedException {
    producer.run();
    verify(mockQueue, times(3)).put(any());
  }

  @Test
  void run_FirstRecordIsCorrectlyFormatted() throws InterruptedException {
    producer.run();
    ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
    verify(mockQueue, times(3)).put(captor.capture());
    List<String> l = captor.getAllValues().get(0);
    assertEquals(List.of("CS101", "2021F", "1", "1", "7", "89"), captor.getAllValues().get(0), "First record should match expected values");
  }

  @Test
  void run_SecondRecordIsCorrectlyFormatted() throws InterruptedException {
    producer.run();
    ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
    verify(mockQueue, times(3)).put(captor.capture());

    assertEquals(List.of("CS102", "2021F", "2", "2", "8", "100"), captor.getAllValues().get(1), "Second record should match expected values");
  }

  @Test
  void toString_IncludesCsvFilePath() {
    String expected = producer.csvFilePath; // Accessing directly for simplicity in example
    assertTrue(producer.toString().contains(expected), "toString should include the csvFilePath.");
  }

  @Test
  void toString_IncludesQueueReference() {
    assertTrue(producer.toString().contains("queue="), "toString should include reference to 'queue'.");
  }
}