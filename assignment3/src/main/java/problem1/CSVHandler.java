package problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles reading and processing data from a CSV file.
 * This class is responsible for opening a CSV file, reading its content,
 * and providing access to the headers and data rows.
 *
 * @author Arman Engin Sucu
 */
public class CSVHandler {

  private List<String> csvHeaders;
  private List<List<String>> customerData;
  private Scanner csvReader;
  private static final String EMPTY_STRING = "";
  private static final String CSV_DATA_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
  private static final String QUOTATION_MARK = "\"";

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
   * Constructs a CSVHandler for reading and processing the specified CSV file.
   *
   * @param path the path to the CSV file to be processed. If the path is not absolute,
   *             it is appended to a predefined absolute path.
   * @throws FileNotFoundException if the specified file does not exist.
   */
  public CSVHandler(String path) throws FileNotFoundException {
    if (!path.contains(ABSOLUTE_PATH)) {
      path = ABSOLUTE_PATH.concat(path);
    }
    this.csvReader = new Scanner(new File(path));
    this.csvHeaders = trimQuotationMark(this.csvReader.nextLine().split(CSV_DATA_DELIMITER));
    this.customerData = new ArrayList<>();
    while (this.csvReader.hasNext()) {
      List<String> info = trimQuotationMark(this.csvReader.nextLine().split(CSV_DATA_DELIMITER));
      this.customerData.add(info);
    }
    this.csvReader.close();
  }

  /**
   * Removes quotation marks from each element in an array of strings.
   *
   * @param rowData the array of strings to process.
   * @return a list of strings with quotation marks removed.
   */
  private List<String> trimQuotationMark(String[] rowData) {
    return Arrays.stream(rowData).
        map(data -> data.replaceAll(QUOTATION_MARK, EMPTY_STRING))
        .collect(Collectors.toList());

  }

  /**
   * Gets the CSV headers.
   *
   * @return a list of strings representing the headers of the CSV file.
   */
  public List<String> getCsvHeaders() {
    return csvHeaders;
  }

  /**
   * Sets the CSV headers.
   *
   * @param csvHeaders a list of strings representing the headers to be set for the CSV file.
   */
  public void setCsvHeaders(List<String> csvHeaders) {
    this.csvHeaders = csvHeaders;
  }

  /**
   * Gets the customer data from the CSV.
   *
   * @return a list of lists, where each inner list represents a row of customer data.
   */
  public List<List<String>> getCustomerData() {
    return customerData;
  }

  /**
   * Sets the customer data for the CSV.
   *
   * @param customerData a list of lists, where each inner list represents a row of customer data to be set.
   */
  public void setCustomerData(List<List<String>> customerData) {
    this.customerData = customerData;
  }

  /**
   * Gets the CSV file reader.
   *
   * @return the scanner used to read the CSV file.
   */
  public Scanner getCsvReader() {
    return csvReader;
  }
  /**
   * Sets the CSV file reader.
   *
   * @param csvReader the scanner to be used for reading the CSV file.
   */
  public void setCsvReader(Scanner csvReader) {
    this.csvReader = csvReader;
  }

  /**
   * Checks whether this CSVHandler is equal to another object.
   *
   * @param o the object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CSVHandler that)) {
      return false;
    }
    return Objects.equals(getCsvHeaders(), that.getCsvHeaders())
        && Objects.equals(getCustomerData(), that.getCustomerData());
  }

  /**
   * Generates a hash code for this CSVHandler.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCsvHeaders(), getCustomerData());
  }

  /**
   * Returns a string representation of this CSVHandler.
   *
   * @return a string that "textually represents" this object.
   */
  @Override
  public String toString() {
    return "CSVHandler{" +
        "csvHeaders=" + csvHeaders +
        ", customerData=" + customerData +
        '}';
  }
}
