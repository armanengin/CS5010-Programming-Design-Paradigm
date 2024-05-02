package assignment5.concurrentSolution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

/**
 * A utility class for processing CSV files related to online learning data.
 * This class provides functionalities to generate summary files based on the student activities
 * and high activity files based on a specified activity threshold.
 */
public final class OULADFileProcessor {

  /**
   * Private constructor to prevent instantiation.
   */
  private OULADFileProcessor() {
  }

  /**
   * Delimiter used in CSV files.
   */
  private static final String CSV_DELIMITER = ",";
  /**
   * Format extension for output files.
   */
  private static final String OUTPUT_FILE_FORMAT = ".csv";
  /**
   * Header for the "date" column in output CSV files.
   */
  private static final String OUTPUT_FILE_HEADER_DATE = "date";
  /**
   * Header for the "total clicks" column in output CSV files.
   */
  private static final String OUTPUT_FILE_HEADER_TOTAL_CLICKS = "total_clicks";
  /**
   * Header for the "module_presentation" column in high activity output CSV files.
   */
  private static final String OUTPUT_FILE_HEADER_MODULE_PRESENTATION = "module_presentation";
  /**
   * Prefix for the filename of high activity output CSV files.
   */
  private static final String ACTIVITY_FILE_PREFIX = "activity-";

  /**
   * Generates summary CSV files for each course-module presentation pair. Each file contains
   * the dates and total clicks for activities that meet or exceed a specified threshold.
   * High activity records are also added to a provided list for further processing.
   *
   * @param summaryData    A map containing the summary data to be written to the file.
   *                       The key is the course-module presentation identifier and the value is
   *                       another map with date as key and total clicks as value.
   * @param outputDirectory The directory where the output files will be saved.
   * @param highActivities  A list to store high activity records for later use.
   * @param threshold      The minimum number of clicks to be considered as high activity.
   */
  static void generateSummaryFiles(Map<String, Map<Integer, Integer>> summaryData,
      String outputDirectory, List<List<String>> highActivities, int threshold) {

    Path outputDirPath = Paths.get(outputDirectory);
    if (!Files.exists(outputDirPath)) {
      try {
        Files.createDirectories(outputDirPath);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    for (Map.Entry<String, Map<Integer, Integer>> entry : summaryData.entrySet()) {
      String fileName = entry.getKey() + OUTPUT_FILE_FORMAT;
      String filePath = outputDirectory + File.separator + fileName;

      try (FileWriter fileWriter = new FileWriter(filePath);
          CSVPrinter csvPrinter = new CSVPrinter(fileWriter,
              CSVFormat.DEFAULT.builder()
                  .setHeader(OUTPUT_FILE_HEADER_DATE, OUTPUT_FILE_HEADER_TOTAL_CLICKS)
                  .setDelimiter(CSV_DELIMITER)
                  .setQuoteMode(QuoteMode.ALL).build())) {

        for (Map.Entry<Integer, Integer> dateClickEntry : entry.getValue().entrySet()) {
          if (dateClickEntry.getValue() >= threshold) {
            List<String> highActivity = new ArrayList<>();
            highActivity.add(entry.getKey());
            highActivity.add(dateClickEntry.getKey().toString());
            highActivity.add(dateClickEntry.getValue().toString());
            highActivities.add(highActivity);
          }

          csvPrinter.printRecord(dateClickEntry.getKey(), dateClickEntry.getValue());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Generates a high activity CSV file containing records that meet or exceed the specified
   * activity threshold. The file is named using the threshold value and saved in the specified
   * directory.
   *
   * @param highActivities  A list containing the high activity records. Each record is a list
   *                        containing the module presentation identifier, date, and total clicks.
   * @param outputDirectory The directory where the high activity file will be saved.
   * @param threshold      The activity threshold used to filter high activity records.
   */
  static void generateHighActivityFile(List<List<String>> highActivities, String outputDirectory,
      int threshold) {
    String fileName = ACTIVITY_FILE_PREFIX + threshold + OUTPUT_FILE_FORMAT;
    String filePath = outputDirectory + File.separator + fileName;

    try (FileWriter fileWriter = new FileWriter(filePath);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter,
            CSVFormat.DEFAULT.builder()
                .setHeader(OUTPUT_FILE_HEADER_MODULE_PRESENTATION, OUTPUT_FILE_HEADER_DATE,
                    OUTPUT_FILE_HEADER_TOTAL_CLICKS)
                .setDelimiter(CSV_DELIMITER).setQuoteMode(QuoteMode.ALL).build())) {

      for (List<String> highActivity : highActivities) {
        csvPrinter.printRecord(highActivity);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}