package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MessageDispatcherTest {
  private MessageDispatcher dispatcher;
  private List<List<String>> mockCustomerData;
  private HashMap<String, String> mockCommandArguments;
  private List<String> mockCsvHeaders;
  @TempDir
  Path tempDir;

  @BeforeEach
  void setUp() {
    // Setup mock data
    mockCustomerData = new ArrayList<>();
    mockCustomerData.add(List.of("John", "Doe"));

    mockCommandArguments = new HashMap<>();
    mockCommandArguments.put("--email", null);
    mockCommandArguments.put("--output-dir", "/testFiles/");
    mockCommandArguments.put("--email-template", "/testFiles/email-template.txt");
    mockCommandArguments.put("--csv-file", "/testFiles/insurance-company-members.csv");

    mockCsvHeaders = List.of("first_name", "last_name");

    dispatcher = new MessageDispatcher(mockCustomerData, mockCommandArguments, mockCsvHeaders);
  }

  @AfterEach
  void tearDown() {
    // Cleanup if needed
  }

  @Test
  void testDispatchMessageValidInput() throws IOException {

    boolean result = dispatcher.dispatchMessage("--email-template");
    assertTrue(result, "Dispatch message should return true for valid input.");
  }

  @Test
  void testDispatchMessageInvalidInput() throws IOException {
    // Test with invalid command argument that does not exist
    assertFalse(dispatcher.dispatchMessage("invalidTemplate"));
  }

  @Test
  void getCustomerData() {
    List<List<String>> expectedData = List.of(List.of("John", "Doe"));
    assertEquals(expectedData, dispatcher.getCustomerData(), "getCustomerData should return the correct customer data.");
  }

  @Test
  void setCustomerData() {
    List<List<String>> newData = List.of(List.of("Alice", "Smith"));
    dispatcher.setCustomerData(newData);
    assertEquals(newData, dispatcher.getCustomerData(), "setCustomerData should update the customer data.");
  }

  @Test
  void getCommandArguments() {
    HashMap<String, String> expectedArgs = new HashMap<>();
    expectedArgs.put("--email", null);
    expectedArgs.put("--output-dir", "/testFiles/");
    expectedArgs.put("--email-template", "/testFiles/email-template.txt");
    expectedArgs.put("--csv-file", "/testFiles/insurance-company-members.csv");

    assertEquals(expectedArgs, dispatcher.getCommandArguments(), "getCommandArguments should return the correct command arguments.");
  }

  @Test
  void setCommandArguments() {
    HashMap<String, String> newArgs = new HashMap<>();
    newArgs.put("--new-arg", "value");
    dispatcher.setCommandArguments(newArgs);
    assertEquals(newArgs, dispatcher.getCommandArguments(), "setCommandArguments should update the command arguments.");
  }

  @Test
  void getCsvHeaders() {
    List<String> expectedHeaders = List.of("first_name", "last_name");
    assertEquals(expectedHeaders, dispatcher.getCsvHeaders(), "getCsvHeaders should return the correct CSV headers.");
  }

  @Test
  void setCsvHeaders() {
    List<String> newHeaders = List.of("email");
    dispatcher.setCsvHeaders(newHeaders);
    assertEquals(newHeaders, dispatcher.getCsvHeaders(), "setCsvHeaders should update the CSV headers.");
  }

  @Test
  void testEqualsWithSelf() {
    // Test the instance against itself
    assertTrue(dispatcher.equals(dispatcher), "An instance should be equal to itself.");
  }

  @Test
  void testEqualsWithIdenticalInstance() {
    // Create another instance with the same data
    MessageDispatcher identicalDispatcher = new MessageDispatcher(mockCustomerData, mockCommandArguments, mockCsvHeaders);
    assertEquals(dispatcher, identicalDispatcher,
        "Instances with identical data should be considered equal.");
  }

  @Test
  void testEqualsWithDifferentCustomerData() {
    // Change customer data
    List<List<String>> differentCustomerData = new ArrayList<>(List.of(List.of("Alice", "Smith")));
    MessageDispatcher differentDispatcher = new MessageDispatcher(differentCustomerData, mockCommandArguments, mockCsvHeaders);
    assertNotEquals(dispatcher, differentDispatcher,
        "Instances with different customer data should not be equal.");
  }

  @Test
  void testEqualsWithDifferentCommandArguments() {
    // Change command arguments
    HashMap<String, String> differentCommandArguments = new HashMap<>(mockCommandArguments);
    differentCommandArguments.put("--email", "example@example.com"); // Alter an argument
    MessageDispatcher differentDispatcher = new MessageDispatcher(mockCustomerData, differentCommandArguments, mockCsvHeaders);
    assertNotEquals(dispatcher, differentDispatcher,
        "Instances with different command arguments should not be equal.");
  }

  @Test
  void testEqualsWithDifferentCsvHeaders() {
    // Change CSV headers
    List<String> differentCsvHeaders = new ArrayList<>(List.of("email_address"));
    MessageDispatcher differentDispatcher = new MessageDispatcher(mockCustomerData, mockCommandArguments, differentCsvHeaders);
    assertNotEquals(dispatcher, differentDispatcher,
        "Instances with different CSV headers should not be equal.");
  }

  @Test
  void testEqualsWithNull() {
    // Compare against null
    assertNotNull(dispatcher, "An instance should not be equal to null.");
  }

  @Test
  void testEqualsWithDifferentType() {
    // Compare against an object of a different class
    Object differentObject = new Object();
    assertNotEquals(dispatcher, differentObject,
        "An instance should not be equal to an object of a different type.");
  }

  @Test
  void testHashCode() {
    MessageDispatcher anotherDispatcher = new MessageDispatcher(mockCustomerData, mockCommandArguments, mockCsvHeaders);
    assertEquals(dispatcher.hashCode(), anotherDispatcher.hashCode(), "Hashcode should be equal for two identical dispatchers.");
  }

  @Test
  void testToString() {
    String expectedString = "MessageGenerator{" +
        "customerData=" + mockCustomerData.toString() +
        ", commandArguments=" + mockCommandArguments.toString() +
        ", csvHeaders=" + mockCsvHeaders.toString() +
        '}';
    String actualString = dispatcher.toString();
    assertNotNull(actualString, "toString should return a non-null string.");
  }
}