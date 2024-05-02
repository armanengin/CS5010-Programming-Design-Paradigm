package assignment5.concurrentSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Consumes course records from a queue and processes them to summarize data. This class extends
 * {@link OULADConsumer} to specifically handle the consumption of course records, extracting
 * essential information and updating a summary data map.
 */
public class CourseConsumer extends OULADConsumer {

  /**
   * Index of the code module in the course record list.
   */
  private static final int CODE_MODULE_INDEX = 0;
  /**
   * Index of the code presentation in the course record list.
   */
  private static final int CODE_PRESENTATION_INDEX = 1;
  /**
   * Delimiter used in the output file name between code module and code presentation.
   */
  private static final String OUTPUT_FILE_DELIMITER = "_";

  /**
   * Constructs a new {@code CourseConsumer} object with a given queue and summary data map.
   *
   * @param queue          The queue from which course records are consumed.
   * @param summaryDataMap The concurrent map that is updated with the summary of processed data.
   */
  public CourseConsumer(BlockingQueue<List<String>> queue,
      ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap) {
    super(queue, summaryDataMap);
  }

  /**
   * Processes a single course record, extracting the code module and code presentation, and updates
   * the summary data map accordingly. If a record with the same key already exists in the summary
   * data map, it is left unchanged; otherwise, a new entry is created.
   *
   * @param record The course record to be processed, represented as a list of strings.
   */
  @Override
  protected void processRecord(List<String> record) {
    String codeModule = record.get(CODE_MODULE_INDEX);
    String codePresentation = record.get(CODE_PRESENTATION_INDEX);

    String key = codeModule + OUTPUT_FILE_DELIMITER + codePresentation;
    summaryDataMap.putIfAbsent(key, new ConcurrentHashMap<>());
  }
}
