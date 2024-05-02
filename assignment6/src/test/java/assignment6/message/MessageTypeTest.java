package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTypeTest {

  // Tests for getValue()
  @Test
  public void testConnectMessageValue() {
    assertEquals(19, MessageType.CONNECT_MESSAGE.getValue());
  }

  @Test
  public void testConnectResponseValue() {
    assertEquals(20, MessageType.CONNECT_RESPONSE.getValue());
  }

  @Test
  public void testDisconnectMessageValue() {
    assertEquals(21, MessageType.DISCONNECT_MESSAGE.getValue());
  }

  @Test
  public void testQueryConnectedUsersValue() {
    assertEquals(22, MessageType.QUERY_CONNECTED_USERS.getValue());
  }

  @Test
  public void testQueryUserResponseValue() {
    assertEquals(23, MessageType.QUERY_USER_RESPONSE.getValue());
  }

  @Test
  public void testBroadcastMessageValue() {
    assertEquals(24, MessageType.BROADCAST_MESSAGE.getValue());
  }

  @Test
  public void testDirectMessageValue() {
    assertEquals(25, MessageType.DIRECT_MESSAGE.getValue());
  }

  @Test
  public void testFailedMessageValue() {
    assertEquals(26, MessageType.FAILED_MESSAGE.getValue());
  }

  @Test
  public void testSendInsultValue() {
    assertEquals(27, MessageType.SEND_INSULT.getValue());
  }

  // Tests for fromInt()
  @Test
  public void testFromIntConnectMessage() {
    assertEquals(MessageType.CONNECT_MESSAGE, MessageType.fromInt(19));
  }

  @Test
  public void testFromIntConnectResponse() {
    assertEquals(MessageType.CONNECT_RESPONSE, MessageType.fromInt(20));
  }

  @Test
  public void testFromIntDisconnectMessage() {
    assertEquals(MessageType.DISCONNECT_MESSAGE, MessageType.fromInt(21));
  }

  @Test
  public void testFromIntQueryConnectedUsers() {
    assertEquals(MessageType.QUERY_CONNECTED_USERS, MessageType.fromInt(22));
  }

  @Test
  public void testFromIntQueryUserResponse() {
    assertEquals(MessageType.QUERY_USER_RESPONSE, MessageType.fromInt(23));
  }

  @Test
  public void testFromIntBroadcastMessage() {
    assertEquals(MessageType.BROADCAST_MESSAGE, MessageType.fromInt(24));
  }

  @Test
  public void testFromIntDirectMessage() {
    assertEquals(MessageType.DIRECT_MESSAGE, MessageType.fromInt(25));
  }

  @Test
  public void testFromIntFailedMessage() {
    assertEquals(MessageType.FAILED_MESSAGE, MessageType.fromInt(26));
  }

  @Test
  public void testFromIntSendInsult() {
    assertEquals(MessageType.SEND_INSULT, MessageType.fromInt(27));
  }

  // Test for invalid value
  @Test
  public void testFromIntWithInvalidValue() {
    int invalidValue = 100;
    Exception exception = assertThrows(IllegalArgumentException.class, () -> MessageType.fromInt(invalidValue));
    String expectedMessage = MessageFormat.format("No MessageType with value {0}", invalidValue);
    assertEquals(expectedMessage, exception.getMessage());
  }

  // Tests for toString()
  @Test
  public void testToStringConnectMessage() {
    assertEquals("MessageType{value=19}", MessageType.CONNECT_MESSAGE.toString());
  }

  @Test
  public void testToStringConnectResponse() {
    assertEquals("MessageType{value=20}", MessageType.CONNECT_RESPONSE.toString());
  }

  @Test
  public void testToStringDisconnectMessage() {
    assertEquals("MessageType{value=21}", MessageType.DISCONNECT_MESSAGE.toString());
  }

  @Test
  public void testToStringQueryConnectedUsers() {
    assertEquals("MessageType{value=22}", MessageType.QUERY_CONNECTED_USERS.toString());
  }

  @Test
  public void testToStringQueryUserResponse() {
    assertEquals("MessageType{value=23}", MessageType.QUERY_USER_RESPONSE.toString());
  }

  @Test
  public void testToStringBroadcastMessage() {
    assertEquals("MessageType{value=24}", MessageType.BROADCAST_MESSAGE.toString());
  }

  @Test
  public void testToStringDirectMessage() {
    assertEquals("MessageType{value=25}", MessageType.DIRECT_MESSAGE.toString());
  }

  @Test
  public void testToStringFailedMessage() {
    assertEquals("MessageType{value=26}", MessageType.FAILED_MESSAGE.toString());
  }

  @Test
  public void testToStringSendInsult() {
    assertEquals("MessageType{value=27}", MessageType.SEND_INSULT.toString());
  }
}