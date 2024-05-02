package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * SendInsult class.
 */
public class SendInsult extends Message {

  private static final String INSULT_MESSAGE_FORMAT = "{0} -> {1}: {2}";

  /**
   * Default constructor.
   */
  public SendInsult() {
    super(MessageType.SEND_INSULT, null, null, null);
  }

  /**
   * Constructor with parameters.
   *
   * @param sender   the sender of the message
   * @param receiver the receiver of the message
   */
  public SendInsult(String sender, String receiver) {
    super(MessageType.SEND_INSULT, sender, receiver, null);
  }

  /**
   * Parse the SendInsult message.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setSender(in.readUTF());
    super.setReceiver(in.readUTF());
  }

  /**
   * Send the SendInsult message.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(MessageType.SEND_INSULT.getValue());
    out.writeUTF(super.getSender());
    out.writeUTF(super.getReceiver());
  }

  /**
   * Process the SendInsult message.
   *
   * @return true if the SendInsult message is processed successfully
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(INSULT_MESSAGE_FORMAT, super.getSender(), super.getReceiver(),
            super.getMessage()));
    return true;
  }
}
