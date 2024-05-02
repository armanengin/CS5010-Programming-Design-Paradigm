package assignment5.concurrentSolution;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Produces course records by reading from a CSV file and placing them into a queue.
 * This class extends {@link OULADProducer} to specifically handle reading course
 * data from a CSV file and enqueueing the records for further processing.
 */
public class CourseProducer extends OULADProducer {
  /**
   * Constructs a new {@code CourseProducer} with the specified CSV file path and queue.
   * The CSV file at the given path is read, and each record is enqueued for processing.
   *
   * @param csvFilePath The file path of the CSV file containing course data.
   * @param queue The queue into which course records are placed after being read from the CSV file.
   */
  public CourseProducer(String csvFilePath, BlockingQueue<List<String>> queue) {
    super(csvFilePath, queue);
  }
}
