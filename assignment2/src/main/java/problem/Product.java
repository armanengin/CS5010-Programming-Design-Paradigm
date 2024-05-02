package problem;

import java.util.Objects;

/**
 * Represents a generic product in the supermarket inventory system.
 * This abstract class is intended to be extended by specific product types, such as groceries or household items.
 * It contains common attributes for a product, including manufacturer, product name, price, and minimum age restriction.
 *
 * @author Arman Engin Sucu
 */
public abstract class Product {
  private static final int DEFAULT_PRODUCT_AGE_RESTRICTION = 0;
  private final String manufacturer;
  private final String productName;
  private final double price;
  private final int minAge;

  /**
   * Constructs a new Product with specified manufacturer, product name, price, and minimum age restriction.
   *
   * @param manufacturer The manufacturer of the product.
   * @param productName  The name of the product.
   * @param price        The price of the product.
   * @param minAge       The minimum age required to purchase the product.
   */
  public Product(String manufacturer, String productName, double price, int minAge) {
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.minAge = minAge;
  }

  /**
   * Constructs a new Product with specified manufacturer, product name, and price.
   * Assumes no minimum age restriction for the product.
   *
   * @param manufacturer The manufacturer of the product.
   * @param productName  The name of the product.
   * @param price        The price of the product.
   */
  public Product(String manufacturer, String productName, double price) {
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.minAge = DEFAULT_PRODUCT_AGE_RESTRICTION;
  }

  /**
   * Retrieves the manufacturer of the product.
   *
   * @return The manufacturer's name.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Retrieves the name of the product.
   *
   * @return The product's name.
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Retrieves the price of the product.
   *
   * @return The price of the product.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Retrieves the minimum age required to purchase the product.
   * If there is no age restriction, this method returns the default age restriction value.
   *
   * @return The minimum age required to purchase the product, or the default value if no restriction applies.
   */
  public int getMinAge() {
    return minAge;
  }

  /**
   * Checks if this Product is equal to another object.
   * Equality is based on the manufacturer, product name, price, and minimum age restriction.
   *
   * @param o The object to compare with this Product for equality.
   * @return true if the specified object is equal to this Product; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product product)) {
      return false;
    }
    return Double.compare(getPrice(), product.getPrice()) == 0
        && getMinAge() == product.getMinAge() && Objects.equals(getManufacturer(),
        product.getManufacturer()) && Objects.equals(getProductName(),
        product.getProductName());
  }

  /**
   * Returns a hash code value for this Product.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this Product.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getManufacturer(), getProductName(), getPrice(), getMinAge());
  }

  /**
   * Returns a string representation of this Product.
   * This typically includes the manufacturer, product name, price, and minimum age restriction.
   *
   * @return A string representation of this Product.
   */
  @Override
  public String toString() {
    return "Product{" +
        "manufacturer='" + manufacturer + '\'' +
        ", productName='" + productName + '\'' +
        ", price=" + price +
        ", minAge=" + minAge +
        '}';
  }
}
