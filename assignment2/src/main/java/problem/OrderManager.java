package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Manages and processes customer orders in a supermarket system.
 * The OrderManager is responsible for checking stock availability, handling product substitutions,
 * managing age-restricted items, and generating receipts for completed orders.
 *
 * @author Arman Engin Sucu
 */
public class OrderManager {
  private Customer customer;
  private ShoppingCart shoppingCart;
  private List<Product> productsCustomerReceived;
  private List<Product> productsOutOfStockAndNotSubstituted;
  private List<Product> productsRemovedByAgeReq;
  private Inventory inventory;
  private SubstitutionStrategyFactory substitutionStrategyFactory;

  /**
   * Constructs a new OrderManager for a given customer.
   *
   * @param customer The customer for whom the order is being managed.
   */
  public OrderManager(Customer customer) {
    this.customer = customer;
    this.shoppingCart = customer.getShoppingCart();
    this.inventory = Inventory.getInventory();
    this.substitutionStrategyFactory = new ConcreteSubstitutionStrategyFactory(this.inventory);
    productsCustomerReceived = new ArrayList<>();
    productsOutOfStockAndNotSubstituted = new ArrayList<>();
    productsRemovedByAgeReq = new ArrayList<>();
  }

  /**
   * Processes the customer's order, performing necessary checks and updates.
   * This includes handling age restrictions, substitutions for out-of-stock items, and inventory updates.
   *
   * @return The receipt for the processed order.
   */
  public Receipt processOrder(){
    addAgeRestrictedItemsToReceipt();
    checkSubstitutes();
    updateInventory();
    return generateReceipt();
  }

  /**
   * Updates the inventory based on the products included in the processed order.
   */
  public void updateInventory(){
    productsCustomerReceived.forEach(product -> {
      int quantity = shoppingCart.getProductQuantity(product);
      inventory.makePurchase(product, quantity);
    });
  }

  /**
   * Adds items to the receipt that are removed from the shopping cart due to age restrictions.
   */
  public void addAgeRestrictedItemsToReceipt(){
    Map<Product, Integer> items = new HashMap<>(shoppingCart.getShoppingCartItems());
    items.forEach((product, quantity) -> {
      if (!AgeCheckForProduct(product)) {
        productsRemovedByAgeReq.add(product);
      }
    });
    productsRemovedByAgeReq.forEach(product -> shoppingCart.removeItemFromCart(product, items.get(product)));
  }

  /**
   * Checks if the customer meets the age requirement for a specific product.
   *
   * @param product The product to check for age restriction.
   * @return true if the customer's age meets or exceeds the product's minimum age requirement, false otherwise.
   */
  public boolean AgeCheckForProduct(Product product){
    return customer.getAge() >= product.getMinAge();
  }

  /**
   * Checks and handles substitutions for items that are out of stock in the customer's shopping cart.
   */
  public void checkSubstitutes(){
    Map<Product, Integer> items = new HashMap<>(shoppingCart.getShoppingCartItems());
    items.forEach((product, quantity) -> {
      if (!inventory.isEnoughItemInStock(product, quantity)) {
        SubstitutionStrategy strategy = substitutionStrategyFactory.getStrategy(product.getClass());
        Product substitute = strategy.findSubstitute(product, quantity);
        if (substitute != null) {
          shoppingCart.addItemToCart(substitute, quantity);
          productsCustomerReceived.add(substitute);
        } else {
          productsOutOfStockAndNotSubstituted.add(product);
        }
      } else {
        productsCustomerReceived.add(product);
      }
    });
    // Update for removing items
    for (Product product : productsOutOfStockAndNotSubstituted) {
      Integer currentQuantity = shoppingCart.getProductQuantity(product);
      if (currentQuantity != null) {
        shoppingCart.removeItemFromCart(product, currentQuantity);
      }
    }
  }

  /**
   * Generates a receipt for the customer's order.
   * Clears the shopping cart
   * @return The generated receipt detailing the order.
   */
  private Receipt generateReceipt() {
    double totalCost = calculateTotalCost();
    shoppingCart.clearShoppingCart();
    return new Receipt(totalCost, productsCustomerReceived, productsOutOfStockAndNotSubstituted, productsRemovedByAgeReq);
  }

  /**
   * Calculates the total cost of the products in the customer's order.
   *
   * @return The total cost of the order.
   */
  private double calculateTotalCost() {
    return productsCustomerReceived.stream()
        .mapToDouble(product -> product.getPrice() * shoppingCart.getProductQuantity(product))
        .sum();
  }

  /**
   * Checks if this OrderManager is equal to another object.
   * The equality is based on the equality of the customer, shopping cart, and lists of products
   * that were received, out of stock and not substituted, and removed due to age requirements.
   *
   * @param o The object to compare with this OrderManager for equality.
   * @return true if the specified object is equal to this OrderManager; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OrderManager that)) {
      return false;
    }
    return Objects.equals(customer, that.customer) && Objects.equals(shoppingCart,
        that.shoppingCart) && Objects.equals(productsCustomerReceived,
        that.productsCustomerReceived) && Objects.equals(
        productsOutOfStockAndNotSubstituted, that.productsOutOfStockAndNotSubstituted)
        && Objects.equals(productsRemovedByAgeReq, that.productsRemovedByAgeReq);
  }

  /**
   * Returns a hash code value for this OrderManager.
   * This method is supported for the benefit of hash tables,
   * such as those provided in java.util.HashMap.
   *
   * @return A hash code value for this OrderManager.
   */
  @Override
  public int hashCode() {
    return Objects.hash(customer, shoppingCart, productsCustomerReceived,
        productsOutOfStockAndNotSubstituted, productsRemovedByAgeReq);
  }

  /**
   * Returns a string representation of this OrderManager.
   * This typically includes the customer, shopping cart, and the lists of
   * products that were received, out of stock and not substituted, and removed due to age requirements.
   *
   * @return A string representation of this OrderManager.
   */
  @Override
  public String toString() {
    return "OrderManager{" +
        "customer=" + customer +
        ", shoppingCart=" + shoppingCart +
        ", productsCustomerReceived=" + productsCustomerReceived +
        ", productsOutOfStockAndNotSubstituted=" + productsOutOfStockAndNotSubstituted +
        ", productsRemovedByAgeReq=" + productsRemovedByAgeReq +
        '}';
  }
}
