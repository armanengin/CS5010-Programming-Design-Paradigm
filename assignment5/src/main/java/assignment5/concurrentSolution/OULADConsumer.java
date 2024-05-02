package assignment5.concurrentSolution;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstract consumer class that processes records from a {@link BlockingQueue}. This class serves
 * as a base for specific types of consumers that process different kinds of data records, for
 * example, course information or student activity data. It implements {@link Runnable} to allow
 * consumers to run in separate threads.
 * <p>
 * Each consumer continuously takes records from the queue until it encounters an empty record,
 * which serves as a stop signal indicating that no more records will be added to the queue. Upon
 * processing a record, the consumer performs specific actions defined in the concrete
 * implementation of the {@link #processRecord(List)} method.
 */
public abstract class OULADConsumer implements Runnable {

  /**
   * The blocking queue from which records are consumed.
   */
  protected BlockingQueue<List<String>> queue;
  /**
   * A map to store processed summary data.
   */
  protected ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap;

  /**
   * Constructs a new OULADConsumer with the given queue and summary data map.
   *
   * @param queue          The queue from which records will be consumed.
   * @param summaryDataMap A map to store processed summary data.
   */
  public OULADConsumer(BlockingQueue<List<String>> queue,
      ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap) {
    this.queue = queue;
    this.summaryDataMap = summaryDataMap;
  }

  /**
   * Processes a single record taken from the queue. This method is abstract and should be
   * implemented by concrete subclasses to define custom processing logic.
   *
   * @param record A list of strings representing a single record.
   */

  protected abstract void processRecord(List<String> record);

  /**
   * The core logic for consuming and processing records. This method runs in a loop, continuously
   * taking records from the queue and processing them until an empty list is encountered, which
   * serves as a stop signal.
   * <p>
   * Subclasses must implement the {@link #processRecord(List)} method to define specific processing
   * behavior.
   */

  @Override
  public void run() {
    try {
      while (true) {
        List<String> record = queue.take();
        if (record.isEmpty()) {
          queue.put(record); // Propagate the stop signal to other consumers
          break;
        }
        processRecord(record);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Determines whether another object is equal to this {@code OULADConsumer}. The comparison is
   * based on the identity of the blocking queue and the summary data map.
   *
   * @param o the reference object with which to compare.
   * @return {@code true} if this object is the same as the {@code o} argument; {@code false}
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OULADConsumer that)) {
      return false;
    }
    return Objects.equals(queue, that.queue) && Objects.equals(summaryDataMap,
        that.summaryDataMap);
  }

  /**
   * Returns a hash code value for this {@code OULADConsumer}. This method is supported for the
   * benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(queue, summaryDataMap);
  }

  /**
   * Returns a string representation of this {@code OULADConsumer} object. The string representation
   * consists of the class name, the hash code of the object, and a listing of the queue and summary
   * data map contents.
   *
   * @return a string representation of this object.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("{");
    sb.append("queue=").append(queue);
    sb.append(", summaryDataMap=").append(summaryDataMap);
    sb.append('}');
    return sb.toString();
  }
}
