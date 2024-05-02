package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static problem1.CSVHandler.ABSOLUTE_PATH;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVHandlerTest {

  private Path tempFile;
  private CSVHandler csvHandler;

  @BeforeEach
  void setUp() throws IOException {
    Path directory = Path.of(ABSOLUTE_PATH, "subdirectory");
    // Ensuring the directory exists
    Files.createDirectories(directory);

    // Create a temporary CSV file in the specified directory
    tempFile = Files.createTempFile(directory, "test", ".csv");

    List<String> lines = List.of(
        "\"Header1\",\"Header2\",\"Header3\"",
        "\"Value1\",\"Value2\",\"Value3\"",
        "\"Value4\",\"Value5\",\"Value6\""
    );
    Files.write(tempFile, lines);
    csvHandler = new CSVHandler(tempFile.toString());
  }

  @AfterEach
  void tearDown() throws IOException {
    // Delete the temporary file after each test
    Files.deleteIfExists(tempFile);
  }

  @Test
  void testFileNotFound() {
    assertThrows(FileNotFoundException.class, () -> {
      new CSVHandler("non_existing_file.csv");
    });
  }

  @Test
  void testCsvHeaderParsing() throws FileNotFoundException {
    CSVHandler handler = new CSVHandler(tempFile.toAbsolutePath().toString());
    assertEquals(List.of("Header1", "Header2", "Header3"), handler.getCsvHeaders());
  }

  @Test
  void testDataRowParsing() throws FileNotFoundException {
    CSVHandler handler = new CSVHandler(tempFile.toString());
    List<List<String>> expectedData = List.of(
        List.of("Value1", "Value2", "Value3"),
        List.of("Value4", "Value5", "Value6")
    );
    assertEquals(expectedData, handler.getCustomerData());
  }

  @Test
  void testQuotationMarkRemoval() throws FileNotFoundException {
    CSVHandler handler = new CSVHandler(tempFile.toString());
    assertFalse(handler.getCustomerData().get(0).get(0).contains("\""));
  }

  @Test
  void getCsvHeaders() {
    assertEquals(Arrays.asList("Header1", "Header2", "Header3"), csvHandler.getCsvHeaders());
  }

  @Test
  void setCsvHeaders() {
    List<String> newHeaders = Arrays.asList("NewHeader1", "NewHeader2", "NewHeader3");
    csvHandler.setCsvHeaders(newHeaders);
    assertEquals(newHeaders, csvHandler.getCsvHeaders());
  }

  @Test
  void getCustomerData() {
    List<List<String>> expectedData = Arrays.asList(
        Arrays.asList("Value1", "Value2", "Value3"),
        Arrays.asList("Value4", "Value5", "Value6")
    );
    assertEquals(expectedData, csvHandler.getCustomerData());
  }

  @Test
  void setCustomerData() {
    List<List<String>> newData = Arrays.asList(
        Arrays.asList("NewValue1", "NewValue2", "NewValue3"),
        Arrays.asList("NewValue4", "NewValue5", "NewValue6")
    );
    csvHandler.setCustomerData(newData);
    assertEquals(newData, csvHandler.getCustomerData());
  }

  @Test
  void getCsvReader() {
    assertNotNull(csvHandler.getCsvReader());
  }

  @Test
  void setCsvReader() {
    Scanner newCsvReader = new Scanner("NewHeader1,NewHeader2,NewHeader3\nNewValue1,NewValue2,NewValue3");
    csvHandler.setCsvReader(newCsvReader);
    assertNotNull(csvHandler.getCsvReader()); // Verifies the setter; specific behavior depends on implementation
  }

  @Test
  void testEquals() throws FileNotFoundException {
    CSVHandler anotherCsvHandler = new CSVHandler(tempFile.toString());
    assertEquals(csvHandler, anotherCsvHandler);
  }

  @Test
  void testHashCode() throws FileNotFoundException {
    CSVHandler anotherCsvHandler = new CSVHandler(tempFile.toString());
    assertEquals(csvHandler.hashCode(), anotherCsvHandler.hashCode());
  }

  @Test
  void testToString() {
    String expected = "CSVHandler{csvHeaders=[Header1, Header2, Header3], customerData=[[Value1, Value2, Value3], [Value4, Value5, Value6]]}";
    String actual = csvHandler.toString();
    assertNotNull(actual);
  }
}