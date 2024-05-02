package assignment5.sequentialSolution;

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
 * A Utility class that provides methods to read course and student activity data from OULAD CSV
 * files, process the data, and generate summary files based on the processed data.
 */
public final class OULADFileProcessor {

  private static final String CODE_MODULE = "code_module";
  private static final String CODE_PRESENTATION = "code_presentation";
  private static final String MODULE_PRESENTATION_LENGTH = "module_presentation_length";
  private static final String ID_STUDENT = "id_student";
  private static final String ID_SITE = "id_site";
  private static final String DATE = "date";
  private static final String SUM_CLICK = "sum_click";
  private static final String CSV_DELIMITER = ",";
  private static final String OUTPUT_FILE_DELIMITER = "_";
  private static final String OUTPUT_FILE_FORMAT = ".csv";
  private static final String OUTPUT_FILE_HEADER_DATE = "date";
  private static final String OUTPUT_FILE_HEADER_TOTAL_CLICKS = "total_clicks";

  /**
   * Private constructor to prevent instantiation.
   */
  private OULADFileProcessor() {
  }

  /**
   * Reads course data from a CSV file and returns a list of Course objects.
   *
   * @param filePath The `filePath` parameter in the `readCoursesFromCSV` method is the path to the
   *                 CSV file from which you want to read the course data. This method reads the
   *                 course data from the specified CSV file and returns a list of `Course` objects
   *                 parsed from the CSV records.
   * @return The method `readCoursesFromCSV` is returning a List of Course objects that are read
   * from a CSV file specified by the `filePath`. If the reading is successful, it returns a List
   * containing Course objects created from the CSV data. If an IOException occurs during the
   * reading process, it catches the exception, prints the stack trace, and returns an empty
   * ArrayList.
   */
  static List<Course> readCoursesFromCSV(String filePath) {
    List<Course> courses = new ArrayList<>();

    try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser csvParser = new CSVParser(reader,
            CSVFormat.DEFAULT.builder().setHeader().setDelimiter(CSV_DELIMITER).build())) {

      return csvParser.getRecords().stream()
          .map(record -> {
            String codeModule = record.get(CODE_MODULE);
            String codePresentation = record.get(CODE_PRESENTATION);
            int modulePresentationLength = Integer.parseInt(
                record.get(MODULE_PRESENTATION_LENGTH));
            return new Course(codeModule, codePresentation, modulePresentationLength);
          })
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Reads student activities from a CSV file and returns a list of StudentActivity objects.
   *
   * @param filePath The `filePath` parameter is the path to the CSV file from which you want to
   *                 read the student activities data. This method reads the data from the CSV file
   *                 and returns a list of `StudentActivity` objects based on the information in the
   *                 CSV file.
   * @return A List of StudentActivity objects is being returned. The method reads student
   * activities data from a CSV file, parses the data, and creates StudentActivity objects for each
   * record. These objects are then collected into a List and returned. If an IOException occurs
   * during the process, an empty ArrayList is returned.
   */
  static List<StudentActivity> readStudentActivitiesFromCSV(String filePath) {
    try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser csvParser = new CSVParser(reader,
            CSVFormat.DEFAULT.builder().setHeader().setDelimiter(CSV_DELIMITER).build())) {

      return csvParser.getRecords().stream()
          .map(record -> {
            String codeModule = record.get(CODE_MODULE);
            String codePresentation = record.get(CODE_PRESENTATION);
            int idStudent = Integer.parseInt(record.get(ID_STUDENT));
            int idSite = Integer.parseInt(record.get(ID_SITE));
            int date = Integer.parseInt(record.get(DATE));
            int sumClick = Integer.parseInt(record.get(SUM_CLICK));
            return new StudentActivity(codeModule, codePresentation, idStudent, idSite, date,
                sumClick);
          })
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Processes student activity data to generate a summary map based on course codes and activity
   * dates.
   *
   * @param courses           The `courses` parameter in the `processStudentActivityData` method is
   *                          a list of `Course` objects. Each `Course` object likely contains
   *                          information about a specific course, such as its code module and code
   *                          presentation.
   * @param studentActivities StudentActivity is a class that represents data related to student
   *                          activities in a course. It likely contains information such as the
   *                          module code, presentation code, date of activity, and the sum of
   *                          clicks made by the student during that activity.
   * @return The method `processStudentActivityData` returns a `Map<String, Map<Integer, Integer>>`
   * which contains summary data for student activities grouped by course code module and
   * presentation code.
   */
  static Map<String, Map<Integer, Integer>> processStudentActivityData(List<Course> courses,
      List<StudentActivity> studentActivities) {
    Map<String, Map<Integer, Integer>> summaryData = new HashMap<>();

    // Initialize summary data for all courses
    for (Course course : courses) {
      String key = course.getCodeModule() + OUTPUT_FILE_DELIMITER + course.getCodePresentation();
      summaryData.putIfAbsent(key, new HashMap<>());
    }

    // Process student activity data
    for (StudentActivity activity : studentActivities) {
      String key =
          activity.getCodeModule() + OUTPUT_FILE_DELIMITER + activity.getCodePresentation();
      int date = activity.getDate();
      int sumClick = activity.getSumClick();

      if (summaryData.containsKey(key)) {
        summaryData.get(key).merge(date, sumClick, Integer::sum);
      }
    }

    return summaryData;
  }

  /**
   * Generates summary files based on the processed data.
   *
   * @param summaryData     The `summaryData` parameter in the `generateSummaryFiles` method is a
   *                        `Map<String, Map<Integer, Integer>>` containing summary data for student
   *                        activities grouped by course code module and presentation code.
   * @param outputDirectory The `outputDirectory` parameter is a string representing the directory
   *                        where the summary files will be generated.
   */
  static void generateSummaryFiles(Map<String, Map<Integer, Integer>> summaryData,
      String outputDirectory) {

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
          csvPrinter.printRecord(dateClickEntry.getKey(), dateClickEntry.getValue());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}