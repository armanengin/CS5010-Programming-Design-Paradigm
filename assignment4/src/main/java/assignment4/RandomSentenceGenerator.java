package assignment4;

/**
 * A class representing a RandomSentenceGenerator which can generate random sentences using a
 * grammar.
 */
public class RandomSentenceGenerator {

  /**
   * Defines the usage instructions for the RandomSentenceGenerator program, indicating how to
   * execute the program with the required command-line argument.
   */
  private static final String USAGE = "Usage: ./assignment4-1.0.jar <grammar directory path>";

  /**
   * Main method that initiates the process of generating random sentences. It expects a single
   * command-line argument specifying the path to a directory containing grammar files. The method
   * retrieves this directory path, loads the grammars, and then uses the {@link CLI} class to
   * interact with the user, allowing them to select a grammar and generate random sentences based
   * on it.
   *
   * @param args Command-line arguments, where the first argument should be the path to the
   *             directory containing grammar files.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println(USAGE);
      return;
    }

    String directoryPath = args[0];
    CLI cli = CLI.getInstance();
    cli.loadAndSelectGrammar(directoryPath);
  }
}
