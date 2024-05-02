package problem;

import java.util.Objects;

/**
 * Represents a generic Household product in the supermarket system.
 * This abstract class extends the Product class, adding a specific attribute for the number of units in the household item.
 * It is designed to be subclassed by specific types of household items.
 *
 * @author Arman Engin Sucu
 */
public abstract class Household extends Product {
  private int numOfUnits;

  /**
   * Constructs a new Household object with specified manufacturer, product name, price, minimum age requirement, and number of units.
   *
   * @param manufacturer The manufacturer of the household item.
   * @param productName  The name of the household item.
   * @param price        The price of the household item.
   * @param minAge       The minimum age required to purchase the household item.
   * @param numOfUnits   The number of individual units in the household item package.
   */
  public Household(String manufacturer, String productName, double price, int minAge, int numOfUnits) {
    super(manufacturer, productName, price, minAge);
    this.numOfUnits = numOfUnits;
  }

  /**
   * Constructs a new Household object with specified manufacturer, product name, price, and number of units.
   * This constructor is used for household items without an age restriction.
   *
   * @param manufacturer The manufacturer of the household item.
   * @param productName  The name of the household item.
   * @param price        The price of the household item.
   * @param numOfUnits   The number of individual units in the household item package.
   */
  public Household(String manufacturer, String productName, double price, int numOfUnits) {
    super(manufacturer, productName, price);
    this.numOfUnits = numOfUnits;
  }

  /**
   * Gets the number of units in the household item package.
   *
   * @return The number of units.
   */
  public int getNumOfUnits() {
    return numOfUnits;
  }

  /**
   * Sets the number of units in the household item package.
   *
   * @param numOfUnits The number of units to set.
   */
  public void setNumOfUnits(int numOfUnits) {
    this.numOfUnits = numOfUnits;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * The comparison is based on the equality of the superclass (Product) attributes and the number of units.
   *
   * @param o The reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Household household)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return getNumOfUnits() == household.getNumOfUnits();
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   * The hash code is generated based on the superclass (Product) hash code and the number of units of the household item.
   *
   * @return A hash code value for this object.
   */

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getNumOfUnits());
  }

  /**
   * Returns a string representation of the Household object.
   * This typically includes the number of units and superclass (Product) attributes.
   *
   * @return A string representation of the household item.
   */
  @Override
  public String toString() {
    return "Household{" +
        "manufacturer='" + getManufacturer() + '\'' +
        ", productName='" + getProductName() + '\'' +
        ", price=" + getPrice() +
        ", minAge=" + getMinAge() +
        ", numOfUnits=" + getNumOfUnits() +
        '}';
    }
}
