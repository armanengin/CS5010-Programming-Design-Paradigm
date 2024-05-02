package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * The QueryUserResponse class represents a QueryUserResponse message.
 */
public class QueryUserResponse extends Message {

  private static final String QUERY_USER_RESPONSE_FORMAT = "Server: {0} users connected.";
  private int numberOfUsers;
  private String[] users;

  /**
   * Default constructor.
   */
  public QueryUserResponse() {
    super(MessageType.QUERY_USER_RESPONSE, ChatroomProtocol.SERVER_NAME, null, null);
  }

  /**
   * Constructor with number of users and users.
   *
   * @param numberOfUsers the number of users
   * @param users         the users
   */
  public QueryUserResponse(int numberOfUsers, String... users) {
    super(MessageType.QUERY_USER_RESPONSE, ChatroomProtocol.SERVER_NAME, null, null);
    this.numberOfUsers = numberOfUsers;
    this.users = users;
  }

  /**
   * Parse the message.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    numberOfUsers = in.readInt();
    users = new String[numberOfUsers];
    for (int i = 0; i < numberOfUsers; i++) {
      users[i] = in.readUTF();
    }
  }

  /**
   * Send the message.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(super.getMessageType().getValue());
    out.writeInt(numberOfUsers);
    for (int i = 0; i < numberOfUsers; i++) {
      out.writeUTF(users[i]);
    }
    out.flush();
  }

  /**
   * Process the message.
   *
   * @return true
   */
  @Override
  public boolean process() {
    System.out.println(MessageFormat.format(QUERY_USER_RESPONSE_FORMAT, numberOfUsers));
    for (int i = 0; i < numberOfUsers; i++) {
      System.out.println(users[i]);
    }
    return true;
  }

  /**
   * Get the number of users.
   *
   * @return the number of users
   */
  public int getNumberOfUsers() {
    return numberOfUsers;
  }

  /**
   * Set the number of users.
   *
   * @param numberOfUsers the number of users
   */
  public void setNumberOfUsers(int numberOfUsers) {
    this.numberOfUsers = numberOfUsers;
  }

  /**
   * Get the users.
   *
   * @return the users
   */
  public String[] getUsers() {
    return users;
  }

  /**
   * Set the users.
   *
   * @param users the users
   */
  public void setUsers(String[] users) {
    this.users = users;
  }

  /**
   * Check if the object is equal to this QueryUserResponse, false otherwise.
   *
   * @param o the object
   * @return true if the object is equal to this QueryUserResponse, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryUserResponse that)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return getNumberOfUsers() == that.getNumberOfUsers() && Arrays.equals(getUsers(),
        that.getUsers());
  }

  /**
   * Get the hashcode of this QueryUserResponse.
   *
   * @return the hashcode of this QueryUserResponse
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getNumberOfUsers());
    result = 31 * result + Arrays.hashCode(getUsers());
    return result;
  }

  /**
   * Get the string representation of this QueryUserResponse.
   *
   * @return the string representation of this QueryUserResponse
   */
  @Override
  public String toString() {
    return "QueryUserResponse{" +
        "numberOfUsers=" + numberOfUsers +
        ", users=" + Arrays.toString(users) +
        '}';
  }
}
