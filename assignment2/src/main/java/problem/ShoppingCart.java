package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a shopping cart in a supermarket system.
 * The ShoppingCart manages a collection of products and their quantities as items are added or removed.
 *
 * @author Arman Engin Sucu
 */
public class ShoppingCart {
  private Map<Product, Integer> shoppingCartItems;

  /**
   * default item amount if it has never been added to the cart before
   */
  public static final int DEFAULT_ITEM_NUM = 0;

  /**
   * Constructs a new ShoppingCart instance. Initializes the internal map to track products and quantities.
   */
  public ShoppingCart() {
    this.shoppingCartItems = new HashMap<>();
  }

  /**
   * Adds a product to the shopping cart along with its quantity.
   * If the inventory does not have enough items in stock, an exception is thrown.
   *
   * @param item     The product to add to the cart.
   * @param quantity The quantity of the product to add.
   */

  public void addItemToCart(Product item, int quantity){
    Inventory inventory = Inventory.getInventory();
    try {
      if (!inventory.isEnoughItemInStock(item, quantity)) {
        throw new NotEnoughItemInStockException("Warning: Not Enough item!");
      } else {
        int currQuantity = shoppingCartItems.getOrDefault(item, DEFAULT_ITEM_NUM);
        shoppingCartItems.put(item, currQuantity + quantity);
      }
    } catch (NotEnoughItemInStockException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Removes a specified quantity of a product from the shopping cart.
   * If the quantity to remove is greater than or equal to the current quantity in the cart, the item is removed completely.
   *
   * @param item     The product to remove from the cart.
   * @param quantity The quantity of the product to remove.
   */
  public void removeItemFromCart(Product item, int quantity){
    Integer currentQuantity = shoppingCartItems.getOrDefault(item, DEFAULT_ITEM_NUM);
    if (currentQuantity == null) {
      System.out.println("Error: Item not found in the cart!");
      return;
    }
    if (quantity >= currentQuantity) {
      // If the quantity to remove is greater or equal, remove the item completely
      shoppingCartItems.remove(item);
    } else {
      // Otherwise, decrease the quantity
      shoppingCartItems.put(item, currentQuantity - quantity);
    }
  }

  /**
   * Calculates the total cost of all items in the shopping cart.
   *
   * @return The total cost of the items in the cart.
   */
  public double getTotalCostOfCart(){
    double totalCost = 0.0;
    for (Map.Entry<Product, Integer> entry : shoppingCartItems.entrySet()) {
      Product product = entry.getKey();
      Integer quantity = entry.getValue();
      totalCost += product.getPrice() * quantity;
    }
    return totalCost;
  }

  /**
   * Retrieves the shopping cart items along with their quantities.
   *
   * @return A map of products and their quantities in the shopping cart.
   */
  public Map<Product, Integer> getShoppingCartItems() {
    return shoppingCartItems;
  }

  /**
   * Clears all items from the shopping cart.
   */
  public void clearShoppingCart() {
    this.shoppingCartItems.clear();
  }

  /**
   * Retrieves the quantity of a specific product in the shopping cart.
   *
   * @param product The product to check the quantity of.
   * @return The quantity of the specified product in the cart, or null if the product is not in the cart.
   */
  public Integer getProductQuantity(Product product) {
    return shoppingCartItems.get(product);
  }

  /**
   * Checks if this ShoppingCart is equal to another object.
   * Equality is based on the contents of the shopping cart items.
   *
   * @param o The object to compare with this ShoppingCart for equality.
   * @return true if the specified object is equal to this ShoppingCart; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShoppingCart that)) {
      return false;
    }
    return Objects.equals(getShoppingCartItems(), that.getShoppingCartItems());
  }

  /**
   * Returns a hash code value for this ShoppingCart.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this ShoppingCart.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getShoppingCartItems());
  }

  /**
   * Returns a string representation of this ShoppingCart.
   * This typically includes the contents of the shopping cart items.
   *
   * @return A string representation of this ShoppingCart.
   */
  @Override
  public String toString() {
    return "ShoppingCart{" +
        "shoppingCartItems=" + shoppingCartItems +
        '}';
  }
}
