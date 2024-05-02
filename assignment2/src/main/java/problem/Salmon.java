package problem;

/**
 * Represents a Salmon product in the supermarket inventory.
 * This class extends the Grocery class, adding specific properties relevant to Salmon.
 * It includes details such as the manufacturer, product name, price, minimum age for purchase (if applicable),
 * and the weight of the salmon.
 *
 * @author Arman Engin Sucu
 */
public class Salmon extends Grocery {

  /**
   * Constructs a new Salmon object with specified manufacturer, product name, price,
   * minimum age requirement, and weight.
   *
   * @param manufacturer The manufacturer of the salmon.
   * @param productName  The name of the salmon product.
   * @param price        The price of the salmon.
   * @param minAge       The minimum age required to purchase the salmon.
   * @param weight       The weight of the salmon in units (e.g., ounces, grams).
   */
  public Salmon(String manufacturer, String productName, double price, int minAge, double weight) {
    super(manufacturer, productName, price, minAge, weight);
  }

  /**
   * Constructs a new Salmon object with specified manufacturer, product name, price,
   * and weight. This constructor is used when there is no minimum age requirement for the salmon.
   *
   * @param manufacturer The manufacturer of the salmon.
   * @param productName  The name of the salmon product.
   * @param price        The price of the salmon.
   * @param weight       The weight of the salmon in units (e.g., ounces, grams).
   */
  public Salmon(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price, weight);
  }
}
