package assignment6.message;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The ChatroomProtocol class is a utility class that provides methods for processing input from the
 * client.
 */
public final class ChatroomProtocol {

  public static final String SERVER_NAME = "Server";
  public static final String SERVER_DISCONNECT_MESSAGE = "You are no longer connected.";
  public static final String SERVER_FULL = "Server is full.";

  /**
   * Private constructor to prevent instantiation.
   */
  private ChatroomProtocol() {
  }

  /**
   * Process the input from the client.
   *
   * @param in the input stream from the client
   * @return the message from the client
   * @throws IOException if an I/O error occurs
   */
  public static Message processInput(DataInputStream in) throws IOException {
    int messageTypeId = in.readInt();
    MessageType messageType = MessageType.fromInt(messageTypeId);

    Message message = MessageFactory.createMessage(messageType);

    message.parse(in);

    return message;
  }
}
