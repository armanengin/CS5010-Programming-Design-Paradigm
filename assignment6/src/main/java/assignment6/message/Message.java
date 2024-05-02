package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * The Message class represents a message.
 */
public abstract class Message {

  private MessageType messageType;
  private String sender;
  private String receiver;
  private String message;

  /**
   * Constructor for Message
   *
   * @param messageType the type of message
   * @param sender      the sender of the message
   * @param receiver    the receiver of the message
   * @param message     the message
   */
  public Message(MessageType messageType, String sender, String receiver, String message) {
    this.messageType = messageType;
    this.sender = sender;
    this.receiver = receiver;
    this.message = message;
  }

  /**
   * Process the message.
   *
   * @return true
   */
  public abstract boolean process();

  /**
   * Parse the message.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  public abstract void parse(DataInputStream in) throws IOException;

  /**
   * Send the message.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  public abstract void send(DataOutputStream out) throws IOException;

  /**
   * Get the message type.
   *
   * @return the message type
   */
  public MessageType getMessageType() {
    return messageType;
  }

  /**
   * Set the message type.
   *
   * @param messageType the message type
   */
  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  /**
   * Get the sender.
   *
   * @return the sender
   */
  public String getSender() {
    return sender;
  }

  /**
   * Set the sender.
   *
   * @param sender the sender
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Get the receiver.
   *
   * @return the receiver
   */
  public String getReceiver() {
    return receiver;
  }

  /**
   * Set the receiver.
   *
   * @param receiver the receiver
   */
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  /**
   * Get the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set the message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Check if the message is equal to another object.
   *
   * @param o the object
   * @return true if the message is equal to the object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Message message1)) {
      return false;
    }
    return getMessageType() == message1.getMessageType() && Objects.equals(getSender(),
        message1.getSender()) && Objects.equals(getReceiver(), message1.getReceiver())
        && Objects.equals(getMessage(), message1.getMessage());
  }

  /**
   * Get the hash code of the message.
   *
   * @return the hash code of the message
   */
  @Override
  public int hashCode() {
    return Objects.hash(getMessageType(), getSender(), getReceiver(), getMessage());
  }

  /**
   * Get the string representation of the message.
   *
   * @return the string representation of the message
   */
  @Override
  public String toString() {
    return "Message{" +
        "messageType=" + messageType +
        ", sender='" + sender + '\'' +
        ", receiver='" + receiver + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}