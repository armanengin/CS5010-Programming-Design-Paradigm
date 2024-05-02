package problem;

/**
 * Represents a Cheese product in the supermarket inventory.
 * This class extends the Grocery class, adding specific attributes relevant to Cheese products.
 * It captures details such as the manufacturer, product name, price, minimum age for purchase (if applicable),
 * and the weight of the cheese.
 *
 * @author Arman Engin Sucu
 */
public class Cheese extends Grocery {

  /**
   * Constructs a new Cheese object with the specified manufacturer, product name, price,
   * minimum age requirement, and weight.
   *
   * @param manufacturer The manufacturer of the cheese.
   * @param productName  The name of the cheese product.
   * @param price        The price of the cheese.
   * @param minAge       The minimum age required to purchase the cheese. This is typically used
   *                     for age-restricted products.
   * @param weight       The weight of the cheese in ounces.
   */
  public Cheese(String manufacturer, String productName, double price, int minAge, double weight) {
    super(manufacturer, productName, price, minAge, weight);
  }

  /**
   * Constructs a new Cheese object with the specified manufacturer, product name, price,
   * and weight. This constructor is used when there is no minimum age requirement for the cheese,
   * which is typical for non-alcoholic products.
   *
   * @param manufacturer The manufacturer of the cheese.
   * @param productName  The name of the cheese product.
   * @param price        The price of the cheese.
   * @param weight       The weight of the cheese in ounces.
   */
  public Cheese(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price, weight);
  }
}

