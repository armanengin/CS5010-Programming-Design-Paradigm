package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * The ConnectResponse class represents a ConnectResponse message.
 */
public class ConnectResponse extends Message {

  private static final String CONNECT_RESPONSE_FORMAT = "{0}: {1}";
  private boolean success;

  /**
   * Default constructor.
   */
  public ConnectResponse() {
    super(MessageType.CONNECT_RESPONSE, ChatroomProtocol.SERVER_NAME, null, null);
  }

  /**
   * Constructor with success and message.
   *
   * @param success the success
   * @param message the message
   */
  public ConnectResponse(boolean success, String message) {
    super(MessageType.CONNECT_RESPONSE, ChatroomProtocol.SERVER_NAME, null, message);
    this.success = success;
  }

  /**
   * Constructor with data input stream.
   *
   * @param in the data input stream
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    success = in.readBoolean();
    super.setMessage(in.readUTF());
  }

  /**
   * Send data to the output stream.
   *
   * @param out the data output stream
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(MessageType.CONNECT_RESPONSE.getValue());
    out.writeBoolean(success);
    out.writeUTF(super.getMessage());
    out.flush();
  }

  /**
   * Process the ConnectResponse.
   *
   * @return if the ConnectResponse is processed successfully
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(CONNECT_RESPONSE_FORMAT, super.getSender(), super.getMessage()));
    return !super.getMessage().equals(ChatroomProtocol.SERVER_DISCONNECT_MESSAGE)
        && !super.getMessage().equals(
        ChatroomProtocol.SERVER_FULL);
  }

  /**
   * Returns the success of this ConnectResponse.
   *
   * @return the success of this ConnectResponse
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * Sets the success of this ConnectResponse.
   *
   * @param success the new success of this ConnectResponse
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * Returns true if the given object is equal to this ConnectResponse, false otherwise.
   *
   * @param o the object to compare
   * @return true if the given object is equal to this ConnectResponse, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConnectResponse that)) {
      return false;
    }
    return isSuccess() == that.isSuccess();
  }

  /**
   * Returns the hashcode of this ConnectResponse.
   *
   * @return the hashcode of this ConnectResponse
   */
  @Override
  public int hashCode() {
    return Objects.hash(isSuccess());
  }

  /**
   * Returns the string representation of this ConnectResponse.
   *
   * @return the string representation of this ConnectResponse
   */
  @Override
  public String toString() {
    return "ConnectResponse{" +
        "success=" + success +
        '}';
  }
}
