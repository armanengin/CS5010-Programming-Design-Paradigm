package problem1;

import static problem1.NumericConstants.ZERO;
import static problem1.NumericConstants.ONE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles command line arguments for generating email and letter messages based on specified templates.
 * <p>
 * This class processes command line inputs to configure the generation of messages, ensuring necessary
 * parameters are provided and valid. It supports commands for specifying email and letter templates,
 * output directory, and the CSV file containing data to be used in message generation.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class CommandHandler {
  private String[] commands;
  private static final String HOW_TO_USE_DESCRIPTION =
      "\nUsage:\n"
          + " --email                    only generate email messages\n"
          + " --email-template <file>    accept a filename that holds the email template.\n"
          + "                            Required if --email is used\n\n"
          + " --letter                   only generate letters\n"
          + " --letter-template <file>   accept a filename that holds the letter template.\n"
          + "                            Required if --letter is used\n\n"
          + " --output-dir <path>        accept the name of a folder, all output is placed in this folder\n\n"
          + " --csv-file <path>          accept the name of the csv file to process\n";

  /**
   * Constructs a CommandHandler with the provided array of command line arguments.
   *
   * @param commands the command line arguments.
   */
  public CommandHandler(String[] commands) {
    this.commands = commands;
  }


  /**
   * Processes and validates the command line arguments, organizing them into a map for easy access.
   * <p>
   * This method checks for required command arguments and pairs, throws IllegalArgumentException
   * if essential commands are missing or improperly used.
   * </p>
   *
   * @return a map containing command arguments with their corresponding values.
   * @throws IllegalArgumentException if required commands are missing or misused.
   */
  public HashMap<String, String> getCommandArguments() {
    HashMap<String, String> commandArguments = new HashMap<>();
    for (int i = ZERO.getValue(); i < commands.length; i++) {
      processCommand(i, commandArguments);
    }
    validateCommands(commandArguments);
    return commandArguments;
  }

  /**
   * Processes individual command line arguments, determining whether they require values
   * and storing them appropriately in the command arguments map.
   *
   * @param index             the index of the current command in the commands array.
   * @param commandArguments  the map to store command arguments and their values.
   */
  private void processCommand(int index, Map<String, String> commandArguments) {
    String command = commands[index];
    boolean isTemplateOrFileCommand = isTemplateOrFileCommand(command);
    boolean isActionCommand = isActionCommand(command);

    if (isTemplateOrFileCommand) {
      String value = commands[index + ONE.getValue()]; // Assuming the next index holds the value for the command
      commandArguments.put(command, value);
    } else if (isActionCommand) {
      commandArguments.put(command, null);
    }
  }

  /**
   * Determines if a given command is one that requires a following value, specifically
   * for templates and file paths.
   *
   * @param command the command to check.
   * @return true if the command requires a following value, false otherwise.
   */
  private boolean isTemplateOrFileCommand(String command) {
    return command.equals(CommandOption.LETTER_TEMPLATE.getCommand()) ||
        command.equals(CommandOption.EMAIL_TEMPLATE.getCommand()) ||
        command.equals(CommandOption.CSV_FILE.getCommand()) ||
        command.equals(CommandOption.OUTPUT_DIRECTORY.getCommand());
  }

  /**
   * Determines if a given command is an action command, which does not require a following value.
   *
   * @param command the command to check.
   * @return true if the command is an action command, false otherwise.
   */
  private boolean isActionCommand(String command) {
    return command.equals(CommandOption.LETTER.getCommand()) ||
        command.equals(CommandOption.EMAIL.getCommand());
  }

  /**
   * Validates the set of command arguments to ensure necessary commands are included and
   * properly paired. Throws IllegalArgumentException for any detected misuse or absence of required commands.
   *
   * @param commandArguments the map of command arguments to validate.
   * @throws IllegalArgumentException if validation fails.
   */
  private void validateCommands(HashMap<String, String> commandArguments) {
    if (!commandArguments.containsKey(CommandOption.CSV_FILE.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.NO_CSV_FILE.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
    else if (!commandArguments.containsKey(CommandOption.OUTPUT_DIRECTORY.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.NO_OUTPUT_DIR.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
    else if (commandArguments.containsKey(CommandOption.EMAIL.getCommand()) && !commandArguments.containsKey(CommandOption.EMAIL_TEMPLATE.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.EMAIL_BUT_NO_TEMPLATE.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
    else if (commandArguments.containsKey(CommandOption.LETTER.getCommand()) && !commandArguments.containsKey(CommandOption.LETTER_TEMPLATE.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.LETTER_BUT_NO_TEMPLATE.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
    else if (commandArguments.containsKey(CommandOption.EMAIL_TEMPLATE.getCommand()) && !commandArguments.containsKey(CommandOption.EMAIL.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.EMAIL_TEMPLATE_BUT_NO_EMAIL.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
    else if (commandArguments.containsKey(CommandOption.LETTER_TEMPLATE.getCommand()) && !commandArguments.containsKey(CommandOption.LETTER.getCommand())) {
      throw new IllegalArgumentException(ErrorMessage.LETTER_TEMPLATE_BUT_NO_LETTER.getMessage() + HOW_TO_USE_DESCRIPTION);
    }
  }

  /**
   * Retrieves the command line arguments processed by this handler.
   *
   * @return an array of command line arguments.
   */
  public String[] getCommands() {
    return commands;
  }

  /**
   * Sets or updates the command line arguments to be processed by this handler.
   *
   * @param commands the command line arguments to set.
   */
  public void setCommands(String[] commands) {
    this.commands = commands;
  }

  /**
   * Compares this CommandHandler instance with another object for equality, based on the command line arguments.
   *
   * @param o the object to compare with this instance.
   * @return true if the specified object is a CommandHandler with the same command line arguments, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommandHandler that)) {
      return false;
    }
    return Arrays.equals(getCommands(), that.getCommands());
  }

  /**
   * Generates a hash code for this CommandHandler instance, based on its command line arguments.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(getCommands());
  }

  /**
   * Returns a string representation of this CommandHandler instance, including its command line arguments.
   *
   * @return a string representation of this object.
   */
  @Override
  public String toString() {
    return "CommandHandler{" +
        "commands=" + Arrays.toString(commands) +
        '}';
  }
}
