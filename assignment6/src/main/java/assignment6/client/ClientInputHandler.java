package assignment6.client;

import assignment6.message.BroadcastMessage;
import assignment6.message.DirectMessage;
import assignment6.message.DisconnectMessage;
import assignment6.message.Message;
import assignment6.message.QueryConnectedUsers;
import assignment6.message.SendInsult;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * Handles user input for a client.
 */
public class ClientInputHandler implements Runnable {

  public static final String COMMAND_ERROR =
      "Command not recognized. Type ? for a list of available commands.";
  private static final int COMMAND_TYPE_INDEX = 0;
  private static final int RECIPIENT_INDEX = 1;
  private static final int DATA_INDEX = 2;
  private static final String LOGOFF_COMMAND = "logoff";
  private static final String WHO_COMMAND = "who";
  private static final String MESSAGE_COMMAND = "@";
  private static final String HELP_COMMAND = "?";
  private static final String INSULT_COMMAND = "!";
  private static final String BROADCAST_RECIPIENT = "all";
  private static final String USER_READ_ERROR_FORMAT = "Error reading user input: {0}";
  private static final String USAGE =
      """
            Available commands:
              @ <recipient> <message> - Send a direct message to a recipient
              @ all <message> - Send a broadcast message to all connected users
              ! <recipient> - sends an insult to a specified user
              logoff - Disconnect from the server
              who - Query connected users
          """;
  private final Client client;

  /**
   * Constructs a new ClientInputHandler with the given client.
   *
   * @param client the client to handle input for
   */
  public ClientInputHandler(Client client) {
    this.client = client;
  }

  /**
   * Reads user input from the client and processes it.
   */
  @Override
  public void run() {
    try {
      while (client.isConnected()) {
        String userInput = client.getStdIn().readLine();
        processUserInput(userInput);
      }
    } catch (IOException e) {
      System.out.println(MessageFormat.format(USER_READ_ERROR_FORMAT, e.getMessage()));
    }
  }

  /**
   * Processes the given user input.
   *
   * @param userInput the user input to process
   * @throws IOException if an I/O error occurs
   */
  private void processUserInput(String userInput) throws IOException {
    CommandParser parser = CommandParser.getInstance();
    List<String> parsedInput = parser.parse(userInput);
    String commandType = parsedInput.get(COMMAND_TYPE_INDEX);
    String recipient = parsedInput.get(RECIPIENT_INDEX);
    String data = parsedInput.get(DATA_INDEX);

    Message message = createMessage(commandType, recipient, data);
    if (message != null) {
      message.send(client.getOut());
    }

    if (!client.isConnected()) {
      client.getStdIn().close();
    }
  }

  /**
   * Creates a message based on the given command type, recipient, and data.
   *
   * @param commandType the type of command
   * @param recipient   the recipient of the message
   * @param data        the data to send
   * @return the message to send
   */
  private Message createMessage(String commandType, String recipient, String data) {
    return switch (commandType) {
      case MESSAGE_COMMAND -> recipient.equals(BROADCAST_RECIPIENT) ?
          new BroadcastMessage(client.getUsername(), data) :
          new DirectMessage(client.getUsername(), recipient, data);
      case LOGOFF_COMMAND -> {
        client.setConnected(false);
        yield new DisconnectMessage(client.getUsername());
      }
      case WHO_COMMAND -> new QueryConnectedUsers(client.getUsername());
      case HELP_COMMAND -> {
        System.out.println(USAGE);
        yield null;
      }
      case INSULT_COMMAND -> new SendInsult(client.getUsername(), recipient);
      default -> {
        System.out.println(COMMAND_ERROR);
        yield null;
      }
    };
  }

  /**
   * Returns true if the given object is a ClientInputHandler with the same client.
   *
   * @param o the object to compare
   * @return true if the given object is a ClientInputHandler with the same client
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClientInputHandler that)) {
      return false;
    }
    return Objects.equals(client, that.client);
  }

  /**
   * Returns the hashcode of the ClientInputHandler.
   *
   * @return the hashcode of the ClientInputHandler
   */
  @Override
  public int hashCode() {
    return Objects.hash(client);
  }

  /**
   * Returns the string representation of the ClientInputHandler.
   *
   * @return the string representation of the ClientInputHandler
   */
  @Override
  public String toString() {
    return "ClientInputHandler{" +
        "client=" + client +
        '}';
  }
}