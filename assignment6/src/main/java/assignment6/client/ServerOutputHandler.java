package assignment6.client;

import assignment6.message.ChatroomProtocol;
import assignment6.message.Message;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * ServerOutputHandler class.
 */
public class ServerOutputHandler implements Runnable {

  private static final String SERVER_READ_ERROR_MESSAGE_FORMAT = "Error reading from server: {0}";
  private static final String DISCONNECTED_FROM_SERVER_MESSAGE = "Disconnected from server.";
  private final Client client;

  /**
   * Constructor for ServerOutputHandler.
   *
   * @param client the client
   */
  public ServerOutputHandler(Client client) {
    this.client = client;
  }

  /**
   * Run method for ServerOutputHandler.
   */
  @Override
  public void run() {
    try {
      while (client.isConnected()) {
        Message message = ChatroomProtocol.processInput(client.getIn());
        client.setConnected(message.process());
      }
    } catch (IOException e) {
      System.out.println(MessageFormat.format(SERVER_READ_ERROR_MESSAGE_FORMAT, e.getMessage()));
    } finally {
      System.out.println(DISCONNECTED_FROM_SERVER_MESSAGE);
    }
  }

  /**
   * Gets the client.
   *
   * @return the client
   */
  public Client getClient() {
    return client;
  }

  /**
   * Returns true if the objects are equal, false otherwise.
   *
   * @param o the object to compare
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ServerOutputHandler that)) {
      return false;
    }
    return Objects.equals(client, that.client);
  }

  /**
   * Returns the hashcode of the client.
   *
   * @return the hashcode of the client
   */
  @Override
  public int hashCode() {
    return Objects.hash(client);
  }

  /**
   * Returns the string representation of the client.
   *
   * @return the string representation of the client
   */
  @Override
  public String toString() {
    return "ServerOutputHandler{" +
        "client=" + client +
        '}';
  }
}
