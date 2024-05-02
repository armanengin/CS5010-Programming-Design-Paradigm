package assignment5.sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class OULADFileProcessorTest {

  @TempDir
  Path tempDir;

  @Test
  void readCoursesFromCSV_ReturnsNonEmptyList() throws Exception {
    Path csvFile = createSampleCoursesFile(tempDir);
    List<Course> courses = OULADFileProcessor.readCoursesFromCSV(csvFile.toString());
    assertFalse(courses.isEmpty(), "List of courses should not be empty.");
  }

  @Test
  void readCoursesFromCSV_CorrectNumberOfCourses() throws Exception {
    Path csvFile = createSampleCoursesFile(tempDir);
    List<Course> courses = OULADFileProcessor.readCoursesFromCSV(csvFile.toString());
    assertEquals(2, courses.size(), "Should read 2 courses.");
  }

  @Test
  void readCoursesFromCSV_FirstCourseCorrect() throws Exception {
    Path csvFile = createSampleCoursesFile(tempDir);
    List<Course> courses = OULADFileProcessor.readCoursesFromCSV(csvFile.toString());
    Course expected = new Course("CS101", "2021F", 120);
    assertEquals(expected, courses.get(0), "First course should match expected values.");
  }

  // Helper method to create a sample courses CSV file
  private Path createSampleCoursesFile(Path tempDir) throws Exception {
    String sampleCSV = "code_module,code_presentation,module_presentation_length\n" +
        "CS101,2021F,120\n" +
        "CS102,2021S,100\n";
    Path csvFile = tempDir.resolve("courses.csv");
    Files.writeString(csvFile, sampleCSV);
    return csvFile;
  }

  @Test
  void readStudentActivitiesFromCSV_ReturnsNonEmptyList() throws Exception {
    Path csvFile = createSampleStudentActivitiesFile(tempDir);
    List<StudentActivity> activities = OULADFileProcessor.readStudentActivitiesFromCSV(csvFile.toString());
    assertFalse(activities.isEmpty(), "List of student activities should not be empty.");
  }

  @Test
  void readStudentActivitiesFromCSV_CorrectNumberOfActivities() throws Exception {
    Path csvFile = createSampleStudentActivitiesFile(tempDir);
    List<StudentActivity> activities = OULADFileProcessor.readStudentActivitiesFromCSV(csvFile.toString());
    assertEquals(2, activities.size(), "Should read 2 student activities.");
  }

  @Test
  void readStudentActivitiesFromCSV_FirstActivityCorrect() throws Exception {
    Path csvFile = createSampleStudentActivitiesFile(tempDir);
    List<StudentActivity> activities = OULADFileProcessor.readStudentActivitiesFromCSV(csvFile.toString());
    StudentActivity expected = new StudentActivity("CS101", "2021F", 123, 456, 789, 10);
    assertEquals(expected, activities.get(0), "First student activity should match expected values.");
  }

  // Helper method to create a sample student activities CSV file
  private Path createSampleStudentActivitiesFile(Path tempDir) throws Exception {
    String sampleCSV = "code_module,code_presentation,id_student,id_site,date,sum_click\n" +
        "CS101,2021F,123,456,789,10\n" +
        "CS102,2021S,124,457,790,15\n";
    Path csvFile = tempDir.resolve("studentActivities.csv");
    Files.writeString(csvFile, sampleCSV);
    return csvFile;
  }

  @Test
  void processStudentActivityData_ReturnsNonEmptyMap() {
    List<Course> courses = List.of(new Course("CS101", "2021F", 120));
    List<StudentActivity> activities = List.of(new StudentActivity("CS101", "2021F", 123, 456, 789, 10));
    Map<String, Map<Integer, Integer>> summaryData = OULADFileProcessor.processStudentActivityData(courses, activities);
    assertFalse(summaryData.isEmpty(), "Summary data map should not be empty.");
  }

  @Test
  void processStudentActivityData_CorrectKeysInMap() {
    List<Course> courses = List.of(new Course("CS101", "2021F", 120));
    List<StudentActivity> activities = List.of(new StudentActivity("CS101", "2021F", 123, 456, 789, 10));
    Map<String, Map<Integer, Integer>> summaryData = OULADFileProcessor.processStudentActivityData(courses, activities);
    assertTrue(summaryData.containsKey("CS101_2021F"), "Summary data map should contain key 'CS101_2021F'.");
  }

  @Test
  void processStudentActivityData_CorrectSumClicksForDate() {
    List<Course> courses = List.of(new Course("CS101", "2021F", 120));
    List<StudentActivity> activities = List.of(new StudentActivity("CS101", "2021F", 123, 456, 789, 10),
        new StudentActivity("CS101", "2021F", 123, 456, 789, 5));
    Map<String, Map<Integer, Integer>> summaryData = OULADFileProcessor.processStudentActivityData(courses, activities);
    assertEquals(15, summaryData.get("CS101_2021F").get(789), "Total clicks for date 789 should be 15.");
  }

  @Test
  void generateSummaryFiles_CreatesFile() throws Exception {
    Map<String, Map<Integer, Integer>> summaryData = new HashMap<>();
    Map<Integer, Integer> courseData = new HashMap<>();
    courseData.put(1, 100);
    summaryData.put("CS101_2021F", courseData);

    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString());

    Path filePath = tempDir.resolve("CS101_2021F.csv");
    assertTrue(Files.exists(filePath), "The file CS101_2021F.csv should be created.");
  }

  @Test
  void generateSummaryFiles_ContainsExpectedNumberOfRecords() throws Exception {
    // Similar setup as the previous test
    Map<String, Map<Integer, Integer>> summaryData = setupSummaryData();
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString());

    // Check for the number of records without asserting their content
    Path filePath = tempDir.resolve("CS101_2021F.csv");
    List<String> lines = Files.readAllLines(filePath);
    // Subtract 1 for the header
    assertEquals(3, lines.size(), "The file should contain 2 records.");
  }

  @Test
  void generateSummaryFiles_FirstRecordIsCorrect() throws Exception {
    // Similar setup as the first test
    Map<String, Map<Integer, Integer>> summaryData = setupSummaryData();
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString());

    Path filePath = tempDir.resolve("CS101_2021F.csv");
    List<String> lines = Files.readAllLines(filePath);
    assertTrue(lines.contains("\"1\",\"100\""), "The first record should match '1,100'.");
  }

  @Test
  void generateSummaryFiles_SecondRecordIsCorrect() throws Exception {
    // Similar setup as the first test
    Map<String, Map<Integer, Integer>> summaryData = setupSummaryData();
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString());

    Path filePath = tempDir.resolve("CS101_2021F.csv");
    List<String> lines = Files.readAllLines(filePath);
    assertTrue(lines.contains("\"2\",\"200\""), "The second record should match '2,200'.");
  }

  private Map<String, Map<Integer, Integer>> setupSummaryData() {
    Map<String, Map<Integer, Integer>> summaryData = new HashMap<>();
    Map<Integer, Integer> courseData = new HashMap<>();
    courseData.put(1, 100);
    courseData.put(2, 200);
    summaryData.put("CS101_2021F", courseData);
    return summaryData;
  }
}