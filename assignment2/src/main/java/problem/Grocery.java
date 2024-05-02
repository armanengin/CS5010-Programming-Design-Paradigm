package problem;

import java.util.Objects;

/**
 * Represents a generic Grocery product in the supermarket system.
 * This abstract class extends the Product class, adding a specific attribute for the weight of the grocery item.
 * It is designed to be subclassed by specific types of grocery items.
 *
 * @author Arman Engin Sucu
 */
public abstract class Grocery extends Product {
  private double weight;

  /**
   * Constructs a new Grocery object with specified manufacturer, product name, price, minimum age requirement, and weight.
   *
   * @param manufacturer The manufacturer of the grocery item.
   * @param productName  The name of the grocery item.
   * @param price        The price of the grocery item.
   * @param minAge       The minimum age required to purchase the grocery item.
   * @param weight       The weight of the grocery item in units (e.g., ounces, grams).
   */
  public Grocery(String manufacturer, String productName, double price, int minAge, double weight) {
    super(manufacturer, productName, price, minAge);
    this.weight = weight;
  }

  /**
   * Constructs a new Grocery object with specified manufacturer, product name, price, and weight.
   * This constructor is used for grocery items without an age restriction.
   *
   * @param manufacturer The manufacturer of the grocery item.
   * @param productName  The name of the grocery item.
   * @param price        The price of the grocery item.
   * @param weight       The weight of the grocery item in units (e.g., ounces, grams).
   */
  public Grocery(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price);
    this.weight = weight;
  }

  /**
   * Gets the weight of the grocery item.
   *
   * @return The weight of the grocery item.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the grocery item.
   *
   * @param weight The weight to set for the grocery item.
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * The comparison is based on the equality of the superclass (Product) attributes and the weight.
   *
   * @param o The reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Grocery grocery)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Double.compare(getWeight(), grocery.getWeight()) == 0;
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   * The hash code is generated based on the superclass (Product) hash code and the weight of the grocery item.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getWeight());
  }

  /**
   * Returns a string representation of the Grocery object.
   * This typically includes the weight and superclass (Product) attributes.
   *
   * @return A string representation of the grocery item.
   */
  @Override
  public String toString() {
    return "Grocery{" +
        "manufacturer='" + getManufacturer() + '\'' +
        ", productName='" + getProductName() + '\'' +
        ", price=" + getPrice() +
        ", minAge=" + getMinAge() +
        ", weight=" + getWeight() +
        '}';
  }
}
