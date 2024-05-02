package assignment6.client;

import java.io.IOException;

/**
 * The main class for the client.
 */
public class ClientMain {

  private static final int ARGUMENTS_LENGTH = 3;
  private static final int HOST_INDEX = 0;
  private static final int PORT_INDEX = 1;
  private static final int USERNAME_INDEX = 2;
  private static final String USAGE_MESSAGE = "Usage: java Client <host> <port> <username>";
  private static final String CONNECTION_ERROR = "Failed to connect to server";

  /**
   * Starts the client.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    if (args.length < ARGUMENTS_LENGTH) {
      System.out.println(USAGE_MESSAGE);
      return;
    }

    String host = args[HOST_INDEX];
    int port = Integer.parseInt(args[PORT_INDEX]);
    String username = args[USERNAME_INDEX];

    Client client = new Client(host, port, username);
    try {
      client.start();
    } catch (IOException e) {
      System.err.println(CONNECTION_ERROR);
    }
  }
}
