package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * FailedMessage class.
 */
public class FailedMessage extends Message {

  private static final String FAILED_MESSAGE_FORMAT = "{0}: {1}";

  /**
   * Constructor.
   */
  public FailedMessage() {
    super(MessageType.FAILED_MESSAGE, ChatroomProtocol.SERVER_NAME, null, null);
  }

  /**
   * Constructor.
   *
   * @param receiver The receiver of the message.
   * @param message  The message.
   */
  public FailedMessage(String receiver, String message) {
    super(MessageType.FAILED_MESSAGE, ChatroomProtocol.SERVER_NAME, receiver, message);
  }

  /**
   * Parse the FailedMessage from the input stream.
   *
   * @param in The input stream.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setMessage(in.readUTF());
  }

  /**
   * Send the FailedMessage to the output stream.
   *
   * @param out The output stream.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(super.getMessageType().getValue());
    out.writeUTF(super.getMessage());
  }

  /**
   * Process the FailedMessage.
   *
   * @return true
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(FAILED_MESSAGE_FORMAT, super.getSender(), super.getMessage()));
    return true;
  }

}
