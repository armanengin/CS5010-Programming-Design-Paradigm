package problem;

/**
 * Represents a Shampoo product in the supermarket inventory.
 * This class extends the Household class, adding specific properties relevant to Shampoo.
 * It captures details such as the manufacturer, product name, price, minimum age for purchase (if applicable),
 * and the number of units in a package of shampoo.
 *
 * @author Arman Engin Sucu
 */
public class Shampoo extends Household {

  /**
   * Constructs a new Shampoo object with specified manufacturer, product name, price,
   * minimum age requirement, and number of units.
   *
   * @param manufacturer The manufacturer of the shampoo.
   * @param productName  The name of the shampoo product.
   * @param price        The price of the shampoo.
   * @param minAge       The minimum age required to purchase the shampoo.
   * @param numOfUnits   The number of units in the shampoo package.
   */
  public Shampoo(String manufacturer, String productName, double price, int minAge, int numOfUnits) {
    super(manufacturer, productName, price, minAge, numOfUnits);
  }

  /**
   * Constructs a new Shampoo object with specified manufacturer, product name, price,
   * and number of units. This constructor is used when there is no minimum age requirement for the shampoo.
   *
   * @param manufacturer The manufacturer of the shampoo.
   * @param productName  The name of the shampoo product.
   * @param price        The price of the shampoo.
   * @param numOfUnits   The number of units in the shampoo package.
   */
  public Shampoo(String manufacturer, String productName, double price, int numOfUnits) {
    super(manufacturer, productName, price, numOfUnits);
  }
}