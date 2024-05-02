package assignment5.concurrentSolution;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Reads student activity data from a CSV file and produces records for processing.
 * This class extends the OULADProducer abstract class and is responsible for reading
 * student activity data from a specified CSV file. Each record read from the file is
 * converted into a list of strings and then placed onto a blocking queue for further
 * processing by consumer threads.
 */
public class StudentActivityProducer extends OULADProducer {

  /**
   * Constructs a new StudentActivityProducer with a specified CSV file path and a queue.
   *
   * @param csvFilePath The path to the CSV file containing student activity data.
   * @param queue The blocking queue onto which the produced records will be placed.
   */
  public StudentActivityProducer(String csvFilePath, BlockingQueue<List<String>> queue) {
    super(csvFilePath, queue);
  }
}
