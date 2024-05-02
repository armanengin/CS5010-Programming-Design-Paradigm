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

class QueryConnectedUsersTest {
  // Test default constructor without parameters
  @Test
  public void testDefaultConstructorSenderIsNull() {
    QueryConnectedUsers message = new QueryConnectedUsers();
    assertNull(message.getSender());
  }

  @Test
  public void testDefaultConstructorReceiverIsServerName() {
    QueryConnectedUsers message = new QueryConnectedUsers();
    assertEquals(ChatroomProtocol.SERVER_NAME, message.getReceiver());
  }

  // Test constructor with sender parameter
  @Test
  public void testConstructorWithSenderSetsSenderCorrectly() {
    String sender = "Client1";
    QueryConnectedUsers message = new QueryConnectedUsers(sender);
    assertEquals(sender, message.getSender());
  }

  @Test
  public void testConstructorWithSenderSetsReceiverCorrectly() {
    QueryConnectedUsers message = new QueryConnectedUsers("Client1");
    assertEquals(ChatroomProtocol.SERVER_NAME, message.getReceiver());
  }

  // Test for parsing input stream
  @Test
  public void testParseSetsSenderCorrectly() throws IOException {
    String sender = "Client1";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(sender);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    QueryConnectedUsers message = new QueryConnectedUsers();
    message.parse(dis);

    assertEquals(sender, message.getSender());
  }

  // Test for sending output stream
  @Test
  public void testSendWritesMessageTypeCorrectly() throws IOException {
    QueryConnectedUsers message = new QueryConnectedUsers("Client1");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.QUERY_CONNECTED_USERS.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesSenderCorrectly() throws IOException {
    String sender = "Client1";
    QueryConnectedUsers message = new QueryConnectedUsers(sender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type to read the sender

    assertEquals(sender, dis.readUTF());
  }

  // Test for processing the message
  @Test
  public void testProcessReturnsTrue() {
    QueryConnectedUsers message = new QueryConnectedUsers("Client1");
    assertTrue(message.process());
  }

  @Test
  public void testProcessOutputFormat() {
    String sender = "Client1";
    QueryConnectedUsers message = new QueryConnectedUsers(sender);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains("Client: QUERY_CONNECTED_USERS Client1"));
  }
}