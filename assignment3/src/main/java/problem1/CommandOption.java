package problem1;

/**
 * Enum representing the different command options available in the application.
 * These options are used to specify actions and configurations through command line arguments.
 *
 * @author Arman Engin Sucu
 */
public enum CommandOption {
  /**
   * Represents the command-line option for specifying the CSV file path.
   * This option is used to input the path of the CSV file that contains the data to be processed.
   */
  CSV_FILE("--csv-file"),
  /**
   * Represents the command-line option to enable email functionality.
   * This option signifies that the user wishes to send emails as part of the command execution.
   */
  EMAIL("--email"),
  /**
   * Represents the command-line option for specifying the email template file path.
   * This option is used to input the path of the template file for emails, which defines the format and content of the emails to be sent.
   */
  EMAIL_TEMPLATE("--email-template"),
  /**
   * Represents the command-line option to enable letter functionality.
   * This option signifies that the user wishes to generate letters as part of the command execution.
   */

  LETTER("--letter"),
  /**
   * Represents the command-line option for specifying the letter template file path.
   * This option is used to input the path of the template file for letters, which defines the format and content of the letters to be generated.
   */
  LETTER_TEMPLATE("--letter-template"),
  /**
   * Represents the command-line option for specifying the output directory.
   * This option is used to input the path of the directory where the output files (emails, letters, etc.) will be saved.
   */
  OUTPUT_DIRECTORY("--output-dir");

  private final String command;

  /**
   * Constructs a CommandOption with the specified command string.
   *
   * @param command the command string associated with this enum constant.
   */
  CommandOption(String command) {
    this.command = command;
  }

  /**
   * Retrieves the command string associated with this enum constant.
   *
   * @return the command string.
   */
  public String getCommand() {
    return command;
  }

  /**
   * Returns a string representation of this CommandOption.
   *
   * @return a string that contains the command associated with this enum constant.
   */
  @Override
  public String toString() {
    return "CommandOption{" +
        "command='" + command + '\'' +
        '}';
  }

}
