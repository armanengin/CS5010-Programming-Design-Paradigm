package problem1;

import static problem1.NumericConstants.ZERO;

import java.io.IOException;
import java.util.HashMap;

/**
 * The {@code Main} class serves as the entry point for the application.
 * It processes command-line arguments, handles the initialization of necessary handlers and dispatchers,
 * and triggers the generation and dispatch of messages based on command-line options.
 *
 * @author Arman Engin Sucu
 */
public class Main {
  /**
   * The main method that serves as the entry point of the application.
   * It processes command-line arguments to configure the application's operation,
   * initializes the necessary components for handling CSV input and message dispatch,
   * and triggers the dispatch of messages based on the specified command options.
   *
   * @param args command-line arguments passed to the application. Expected arguments include options
   *             for specifying the input CSV file, email and letter templates, and the output directory.
   * @throws IOException if there is an issue reading from the CSV file or writing to output files.
   */
  public static void main(String args[]) throws IOException {
    for (int i = ZERO.getValue(); i < args.length; i++) {
      args[i] = args[i].trim();
    }
    // Initializes a CommandHandler to parse and store command-line arguments.
    CommandHandler commandHandler = new CommandHandler(args);
    HashMap<String, String> commandArguments = commandHandler.getCommandArguments();

    // Creates a CSVHandler to read customer data from the specified CSV file.
    CSVHandler csvHandler = new CSVHandler(commandArguments.get(CommandOption.CSV_FILE.getCommand()));

    // Initializes a MessageDispatcher with customer data, command arguments, and CSV headers.
    MessageDispatcher messageGenerator = new MessageDispatcher(csvHandler.getCustomerData(), commandArguments,
        csvHandler.getCsvHeaders());

    // Dispatches email messages based on the specified email template.
    messageGenerator.dispatchMessage(CommandOption.EMAIL_TEMPLATE.getCommand());
    // Dispatches letter messages based on the specified letter template.
    messageGenerator.dispatchMessage(CommandOption.LETTER_TEMPLATE.getCommand());
  }
}
