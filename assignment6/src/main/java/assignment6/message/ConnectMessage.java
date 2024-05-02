package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * ConnectMessage class.
 */
public class ConnectMessage extends Message {

  private static final String CONNECT_MESSAGE_FORMAT = "{0}: CONNECT_MESSAGE";

  /**
   * Default constructor.
   */
  public ConnectMessage() {
    super(MessageType.CONNECT_MESSAGE, null, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Constructor.
   *
   * @param sender the sender of the message
   */
  public ConnectMessage(String sender) {
    super(MessageType.CONNECT_MESSAGE, sender, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Parse the ConnectMessage.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setSender(in.readUTF());
    super.setReceiver(ChatroomProtocol.SERVER_NAME);
  }

  /**
   * Send the ConnectMessage.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(MessageType.CONNECT_MESSAGE.getValue());
    out.writeUTF(super.getSender());
    out.flush();
  }

  /**
   * Process the ConnectMessage.
   *
   * @return true
   */
  @Override
  public boolean process() {
    System.out.println(MessageFormat.format(CONNECT_MESSAGE_FORMAT, super.getSender()));
    return true;
  }
}