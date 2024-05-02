package problem1;

import static java.nio.charset.StandardCharsets.UTF_8;
import static problem1.NumericConstants.ONE;
import static problem1.NumericConstants.ZERO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The {@code MessageDispatcher} class is responsible for generating and dispatching messages
 * based on the provided templates and customer data. It handles the construction of messages
 * for emails or letters by replacing placeholders in the templates with actual customer data.
 *
 * @author Arman Engin Sucu
 */
public class MessageDispatcher {
  /**
   * Stores customer data with each list representing a customer and their details.
   */
  private List<List<String>> customerData;
  /**
   * Stores command arguments including paths to message templates and output directories.
   */
  private HashMap<String, String> commandArguments;
  /**
   * Stores CSV header names that correspond to placeholders in the message templates.
   */
  private List<String> csvHeaders;

  // Regex patterns for identifying placeholders in the templates.
  private static final String DOUBLE_LEFT_BRACKET_REGEX = "\\[\\[";
  private static final String DOUBLE_RIGHT_BRACKET_REGEX = "\\]\\]";

  // Constants for generating output file paths.
  private static final String SEND_TO = "-send-to-customer-";
  private static final String SEPARATOR = "-";
  private static final String FILE_EXTENSION = ".txt";
  private static final String OUTPUT_DIRECTORY_COMMAND = "--output-dir";

  /**
   * A string constant representing the absolute path of the current
   * working directory. This path is dynamically obtained at runtime,
   * ensuring that it accurately reflects the current execution environment.
   * This constant is useful for file operations that require an absolute
   * path reference.
   */
  final public static String ABSOLUTE_PATH = new File(
      "").getAbsolutePath();
  /**
   * Constructs a MessageDispatcher with specified customer data, command arguments, and CSV headers.
   *
   * @param customerData A list of lists, where each inner list represents a customer and their details.
   * @param commandArguments A map of command arguments, including template locations and output directory.
   * @param csvHeaders A list of CSV header names corresponding to placeholders in the message templates.
   */
  public MessageDispatcher(List<List<String>> customerData, HashMap<String, String> commandArguments,
      List<String> csvHeaders) {
    this.customerData = customerData;
    this.commandArguments = commandArguments;
    this.csvHeaders = csvHeaders;
  }

  /**
   * Dispatches messages based on the specified template command.
   * It reads the template file, replaces placeholders with actual customer data,
   * and writes the personalized messages to files in the output directory.
   *
   * @param templateCommand The command that specifies the template to use for message generation.
   * @return true if messages are successfully dispatched, false otherwise.
   * @throws IOException If an I/O error occurs during reading or writing files.
   */
  public boolean dispatchMessage(String templateCommand) throws IOException {
    if (commandArguments.containsKey(templateCommand)) {
      // get the output directory absolute path
      String directoryPath = this.commandArguments.get(OUTPUT_DIRECTORY_COMMAND);
      if (!directoryPath.contains(ABSOLUTE_PATH)) {
        directoryPath = ABSOLUTE_PATH + directoryPath;
      }
      String finalOutputDi = directoryPath;
      // write the information into the output directory
      outputDataToFile(templateCommand, finalOutputDi);
      return true;
    }
    return false;
  }

  /**
   * Outputs personalized data to files for each customer based on the template command.
   * Replaces placeholders in the template with actual customer data and writes the result to an output file.
   *
   * @param templateCommand The command specifying the template to use.
   * @param finalOutputDir The final output directory to write the personalized messages.
   * @throws IOException If an I/O error occurs during file operations.
   */
  private void outputDataToFile(String templateCommand, String finalOutputDir) throws IOException {
    String templateFileLocation = this.commandArguments.get(templateCommand);
    if (!templateFileLocation.contains(ABSOLUTE_PATH)) {
      templateFileLocation = ABSOLUTE_PATH + templateFileLocation;
    }
    Path templateFilePath = Path.of(templateFileLocation);

    int customerCount = ZERO.getValue();
    for (List<String> currData : this.customerData) {
      customerCount += ONE.getValue();
      String processedContent = processTemplateContent(templateFilePath, currData);
      writeOutputFile(processedContent, finalOutputDir, templateCommand, customerCount, currData.get(ZERO.getValue()));
    }
  }

  /**
   * Processes the template content by replacing placeholders with actual customer information.
   * This method reads the template file's content, replaces each placeholder with the corresponding
   * value from the customer information list, and returns the processed content as a string.
   *
   * @param templateFilePath the path to the template file.
   * @param customerInfo a list of strings containing customer information corresponding to the placeholders in the template.
   * @return a string representing the content of the template file after all placeholders have been replaced with actual customer information.
   * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
   */
  private String processTemplateContent(Path templateFilePath, List<String> customerInfo) throws IOException {
    String fileContents = Files.readString(templateFilePath, StandardCharsets.UTF_8);
    for (int index = ZERO.getValue(); index < csvHeaders.size(); index++) {
      String placeholder = DOUBLE_LEFT_BRACKET_REGEX + csvHeaders.get(index) + DOUBLE_RIGHT_BRACKET_REGEX;
      fileContents = fileContents.replaceAll(placeholder, customerInfo.get(index));
    }
    return fileContents;
  }

  /**
   * Writes the processed template content to a file. This method constructs the output file path
   * using the specified directory, template command, customer count, and customer identifier.
   * It then writes the processed content to this file.
   *
   * @param fileContents the processed template content to be written to the file.
   * @param finalOutputDir the directory where the output file will be created.
   * @param templateCommand the command used in the template processing, affecting the naming of the output file.
   * @param customerCount the sequence number of the customer, used in naming the output file.
   * @param customerIdentifier a unique identifier for the customer, used in naming the output file.
   * @throws IOException if an I/O error occurs writing to the file.
   */
  private void writeOutputFile(String fileContents, String finalOutputDir, String templateCommand, int customerCount, String customerIdentifier) throws IOException {
    String outputFilePath = finalOutputDir + templateCommand + SEND_TO + customerCount + SEPARATOR + customerIdentifier + FILE_EXTENSION;
    Files.writeString(Path.of(outputFilePath), fileContents, StandardCharsets.UTF_8);
  }

  /**
   * Returns the customer data being used for message dispatching.
   *
   * @return A list of lists, where each inner list represents a customer and their details.
   */
  public List<List<String>> getCustomerData() {
    return customerData;
  }

  /**
   * Sets the customer data to be used for message dispatching.
   *
   * @param customerData A list of lists, where each inner list represents a customer and their details.
   */
  public void setCustomerData(List<List<String>> customerData) {
    this.customerData = customerData;
  }

  /**
   * Returns the command arguments used for determining the template and output directory locations.
   *
   * @return A map of command arguments including keys for template locations and output directory.
   */
  public HashMap<String, String> getCommandArguments() {
    return commandArguments;
  }

  /**
   * Sets the command arguments for determining the template and output directory locations.
   *
   * @param commandArguments A map of command arguments including keys for template locations and output directory.
   */
  public void setCommandArguments(HashMap<String, String> commandArguments) {
    this.commandArguments = commandArguments;
  }

  /**
   * Returns the CSV headers that correspond to placeholders in the message templates.
   *
   * @return A list of CSV header names.
   */
  public List<String> getCsvHeaders() {
    return csvHeaders;
  }

  /**
   * Sets the CSV headers that correspond to placeholders in the message templates.
   *
   * @param csvHeaders A list of CSV header names.
   */
  public void setCsvHeaders(List<String> csvHeaders) {
    this.csvHeaders = csvHeaders;
  }

  /**
   * Compares this MessageDispatcher to another object for equality.
   * Two MessageDispatchers are considered equal if their customer data, command arguments, and csvHeaders are equal.
   *
   * @param o The object to compare this MessageDispatcher against.
   * @return true if the given object represents a MessageDispatcher equivalent to this dispatcher, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageDispatcher that)) {
      return false;
    }
    return Objects.equals(getCustomerData(), that.getCustomerData())
        && Objects.equals(getCommandArguments(), that.getCommandArguments())
        && Objects.equals(getCsvHeaders(), that.getCsvHeaders());
  }

  /**
   * Computes a hash code for this MessageDispatcher.
   * The hash code is based on the customer data, command arguments, and csvHeaders.
   *
   * @return A hash code value for this object.
   */

  @Override
  public int hashCode() {
    return Objects.hash(getCustomerData(), getCommandArguments(), getCsvHeaders());
  }

  /**
   * Returns a string representation of this MessageDispatcher.
   * Includes details about customer data, command arguments, and csvHeaders.
   *
   * @return A string representation of this MessageDispatcher.
   */
  @Override
  public String toString() {
    return "MessageGenerator{" +
        "customerData=" + customerData +
        ", commandArguments=" + commandArguments +
        ", csvHeaders=" + csvHeaders +
        '}';
  }
}
