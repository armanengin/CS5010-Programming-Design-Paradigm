package problem;

/**
 * Represents a Beer product in the supermarket inventory.
 * This class extends the Grocery class, adding specific properties relevant to Beer.
 * It captures details such as the manufacturer, product name, price, minimum age for purchase,
 * and the weight of the beer.
 *
 * @author Arman Engin Sucu
 */
public class Beer extends Grocery {

  /**
   * Constructs a new Beer object with the specified manufacturer, product name, price,
   * minimum age requirement, and weight.
   *
   * @param manufacturer The manufacturer of the beer.
   * @param productName  The name of the beer product.
   * @param price        The price of the beer.
   * @param minAge       The minimum age required to purchase the beer.
   * @param weight       The weight of the beer in ounces.
   */
  public Beer(String manufacturer, String productName, double price, int minAge, double weight) {
    super(manufacturer, productName, price, minAge, weight);
  }

  /**
   * Constructs a new Beer object with the specified manufacturer, product name, price,
   * and weight. This constructor assumes there is no minimum age requirement for purchasing
   * the beer (common in regions where alcohol-free or very low alcohol beers are sold).
   *
   * @param manufacturer The manufacturer of the beer.
   * @param productName  The name of the beer product.
   * @param price        The price of the beer.
   * @param weight       The weight of the beer in ounces.
   */
  public Beer(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price, weight);
  }
}
