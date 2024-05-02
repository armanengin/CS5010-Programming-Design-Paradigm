package assignment5.sequentialSolution;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Main class for the sequential solution of Assignment 5. This class reads course and student
 * activity data from CSV files, processes the data, and generates summary files based on the
 * processed data.
 */
public class Main {

  private static final String NO_DIRECTORY_ERROR = "OULAD csv directory was not specified.";
  private static final String COURSE_FILENAME = "courses.csv";
  private static final String STUDENT_ACTIVITY_FILENAME = "StudentVle.csv";
  private static final String OUTPUT_DIRECTORY = "out";

  /**
   * Main method for the sequential solution of Assignment 5. This method reads course and student
   * activity data from CSV files, processes the data, and generates summary files based on the
   * processed data.
   *
   * @param args Command-line arguments passed to the program. These arguments are not used.
   *             Therefore, the program will run with default settings.
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println(NO_DIRECTORY_ERROR);
      return;
    }

    String courseCsvFilePath = args[0] + File.separator + COURSE_FILENAME;
    String studentActivityCsvFilePath = args[0] + File.separator + STUDENT_ACTIVITY_FILENAME;

    List<Course> courses = OULADFileProcessor.readCoursesFromCSV(courseCsvFilePath);

    List<StudentActivity> studentActivities = OULADFileProcessor.readStudentActivitiesFromCSV(
        studentActivityCsvFilePath);

    Map<String, Map<Integer, Integer>> summaryData = OULADFileProcessor.processStudentActivityData(
        courses,
        studentActivities);

    OULADFileProcessor.generateSummaryFiles(summaryData, OUTPUT_DIRECTORY);
  }
}
