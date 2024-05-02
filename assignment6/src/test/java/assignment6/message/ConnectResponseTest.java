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

class ConnectResponseTest {

  @Test
  public void testConstructorWithSuccess() {
    ConnectResponse response = new ConnectResponse(true, "Success message");
    assertTrue(response.isSuccess());
  }

  @Test
  public void testConstructorWithMessage() {
    String message = "Success message";
    ConnectResponse response = new ConnectResponse(true, message);
    assertEquals(message, response.getMessage());
  }

  @Test
  public void testParseSuccessField() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeBoolean(true);
    dos.writeUTF("Parsed message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    ConnectResponse response = new ConnectResponse();
    response.parse(dis);

    assertTrue(response.isSuccess());
  }

  @Test
  public void testParseMessageField() throws IOException {
    String expectedMessage = "Parsed message";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeBoolean(true);
    dos.writeUTF(expectedMessage);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    ConnectResponse response = new ConnectResponse();
    response.parse(dis);

    assertEquals(expectedMessage, response.getMessage());
  }

  @Test
  public void testSendWritesCorrectSuccess() throws IOException {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    response.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    dis.readInt(); // Skip the message type
    assertTrue(dis.readBoolean());
  }

  @Test
  public void testSendWritesCorrectMessage() throws IOException {
    String message = "Test message";
    ConnectResponse response = new ConnectResponse(true, message);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    response.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    dis.readInt(); // Skip the message type
    dis.readBoolean(); // Skip the success boolean
    assertEquals(message, dis.readUTF());
  }

  @Test
  public void testProcessReturnsTrueForSuccess() {
    ConnectResponse response = new ConnectResponse(true, "You are connected.");
    assertTrue(response.process());
  }

  @Test
  public void testProcessReturnsFalseForDisconnectMessage() {
    ConnectResponse response = new ConnectResponse(false, ChatroomProtocol.SERVER_DISCONNECT_MESSAGE);
    assertFalse(response.process());
  }

  @Test
  public void testProcessReturnsFalseForServerFullMessage() {
    ConnectResponse response = new ConnectResponse(false, ChatroomProtocol.SERVER_FULL);
    assertFalse(response.process());
  }

  @Test
  public void testProcessOutputFormat() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    ConnectResponse response = new ConnectResponse(true, "You are connected.");
    response.process();
    System.setOut(System.out); // reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains("Server: You are connected."));
  }

  // Test for setSuccess and isSuccess
  @Test
  public void testSetSuccess() {
    ConnectResponse response = new ConnectResponse();
    response.setSuccess(true);
    assertTrue(response.isSuccess());
  }

  // Tests for equals method
  @Test
  public void testEqualsWithSameObject() {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    assertTrue(response.equals(response));
  }

  @Test
  public void testEqualsWithIdenticalObjects() {
    ConnectResponse response1 = new ConnectResponse(true, "Test message");
    ConnectResponse response2 = new ConnectResponse(true, "Test message");
    assertTrue(response1.equals(response2));
  }

  @Test
  public void testEqualsWithDifferentObjects() {
    ConnectResponse response1 = new ConnectResponse(true, "Test message");
    ConnectResponse response2 = new ConnectResponse(false, "Test message");
    assertFalse(response1.equals(response2));
  }

  @Test
  public void testEqualsWithNull() {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    assertFalse(response.equals(null));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    assertFalse(response.equals(new Object()));
  }

  // Test for hashCode method
  @Test
  public void testHashCodeConsistency() {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    assertEquals(response.hashCode(), response.hashCode());
  }

  @Test
  public void testHashCodeEqualityForIdenticalObjects() {
    ConnectResponse response1 = new ConnectResponse(true, "Test message");
    ConnectResponse response2 = new ConnectResponse(true, "Test message");
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  public void testHashCodeInequalityForDifferentObjects() {
    ConnectResponse response1 = new ConnectResponse(true, "Test message");
    ConnectResponse response2 = new ConnectResponse(false, "Test message");
    assertNotEquals(response1.hashCode(), response2.hashCode());
  }

  // Test for toString method
  @Test
  public void testToStringFormat() {
    ConnectResponse response = new ConnectResponse(true, "Test message");
    String expected = "ConnectResponse{success=true}";
    assertEquals(expected, response.toString());
  }
}