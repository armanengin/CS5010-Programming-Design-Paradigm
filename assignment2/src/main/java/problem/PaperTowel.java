package problem;

/**
 * Represents a Paper Towel product in the supermarket inventory.
 * This class extends the Household class, adding specific properties and constructors relevant to Paper Towel.
 * It captures details such as the manufacturer, product name, price, minimum age for purchase (if applicable),
 * and the number of units in a package of paper towels.
 *
 * @author Arman Engin Sucu
 */
public class PaperTowel extends Household {

  /**
   * Constructs a new Paper Towel object with the specified manufacturer, product name, price,
   * minimum age requirement, and number of units.
   *
   * @param manufacturer The manufacturer of the paper towel.
   * @param productName  The name of the paper towel product.
   * @param price        The price of the paper towel.
   * @param minAge       The minimum age required to purchase the paper towel.
   * @param numOfUnits   The number of units in the paper towel package.
   */
  public PaperTowel(String manufacturer, String productName, double price, int minAge, int numOfUnits) {
    super(manufacturer, productName, price, minAge, numOfUnits);
  }

  /**
   * Constructs a new Paper Towel object with the specified manufacturer, product name, price,
   * and number of units. This constructor is used when there is no minimum age requirement for the paper towels.
   *
   * @param manufacturer The manufacturer of the paper towel.
   * @param productName  The name of the paper towel product.
   * @param price        The price of the paper towel.
   * @param numOfUnits   The number of units in the paper towel package.
   */
  public PaperTowel(String manufacturer, String productName, double price, int numOfUnits) {
    super(manufacturer, productName, price, numOfUnits);
  }
}
