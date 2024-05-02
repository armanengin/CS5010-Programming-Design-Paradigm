package assignment5.concurrentSolution;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * An abstract producer class that reads records from a CSV file and places them into a shared
 * blocking queue for processing. This class is designed to be run on a separate thread
 * as part of a producer-consumer pattern implementation for concurrent data processing.
 */
public abstract class OULADProducer implements Runnable {

  /**
   * Delimiter used in the CSV files.
   */
  private static final String CSV_DELIMITER = ",";
  /**
   * Path to the CSV file to be read.
   */
  protected String csvFilePath;
  /**
   * The queue into which parsed records are placed for further processing.
   */
  protected BlockingQueue<List<String>> queue;

  /**
   * Constructs a new {@code OULADProducer} with the specified CSV file path and blocking queue.
   *
   * @param csvFilePath the path to the CSV file to be read.
   * @param queue       the queue into which parsed records are placed.
   */
  public OULADProducer(String csvFilePath, BlockingQueue<List<String>> queue) {
    this.csvFilePath = csvFilePath;
    this.queue = queue;
  }

  /**
   * Reads records from the CSV file, converts them to a list of strings, and places them into
   * the blocking queue. Once all records have been read, a stop signal (an empty list) is placed
   * into the queue to indicate completion.
   */
  @Override
  public void run() {
    try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        CSVParser csvParser = new CSVParser(reader,
            CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true)
                .setDelimiter(CSV_DELIMITER)
                .build())) {
      for (CSVRecord record : csvParser) {
        queue.put(record.toList());
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    } finally {
      List<String> stopSignal = new ArrayList<>();
      try {
        queue.put(stopSignal);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Compares this producer to another object for equality. Two producers are considered equal
   * if they have the same CSV file path and use the same queue.
   *
   * @param o the object to be compared for equality with this producer.
   * @return {@code true} if the specified object is equal to this producer; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OULADProducer that)) {
      return false;
    }
    return Objects.equals(csvFilePath, that.csvFilePath) && Objects.equals(queue,
        that.queue);
  }

  /**
   * Returns the hash code value for this producer. The hash code is computed based on
   * the CSV file path and queue.
   *
   * @return the hash code value for this producer.
   */
  @Override
  public int hashCode() {
    return Objects.hash(csvFilePath, queue);
  }

  /**
   * Returns a string representation of this producer, including the class name, CSV file path,
   * and queue reference.
   *
   * @return a string representation of this producer.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("{");
    sb.append("csvFilePath='").append(csvFilePath).append('\'');
    sb.append(", queue=").append(queue);
    sb.append('}');
    return sb.toString();
  }
}