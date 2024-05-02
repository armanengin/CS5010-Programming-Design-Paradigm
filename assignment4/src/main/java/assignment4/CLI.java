package assignment4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A singleton command-line interface (CLI) class that manages grammars and sentence generation. It
 * contains methods to load grammars from files, select grammars, and generate sentences based on
 * the selected grammar. No hashCode and equals methods because of the nature of singleton classes
 */
public class CLI {

  /**
   * Error message indicating no grammars were found in the specified directory.
   */
  public static final String NO_GRAMMARS_FOUND_ERROR = "No grammars available in the specified"
      + "directory.";

  /**
   * Message displayed when the program starts loading grammars from the specified directory.
   */
  public static final String PROMPT_LOADING = "Loading grammars...";

  /**
   * Message listing all available grammars for the user to choose from.
   */
  public static final String PROMPT_AVAILABLE_GRAMMARS = "\nThe following grammars are available:";

  /**
   * Prompt asking the user to select a grammar for sentence generation, with an option to quit.
   */
  public static final String PROMPT_GRAMMAR_CHOICE = "\nWhich would you like to use? (q to quit)";

  /**
   * Error message displayed when the user's choice is outside the range of available grammars.
   */
  public static final String PROMPT_OUT_OF_RANGE_ERROR = "\nChoice out of range. Please try again.";

  /**
   * Error message displayed when the user inputs an invalid selection (non-numeric input when a
   * number is expected).
   */
  public static final String PROMPT_INVALID_INPUT = "\nInvalid input. Please enter a number.";

  /**
   * Error message indicating an issue occurred while attempting to load grammars.
   */
  public static final String PROMPT_LOADING_ERROR = "\nAn error occurred while loading grammars: ";

  /**
   * Prompt asking the user if they would like to generate another sentence using the same grammar.
   */
  public static final String PROMPT_CONTINUE = "\nWould you like another? (y/n)";

  /**
   * Error message displayed when the user inputs an invalid response to the continuation prompt
   * (not 'y' or 'n').
   */
  public static final String PROMPT_INVALID_INPUT_FOR_CONTINUE = "\nInvalid input. Please enter 'y'"
      + "or 'n'.";

  /**
   * Error message indicating a failure during the reading or parsing of a grammar file.
   */
  public static final String PROMPT_PARSING_ERROR = "Failed to read or parse grammar file: ";

  private static CLI instance;
  private final Scanner scanner = new Scanner(System.in);
  private final Map<String, Grammar> grammars = new HashMap<>();

  /**
   * Private constructor for the singleton CLI class.
   */
  private CLI() {
  }

  /**
   * Returns the singleton instance of the CLI class, creating it if necessary.
   *
   * @return The singleton CLI instance.
   */
  public static synchronized CLI getInstance() {
    if (instance == null) {
      instance = new CLI();
    }
    return instance;
  }

  /**
   * Loads grammar files from the specified directory and allows the user to select one for sentence
   * generation. Once a grammar is selected, it is used to generate sentences as per user request.
   *
   * @param directoryPath The path to the directory containing grammar files.
   */

  public void loadAndSelectGrammar(String directoryPath) {
    try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
      List<String> grammarFiles = paths
          .filter(Files::isRegularFile)
          .map(path -> path.getFileName().toString())
          .toList();

      if (grammarFiles.isEmpty()) {
        System.out.println(NO_GRAMMARS_FOUND_ERROR);
        return;
      }

      System.out.println(PROMPT_LOADING);

      while (true) {
        System.out.println(PROMPT_AVAILABLE_GRAMMARS);
        // adjust the index in the console output to be 1-based
        IntStream.range(0, grammarFiles.size())
            .forEach(
                i -> System.out.println((i + 1) + ". " + grammarFiles.get(i)));

        System.out.println(PROMPT_GRAMMAR_CHOICE);
        String input = scanner.nextLine();

        if ("q".equalsIgnoreCase(input)) {
          break;
        }

        try {
          // adjust the index of the console input to be 0-based
          int choice = Integer.parseInt(input) - 1;
          if (choice >= 0 && choice < grammarFiles.size()) {
            String selectedGrammarFile = grammarFiles.get(choice);

            generateSentences(directoryPath, selectedGrammarFile);
          } else {
            System.out.println(PROMPT_OUT_OF_RANGE_ERROR);
          }
        } catch (NumberFormatException e) {
          System.out.println(PROMPT_INVALID_INPUT);
        }
      }
    } catch (Exception e) {
      System.out.println(PROMPT_LOADING_ERROR + e.getMessage());
    }
  }

  /**
   * Generates sentences based on the selected grammar file. The user is repeatedly prompted to
   * generate more sentences until they choose to stop.
   *
   * @param directoryPath The directory path where the grammar files are located.
   * @param grammarFile   The filename of the grammar file from which to generate sentences.
   */
  private void generateSentences(String directoryPath, String grammarFile) {
    String fullPath = Paths.get(directoryPath, grammarFile).toString();
    Grammar grammar = grammars.computeIfAbsent(fullPath, this::parseGrammarFromFile);

    boolean keepGenerating = true;
    while (keepGenerating) {
      String sentence = grammar.generate();
      System.out.println("\n" + sentence);
      System.out.println(PROMPT_CONTINUE);
      String again = scanner.nextLine();

      while (!"y".equalsIgnoreCase(again) && !"n".equalsIgnoreCase(again)) {
        System.out.println(PROMPT_INVALID_INPUT_FOR_CONTINUE);
        System.out.println(PROMPT_CONTINUE);
        again = scanner.nextLine();
      }

      keepGenerating = "y".equalsIgnoreCase(again);
    }
  }

  /**
   * Parses a grammar object from a file at the specified path.
   *
   * @param grammarFilePath The file path of the grammar to be parsed.
   * @return A Grammar object parsed from the specified file.
   * @throws RuntimeException if reading or parsing the grammar file fails.
   */
  public Grammar parseGrammarFromFile(String grammarFilePath) {
    try {
      String json = Files.readString(Path.of(grammarFilePath));

      return GrammarParser.parse(json);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns a string representation of the CLI instance, including its scanner and grammars.
   *
   * @return A string representation of the CLI.
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("CLI{");
    sb.append("scanner=").append(scanner);
    sb.append(", grammars=").append(grammars);
    sb.append('}');
    return sb.toString();
  }
}