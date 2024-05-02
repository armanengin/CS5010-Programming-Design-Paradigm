package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisconnectMessageTest {

  @Test
  public void testConstructorWithSenderSetsSender() {
    String expectedSender = "testSender";
    DisconnectMessage message = new DisconnectMessage(expectedSender);
    assertEquals(expectedSender, message.getSender());
  }

  @Test
  public void testConstructorWithSenderSetsReceiver() {
    DisconnectMessage message = new DisconnectMessage("testSender");
    assertEquals(ChatroomProtocol.SERVER_NAME, message.getReceiver());
  }

  @Test
  public void testParseSetsSender() throws IOException {
    String expectedSender = "testSender";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(expectedSender);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    DisconnectMessage message = new DisconnectMessage();
    message.parse(dis);

    assertEquals(expectedSender, message.getSender());
  }

  @Test
  public void testSendWritesCorrectMessageType() throws IOException {
    DisconnectMessage message = new DisconnectMessage("testSender");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.DISCONNECT_MESSAGE.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesCorrectSender() throws IOException {
    String expectedSender = "testSender";
    DisconnectMessage message = new DisconnectMessage(expectedSender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip the message type

    assertEquals(expectedSender, dis.readUTF());
  }

  @Test
  public void testProcessOutputsCorrectFormat() {
    String expectedSender = "testSender";
    DisconnectMessage message = new DisconnectMessage(expectedSender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // Reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains(expectedSender));
  }

  @Test
  public void testProcessReturnsFalse() {
    DisconnectMessage message = new DisconnectMessage("testSender");
    assertFalse(message.process());
  }
}