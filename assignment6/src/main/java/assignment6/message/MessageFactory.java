package assignment6.message;

/**
 * A factory class to create messages.
 */
public class MessageFactory {

  /**
   * Private constructor to prevent instantiation.
   */
  private MessageFactory() {
  }

  /**
   * Creates a message based on the message type.
   *
   * @param messageType the message type
   * @return the message
   */
  public static Message createMessage(MessageType messageType) {
    return switch (messageType) {
      case CONNECT_MESSAGE -> new ConnectMessage();
      case CONNECT_RESPONSE -> new ConnectResponse();
      case DISCONNECT_MESSAGE -> new DisconnectMessage();
      case QUERY_CONNECTED_USERS -> new QueryConnectedUsers();
      case QUERY_USER_RESPONSE -> new QueryUserResponse();
      case BROADCAST_MESSAGE -> new BroadcastMessage();
      case DIRECT_MESSAGE -> new DirectMessage();
      case FAILED_MESSAGE -> new FailedMessage();
      case SEND_INSULT -> new SendInsult();
    };
  }
}
