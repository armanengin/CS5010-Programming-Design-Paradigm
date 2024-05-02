package assignment5.concurrentSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Consumes student activity records from a blocking queue and updates a summary data map. This
 * class extends the OULADConsumer abstract class, implementing the specific logic to process
 * student activity records.
 */
public class StudentActivityConsumer extends OULADConsumer {

  private static final int CODE_MODULE_INDEX = 0;
  private static final int CODE_PRESENTATION_INDEX = 1;
  private static final int DATE_INDEX = 4;
  private static final int SUM_CLICK_INDEX = 5;
  private static final String OUTPUT_FILE_DELIMITER = "_";

  /**
   * Constructs a new StudentActivityConsumer.
   *
   * @param queue          A blocking queue containing lists of strings, each list represents a
   *                       student activity record.
   * @param summaryDataMap A concurrent hash map used to store the summary of student activities.
   */
  public StudentActivityConsumer(BlockingQueue<List<String>> queue,
      ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap) {
    super(queue, summaryDataMap);
  }

  /**
   * Processes a single record from the blocking queue and updates the summary data map. Each record
   * is a list of strings where each string represents a column value from the student activity
   * data. The method extracts necessary information such as module code, presentation code, date,
   * and sum of clicks, and then updates the summary data map accordingly.
   *
   * @param record A list of strings representing a single record of student activity data.
   */
  @Override
  protected void processRecord(List<String> record) {
    String codeModule = record.get(CODE_MODULE_INDEX);
    String codePresentation = record.get(CODE_PRESENTATION_INDEX);
    int date = Integer.parseInt(record.get(DATE_INDEX));
    int sumClick = Integer.parseInt(record.get(SUM_CLICK_INDEX));

    String key = codeModule + OUTPUT_FILE_DELIMITER + codePresentation;
    summaryDataMap.putIfAbsent(key, new ConcurrentHashMap<>());
    summaryDataMap.get(key).merge(date, sumClick, Integer::sum);
  }
}
