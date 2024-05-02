package problem;

import java.util.Objects;

/**
 * Represents a customer in the supermarket system.
 * Each customer has a name, age, and a shopping cart for storing selected products.
 *
 * @author Arman Engin Sucu
 */
public class Customer {
  private final int DEFAULT_ADD_QUANTITY = 1;
  private Name name;
  private int age;
  private ShoppingCart shoppingCart;

  /**
   * Constructs a new Customer with the specified name, age, and shopping cart.
   *
   * @param name          The name of the customer.
   * @param age           The age of the customer.
   * @param shoppingCart  The shopping cart associated with the customer.
   */
  public Customer(Name name, int age, ShoppingCart shoppingCart) {
    this.name = name;
    this.age = age;
    this.shoppingCart = shoppingCart;
  }

  /**
   * Adds a specified quantity of a product to the customer's shopping cart.
   *
   * @param stockItem The stock item to add to the cart.
   * @param quantity  The quantity of the stock item to add.
   */
  public void addProductToShoppingCart(StockItem stockItem, int quantity) {
    Product product = stockItem.getProduct();
    this.getShoppingCart().addItemToCart(product, quantity);
  }

  /**
   * Adds a product to the customer's shopping cart using a default quantity.
   *
   * @param stockItem The stock item to add to the cart.
   */
  public void addProductToShoppingCart(StockItem stockItem) {
    Product product = stockItem.getProduct();
    this.getShoppingCart().addItemToCart(product, DEFAULT_ADD_QUANTITY);
  }

  /**
   * Removes a specified quantity of a product from the customer's shopping cart.
   *
   * @param stockItem The stock item to remove from the cart.
   * @param quantity  The quantity of the stock item to remove.
   */
  public void removeProductToShoppingCart(StockItem stockItem, int quantity) {
    Product product = stockItem.getProduct();
    this.getShoppingCart().removeItemFromCart(product, quantity);
  }

  /**
   * Gets the customer's name.
   *
   * @return The name of the customer.
   */
  public Name getName() {
    return name;
  }

  /**
   * Sets the customer's name.
   *
   * @param name The new name to set for the customer.
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * Gets the customer's age.
   *
   * @return The age of the customer.
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the customer's age.
   *
   * @param age The new age to set for the customer.
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the customer's shopping cart.
   *
   * @return The shopping cart of the customer.
   */
  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Sets the customer's shopping cart.
   *
   * @param shoppingCart The new shopping cart to set for the customer.
   */
  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  /**
   * Indicates whether some other object is "equal to" this Customer.
   * The Customer objects are considered equal if they have the same name, age, and shopping cart.
   *
   * @param o The reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer = (Customer) o;
    return age == customer.age &&
        Objects.equals(name, customer.name) &&
        Objects.equals(shoppingCart, customer.shoppingCart);
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   * The hash code is generated based on the customer's name, age, and shopping cart.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, age, shoppingCart);
  }

  /**
   * Returns a string representation of the Customer object.
   * This typically includes the customer's name, age, and the contents of their shopping cart.
   *
   * @return A string representation of the customer.
   */
  @Override
  public String toString() {
    return "Customer{" +
        "name=" + name +
        ", age=" + age +
        ", shoppingCart=" + shoppingCart +
        '}';
  }
}
