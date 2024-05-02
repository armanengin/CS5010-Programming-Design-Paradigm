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

class SendInsultTest {

  // Constructor Tests
  @Test
  public void testDefaultConstructorSenderIsNull() {
    SendInsult message = new SendInsult();
    assertNull(message.getSender());
  }

  @Test
  public void testDefaultConstructorReceiverIsNull() {
    SendInsult message = new SendInsult();
    assertNull(message.getReceiver());
  }

  @Test
  public void testConstructorWithParametersSetsSender() {
    SendInsult message = new SendInsult("sender", "receiver");
    assertEquals("sender", message.getSender());
  }

  @Test
  public void testConstructorWithParametersSetsReceiver() {
    SendInsult message = new SendInsult("sender", "receiver");
    assertEquals("receiver", message.getReceiver());
  }

  // Parse Method Tests
  @Test
  public void testParseSetsSenderCorrectly() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("sender");
    dos.writeUTF("receiver");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    SendInsult message = new SendInsult();
    message.parse(dis);
    assertEquals("sender", message.getSender());
  }

  @Test
  public void testParseSetsReceiverCorrectly() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("sender");
    dos.writeUTF("receiver");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    SendInsult message = new SendInsult();
    message.parse(dis);
    assertEquals("receiver", message.getReceiver());
  }

  // Send Method Tests
  @Test
  public void testSendWritesMessageTypeCorrectly() throws IOException {
    SendInsult message = new SendInsult("sender", "receiver");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.SEND_INSULT.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesSenderCorrectly() throws IOException {
    SendInsult message = new SendInsult("sender", "receiver");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    assertEquals("sender", dis.readUTF());
  }

  @Test
  public void testSendWritesReceiverCorrectly() throws IOException {
    SendInsult message = new SendInsult("sender", "receiver");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readUTF(); // Skip sender
    assertEquals("receiver", dis.readUTF());
  }

  // Process Method Tests
  @Test
  public void testProcessReturnsTrue() {
    SendInsult message = new SendInsult("sender", "receiver");
    assertTrue(message.process());
  }

  @Test
  public void testProcessOutputFormat() {
    String[] args = {"sender -> receiver: You're a slow turtle!"};
    SendInsult message = new SendInsult("sender", "receiver");
    message.setMessage("You're a slow turtle!");

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains(args[0]));
  }
}