package assignment6.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

class ClientTest {

  @Mock
  private Socket mockSocket;
  @Mock
  private ServerSocket mockServerSocket;

  @Mock
  private DataInputStream mockIn;

  @Mock
  private DataOutputStream mockOut;

  @Mock
  private BufferedReader mockStdIn;

  private Client client;

  @BeforeEach
  void setUp() throws IOException {
    MockitoAnnotations.openMocks(this);
    client = new Client("localhost", 1234, "test");
    mockServerSocket = mock(ServerSocket.class);
    when(mockServerSocket.accept()).thenReturn(mockSocket);
    when(mockSocket.getInputStream()).thenReturn(mock(InputStream.class));
    when(mockSocket.getOutputStream()).thenReturn(mock(OutputStream.class));
    client.setSocket(mockSocket);
    client.setIn(mockIn);
    client.setOut(mockOut);
    client.setStdIn(mockStdIn);
  }

  @Test
  void connectClient() {
    String host = "localhost";
    int port = 8080;
    Client client = new Client(host, port, "test");

    try (MockedConstruction<Socket> mocked = mockConstruction(Socket.class,
        (mock, context) -> when(mock.getInputStream()).thenReturn(mock(InputStream.class)))) {

      client.connectClient();

      assertTrue(client.isConnected());
    }
  }

  @Test
  void sendConnectMessage() throws IOException {
    doNothing().when(mockOut).writeUTF(anyString());

    client.sendConnectMessage();

    verify(mockOut, times(1)).writeUTF(anyString());
  }

  @Test
  void getHost() {
    assertEquals("localhost", client.getHost());
  }

  @Test
  void getPort() {
    assertEquals(1234, client.getPort());
  }

  @Test
  void getUsername() {
    assertEquals("test", client.getUsername());
  }

  @Test
  void getSocket() {
    assertSame(mockSocket, client.getSocket());
  }

  @Test
  void setSocket() {
    Socket newSocket = mock(Socket.class);

    client.setSocket(newSocket);

    assertSame(newSocket, client.getSocket());
  }

  @Test
  void getIn() {
    assertSame(mockIn, client.getIn());
  }

  @Test
  void setIn() {
    DataInputStream newIn = mock(DataInputStream.class);

    client.setIn(newIn);

    assertSame(newIn, client.getIn());
  }

  @Test
  void getOut() {
    assertSame(mockOut, client.getOut());
  }

  @Test
  void setOut() {
    DataOutputStream newOut = mock(DataOutputStream.class);

    client.setOut(newOut);

    assertSame(newOut, client.getOut());
  }

  @Test
  void getStdIn() {
    assertSame(mockStdIn, client.getStdIn());
  }

  @Test
  void setStdIn() {
    BufferedReader newStdIn = mock(BufferedReader.class);

    client.setStdIn(newStdIn);

    assertSame(newStdIn, client.getStdIn());
  }

  @Test
  void isConnected() {
    assertTrue(client.isConnected());
  }

  @Test
  void setConnected() {
    client.setConnected(false);

    assertFalse(client.isConnected());
  }

  @Test
  void testEquals() {
    Client sameClient = new Client("localhost", 1234, "test");
    sameClient.setSocket(mockSocket);
    sameClient.setIn(mockIn);
    sameClient.setOut(mockOut);
    sameClient.setStdIn(mockStdIn);

    Client differentClient = new Client("localhost", 5678, "test");

    assertEquals(client, sameClient);
    assertNotEquals(client, null);
    assertNotEquals(client, "string");
    assertEquals(client, client);
    assertNotEquals(client, differentClient);
  }

  @Test
  void testHashCode() {
    Client sameClient = new Client("localhost", 1234, "test");
    sameClient.setSocket(mockSocket);
    sameClient.setIn(mockIn);
    sameClient.setOut(mockOut);
    sameClient.setStdIn(mockStdIn);

    assertEquals(client.hashCode(), sameClient.hashCode());
  }

  @Test
  void testToString() {
    assertTrue(client.toString().contains("Client"));
    assertTrue(client.toString().contains("localhost"));
    assertTrue(client.toString().contains("1234"));
    assertTrue(client.toString().contains("test"));
  }
}