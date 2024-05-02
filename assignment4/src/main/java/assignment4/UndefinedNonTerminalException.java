package assignment4;

/**
 * A class representing an UndefinedNonTerminalException which is thrown when a non-terminal is
 * undefined.
 */
public class UndefinedNonTerminalException extends RuntimeException {

  /**
   * Constructs a new UndefinedNonTerminalException with the specified message.
   *
   * @param message the message of this UndefinedNonTerminalException
   */
  public UndefinedNonTerminalException(String message) {
    super(message);
  }
}