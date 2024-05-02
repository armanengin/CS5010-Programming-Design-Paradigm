package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class OULADFileProcessorTest {
  @TempDir
  Path tempDir;

  Map<String, Map<Integer, Integer>> summaryData = new HashMap<>();
  List<List<String>> highActivities = new ArrayList<>();

  @BeforeEach
  void setUp() {
    // Example setup - populate summaryData with test data
    Map<Integer, Integer> courseData = new HashMap<>();
    courseData.put(-10, 10000); // Date, Total Clicks
    courseData.put(-9, 15000);
    summaryData.put("CS101_2021F", courseData);
  }

  @AfterEach
  void tearDown() throws IOException {
    highActivities.clear();
    Files.list(tempDir).forEach(path -> path.toFile().delete());
  }

  private List<CSVRecord> readCSVRecords(Path filePath) throws IOException {
    try (FileReader in = new FileReader(filePath.toFile())) {
      return CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build()
          .parse(in).getRecords();
    }
  }

  private void prepareHighActivities() {
    // This method should mimic the action of `generateSummaryFiles`
    // by populating `highActivities` with data that would trigger
    // the creation of a high activity file.
    List<String> highActivityRecord = Arrays.asList("CS101_2021F", "-9", "15000");
    highActivities.add(highActivityRecord);
  }


  @Test
  void generateSummaryFiles_CreatesFile() {
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString(), highActivities, 10000);
    Path filePath = tempDir.resolve("CS101_2021F.csv");
    assertTrue(Files.exists(filePath), "Summary file should be created");
  }

  @Test
  void generateSummaryFiles_ContainsTwoRecords() throws IOException {
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString(), highActivities, 10000);
    Path filePath = tempDir.resolve("CS101_2021F.csv");
    List<CSVRecord> records = readCSVRecords(filePath);
    assertEquals(2, records.size(), "There should be 2 records in the summary file.");
  }

  @Test
  void generateSummaryFiles_HasRecordWithCorrectTotalClicks() throws IOException {
    OULADFileProcessor.generateSummaryFiles(summaryData, tempDir.toString(), highActivities, 10000);
    Path filePath = tempDir.resolve("CS101_2021F.csv");
    List<CSVRecord> records = readCSVRecords(filePath);

    // Check that one of the records has 10000 total clicks
    boolean foundRecordWith10000Clicks = records.stream()
        .anyMatch(record -> "10000".equals(record.get("total_clicks")));
    assertTrue(foundRecordWith10000Clicks, "There should be a record with 10000 total clicks.");
  }


  @Test
  void generateHighActivityFile_CreatesFile() throws IOException {
    prepareHighActivities();
    OULADFileProcessor.generateHighActivityFile(highActivities, tempDir.toString(), 11000);
    Path filePath = tempDir.resolve("activity-11000.csv");
    assertTrue(Files.exists(filePath), "High activity file should be created");
  }

  @Test
  void generateHighActivityFile_ContainsCorrectModulePresentation() throws IOException {
    prepareHighActivities();
    OULADFileProcessor.generateHighActivityFile(highActivities, tempDir.toString(), 11000);
    Path filePath = tempDir.resolve("activity-11000.csv");
    CSVRecord record = readCSVRecords(filePath).get(0);
    assertEquals("CS101_2021F", record.get("module_presentation"), "Module presentation should match.");
  }

  @Test
  void generateHighActivityFile_ContainsCorrectTotalClicks() throws IOException {
    prepareHighActivities();
    OULADFileProcessor.generateHighActivityFile(highActivities, tempDir.toString(), 11000);
    Path filePath = tempDir.resolve("activity-11000.csv");
    CSVRecord record = readCSVRecords(filePath).get(0);
    assertEquals("15000", record.get("total_clicks"), "Total clicks should match for high activity.");
  }
}