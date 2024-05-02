package problem1;

/**
 * Enum representing specific error messages for the application.
 * Each enum constant corresponds to a different error scenario that might occur during the execution of the application.
 *
 * @author Arman Engin Sucu
 */
public enum ErrorMessage {
  /**
   * Error message for missing CSV file argument.
   */
  NO_CSV_FILE("\nError: Missing argument --csv-file. Please specify the CSV file path using --csv-file path.\n"),
  /**
   * Error message for missing output directory argument.
   */
  NO_OUTPUT_DIR("\nError: Missing argument --output-dir. Please specify the output directory path using --output-dir path.\n"),
  /**
   * Error message for email operation provided without an email template.
   */
  EMAIL_BUT_NO_TEMPLATE("\nError: Email operation initiated without an email template. Please provide an email template using --email-template path.\n"),
  /**
   * Error message for letter operation provided without a letter template.
   */
  LETTER_BUT_NO_TEMPLATE("\nError: Letter operation initiated without a letter template. Please provide a letter template using --letter-template path.\n"),
  /**
   * Error message for email template provided without specifying email operation.
   */
  EMAIL_TEMPLATE_BUT_NO_EMAIL("\nError: An email template is provided without specifying the email operation. Please initiate the email operation using --email.\n"),
  /**
   * Error message for letter template provided without specifying letter operation.
   */
  LETTER_TEMPLATE_BUT_NO_LETTER("\nError: A letter template is provided without specifying the letter operation. Please initiate the letter operation using --letter.\n");

  /**
   * The message associated with the error.
   */
  private final String message;

  /**
   * Constructs an instance of the error message with the specified detail message.
   *
   * @param message the detail message.
   */
  ErrorMessage(String message) {
    this.message = message;
  }

  /**
   * Retrieves the detail message associated with the error.
   *
   * @return the detail message.
   */

  public String getMessage() {
    return message;
  }

  /**
   * Returns a string representation of the error message.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "ErrorMessage{" +
        "message='" + message + '\'' +
        '}';
  }
}
