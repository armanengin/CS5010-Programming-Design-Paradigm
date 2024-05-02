package problem1;

/**
 * Enum representing numeric constants for improved code readability and maintainability.
 * This enum helps avoid the use of "magic numbers" in the codebase by providing a clear
 * and understandable reference to commonly used numeric values.
 *
 * @author Arman Engin Sucu
 */
public enum NumericConstants {
  /**
   * Numeric constant representing the value zero (0).
   * Can be used in place of hardcoded zeros to improve the readability of the code.
   */
  ZERO(0),
  /**
   * Numeric constant representing the value one (1).
   * Useful for operations where the concept of 'one' is significant and improves
   * the clarity of the code over using a literal '1'.
   */
  ONE(1);

  private final int value;

  /**
   * Constructor for the numeric constant.
   * @param value The integer value associated with the constant.
   */
  NumericConstants(int value) {
    this.value = value;
  }

  /**
   * Retrieves the integer value associated with the numeric constant.
   * @return The integer value of the constant.
   */
  public int getValue() {
    return value;
  }

  /**
   * Returns a string representation of the numeric constant.
   * This includes the name of the enum constant and its associated numeric value,
   * making it easier to identify and understand the constant's purpose and value
   * when debugging or logging.
   *
   * @return A string that represents the numeric constant, including its name and value.
   */
  @Override
  public String toString() {
    return "NumericConstants{" +
        "value=" + value +
        '}';
  }
}