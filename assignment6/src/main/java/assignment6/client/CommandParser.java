package assignment6.client;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The CommandParser class is responsible for parsing user input.
 */
public final class CommandParser {

  private static final List<String> COMMANDS = List.of(
      "logoff",
      "who",
      "?"
  );
  private static final List<String> COMMANDS_WITH_ARGS = List.of("@", "!");
  private static final String EMPTY_STRING = "";
  private static final String REGEX_DELIMITER = "|";
  private static final String REGEX_FORMAT = "^({0})$|({1})(\\S+)";
  private static final String USER_INPUT_DELIMITER = " ";
  private static final int COMMAND_PART_INDEX = 0;
  private static final int SPLITS_LIMIT = 2;
  private static final int COMMAND_GROUP = 1;
  private static final int COMMAND_WITH_ARG_GROUP = 2;
  private static final int ARGUMENT_GROUP = 3;
  private static CommandParser instance;
  private static Pattern commandPattern;

  /**
   * Private constructor to prevent instantiation.
   */
  private CommandParser() {
    buildCommandPattern();
  }

  /**
   * Returns the singleton instance of the CommandParser.
   *
   * @return the singleton instance of the CommandParser
   */
  public static synchronized CommandParser getInstance() {
    if (instance == null) {
      instance = new CommandParser();
    }
    return instance;
  }

  /**
   * Sets the singleton instance of the CommandParser.
   *
   * @param instance the singleton instance of the CommandParser
   */
  public static void setInstance(CommandParser instance) {
    CommandParser.instance = instance;
  }

  /**
   * Returns the command pattern.
   *
   * @return the command pattern
   */
  public static Pattern getCommandPattern() {
    return commandPattern;
  }

  /**
   * Sets the command pattern.
   *
   * @param commandPattern the command pattern
   */
  public static void setCommandPattern(Pattern commandPattern) {
    CommandParser.commandPattern = commandPattern;
  }

  /**
   * Builds the command pattern.
   */
  private void buildCommandPattern() {
    String regularCommands = COMMANDS.stream()
        .map(Pattern::quote)
        .collect(Collectors.joining(REGEX_DELIMITER));
    String prefixCommands = COMMANDS_WITH_ARGS.stream()
        .map(Pattern::quote)
        .collect(Collectors.joining(REGEX_DELIMITER));
    String commandRegex = MessageFormat.format(REGEX_FORMAT, regularCommands, prefixCommands);
    commandPattern = Pattern.compile(commandRegex);
  }

  /**
   * Parses the user input.
   *
   * @param userInput the user input
   * @return the parsed user input
   */
  public List<String> parse(String userInput) {
    String[] parts = userInput.split(USER_INPUT_DELIMITER, SPLITS_LIMIT);
    String commandPart = parts[COMMAND_PART_INDEX];
    String message = parts.length > 1 ? parts[COMMAND_GROUP] : EMPTY_STRING;

    Matcher matcher = commandPattern.matcher(commandPart);
    if (matcher.matches()) {
      if (matcher.group(COMMAND_GROUP) != null && message.isEmpty()) {
        String command = matcher.group(COMMAND_GROUP);
        return List.of(command, EMPTY_STRING, EMPTY_STRING);
      } else if (matcher.group(COMMAND_WITH_ARG_GROUP) != null
          && matcher.group(ARGUMENT_GROUP) != null) {
        String command = matcher.group(COMMAND_WITH_ARG_GROUP);
        String argument = matcher.group(ARGUMENT_GROUP);
        return List.of(command, argument, message);
      }
    }

    return List.of(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
  }
}
