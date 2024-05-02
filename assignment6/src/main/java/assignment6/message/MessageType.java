package assignment6.message;

import java.text.MessageFormat;

/**
 * Represents the type of message that is being sent.
 */
public enum MessageType {
  CONNECT_MESSAGE(19),
  CONNECT_RESPONSE(20),
  DISCONNECT_MESSAGE(21),
  QUERY_CONNECTED_USERS(22),
  QUERY_USER_RESPONSE(23),
  BROADCAST_MESSAGE(24),
  DIRECT_MESSAGE(25),
  FAILED_MESSAGE(26),
  SEND_INSULT(27);

  private static final String MESSAGE_TYPE_NOT_FOUND_FORMAT = "No MessageType with value {0}";
  private final int value;

  /**
   * Constructor for MessageType.
   *
   * @param value the value of the MessageType
   */
  MessageType(int value) {
    this.value = value;
  }

  /**
   * Returns the MessageType associated with the given integer value.
   *
   * @param intValue the integer value of the MessageType
   * @return the MessageType associated with the given integer value
   * @throws IllegalArgumentException if the integer value does not correspond to a MessageType
   */
  public static MessageType fromInt(int intValue) {
    for (MessageType type : MessageType.values()) {
      if (type.getValue() == intValue) {
        return type;
      }
    }
    throw new IllegalArgumentException(
        MessageFormat.format(MESSAGE_TYPE_NOT_FOUND_FORMAT, intValue));
  }

  /**
   * Returns the value of the MessageType.
   *
   * @return the value of the MessageType
   */
  public int getValue() {
    return value;
  }

  /**
   * Returns a string representation of the MessageType.
   *
   * @return a string representation of the MessageType
   */
  @Override
  public String toString() {
    return "MessageType{" +
        "value=" + value +
        '}';
  }
}