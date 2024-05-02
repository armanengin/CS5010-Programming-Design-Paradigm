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

class ConnectMessageTest {
  @Test
  public void testConnectMessageConstructorWithoutParametersSetsCorrectReceiver() {
    ConnectMessage message = new ConnectMessage();
    assertEquals(ChatroomProtocol.SERVER_NAME, message.getReceiver());
  }

  @Test
  public void testConnectMessageConstructorWithSenderSetsSender() {
    String expectedSender = "testSender";
    ConnectMessage message = new ConnectMessage(expectedSender);
    assertEquals(expectedSender, message.getSender());
  }

  @Test
  public void testParseCorrectlyAssignsSender() throws IOException {
    String expectedSender = "testSender";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(expectedSender);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    ConnectMessage message = new ConnectMessage();
    message.parse(dis);

    assertEquals(expectedSender, message.getSender());
  }

  @Test
  public void testSendWritesMessageType() throws IOException {
    ConnectMessage message = new ConnectMessage("testSender");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    int messageType = dis.readInt();
    assertEquals(MessageType.CONNECT_MESSAGE.getValue(), messageType);
  }

  @Test
  public void testSendWritesSender() throws IOException {
    String expectedSender = "testSender";
    ConnectMessage message = new ConnectMessage(expectedSender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type

    String sender = dis.readUTF();
    assertEquals(expectedSender, sender);
  }

  @Test
  public void testProcessOutputContainsSender() {
    String expectedSender = "testSender";
    ConnectMessage message = new ConnectMessage(expectedSender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains(expectedSender));
  }

  @Test
  public void testProcessOutputContainsMessageType() {
    String expectedSender = "testSender";
    ConnectMessage message = new ConnectMessage(expectedSender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains("CONNECT_MESSAGE"));
  }
}