package problem;

import java.util.List;
import java.util.Objects;

/**
 * Represents a substitution strategy for grocery items in a supermarket system.
 * This strategy is used to find suitable substitutes for out-of-stock grocery items based on specific criteria.
 *
 * @author Arman Engin Sucu
 */
public class GrocerySubstitutionStrategy implements SubstitutionStrategy {
  private Inventory inventory;
  private final int DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION = 1;

  /**
   * Constructs a new GrocerySubstitutionStrategy with the provided inventory.
   *
   * @param inventory The inventory of the supermarket, used to check stock and find substitutes.
   */
  public GrocerySubstitutionStrategy(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Finds a suitable substitute for an out-of-stock grocery item.
   * The substitute is selected based on being of the same specific type, having equal or greater weight,
   * being in stock with at least the default minimum quantity, and costing the same or less.
   *
   * @param outOfStockItem The grocery item that is out of stock.
   * @param quantityNeeded The quantity of the item needed.
   * @return A suitable substitute product if found; null otherwise.
   */
  @Override
  public Product findSubstitute(Product outOfStockItem, int quantityNeeded) {
    // We will look for a substitute among the groceries of the same specific type.
    List<StockItem> stockItems = inventory.getGroceries();
    for (StockItem stockItem : stockItems) {
      Product potentialSubstitute = stockItem.getProduct();
      // Check if potential substitute is of the same specific type as the out-of-stock item.
      if (potentialSubstitute.getClass().equals(outOfStockItem.getClass()) &&
          stockItem.getQuantity() >= quantityNeeded && stockItem.getQuantity() >= DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION &&
          potentialSubstitute.getPrice() <= outOfStockItem.getPrice() &&
          ((Grocery) potentialSubstitute).getWeight() >= ((Grocery) outOfStockItem).getWeight()) {
        return potentialSubstitute;
      }
    }
    return null; // No suitable substitute found
  }

  /**
   * Retrieves the inventory associated with this substitution strategy.
   *
   * @return The inventory used for finding substitution items.
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Retrieves the default minimum quantity for considering an item as a potential substitute.
   *
   * @return The default minimum quantity for substitution.
   */

  public int getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION() {
    return DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION;
  }

  /**
   * Sets the inventory to be used by this substitution strategy.
   *
   * @param inventory The new inventory to be used for finding substitution items.
   */

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Checks if this GrocerySubstitutionStrategy is equal to another object.
   * Equality is based on the inventory and the default minimum quantity for substitution.
   *
   * @param o The object to compare with this GrocerySubstitutionStrategy for equality.
   * @return true if the specified object is equal to this strategy; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GrocerySubstitutionStrategy that)) {
      return false;
    }
    return
        getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION()
            == that.getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION()
            && Objects.equals(getInventory(), that.getInventory());
  }

  /**
   * Returns a hash code value for this GrocerySubstitutionStrategy.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this strategy.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getInventory(), getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION());
  }

  /**
   * Returns a string representation of this GrocerySubstitutionStrategy.
   * This typically includes the inventory and the default minimum quantity for substitution.
   *
   * @return A string representation of this strategy.
   */
  @Override
  public String toString() {
    return "GrocerySubstitutionStrategy{" +
        "inventory=" + inventory +
        ", DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION=" + DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION +
        '}';
  }
}

