package problem;

/**
 * Custom exception class representing a situation where there are not enough items in stock to fulfill a request.
 * This exception is typically thrown when an attempt is made to purchase or reserve more units of an item than are available in stock.
 *
 * @author Arman Engin Sucu
 */
public class NotEnoughItemInStockException extends Exception {

  /**
   * Constructs a new NotEnoughItemInStockException with no detail message.
   */
  public NotEnoughItemInStockException() {}

  /**
   * Constructs a new NotEnoughItemInStockException with the specified detail message.
   * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
   *
   * @param message The detail message which is saved for later retrieval.
   */
  public NotEnoughItemInStockException(String message) {
    super(message);
  }
}