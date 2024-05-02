package problem;

import java.util.List;
import java.util.Objects;

/**
 * Represents a substitution strategy for household items in a supermarket system.
 * This strategy is used to find suitable substitutes for out-of-stock household items based on specific criteria.
 *
 * @author Arman Engin Sucu
 */
public class HouseholdSubstitutionStrategy implements SubstitutionStrategy {
  private Inventory inventory;
  private final int DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION = 1;

  /**
   * Constructs a new HouseholdSubstitutionStrategy with the provided inventory.
   *
   * @param inventory The inventory of the supermarket, used to check stock and find substitutes.
   */
  public HouseholdSubstitutionStrategy(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Finds a suitable substitute for an out-of-stock household item.
   * The substitute is selected based on being of the same specific type, having equal or greater number of units,
   * being in stock with at least the default minimum quantity, and costing the same or less.
   *
   * @param outOfStockItem The household item that is out of stock.
   * @param quantityNeeded The quantity of the item needed.
   * @return A suitable substitute product if found; null otherwise.
   */
  @Override
  public Product findSubstitute(Product outOfStockItem, int quantityNeeded) {
    // We will look for a substitute among the groceries of the same specific type.
    List<StockItem> stockItems = inventory.getHouseholds();
    for (StockItem stockItem : stockItems) {
      Product potentialSubstitute = stockItem.getProduct();
      // Check if potential substitute is of the same specific type as the out-of-stock item.
      if (potentialSubstitute.getClass().equals(outOfStockItem.getClass()) &&
          stockItem.getQuantity() >= quantityNeeded && stockItem.getQuantity() >= DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION &&
          potentialSubstitute.getPrice() <= outOfStockItem.getPrice() &&
          ((Household) potentialSubstitute).getNumOfUnits() >= ((Household) outOfStockItem).getNumOfUnits()) {
        return potentialSubstitute;
      }
    }
    return null; // No suitable substitute found
  }

  /**
   * Retrieves the inventory associated with this substitution strategy.
   * The inventory is used to check stock levels and find potential substitutes.
   *
   * @return The inventory used for substitution.
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Sets the inventory to be used by this substitution strategy.
   * The inventory is crucial for checking stock levels and identifying possible substitutes.
   *
   * @param inventory The new inventory to be set for substitution purposes.
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Retrieves the default minimum quantity required for a product to be considered as a potential substitute.
   * This value is used to ensure that a sufficient quantity of the substitute product is available.
   *
   * @return The default minimum quantity for considering a product as a substitute.
   */
  public int getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION() {
    return DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION;
  }

  /**
   * Checks if this HouseholdSubstitutionStrategy is equal to another object.
   * Equality is based on the inventory and the default minimum quantity for substitution.
   *
   * @param o The object to compare with this HouseholdSubstitutionStrategy for equality.
   * @return true if the specified object is equal to this strategy; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HouseholdSubstitutionStrategy that)) {
      return false;
    }
    return
        getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION()
            == that.getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION()
            && Objects.equals(getInventory(), that.getInventory());
  }

  /**
   * Returns a hash code value for this HouseholdSubstitutionStrategy.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this strategy.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getInventory(), getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION());
  }

  /**
   * Returns a string representation of this HouseholdSubstitutionStrategy.
   * This typically includes the inventory and the default minimum quantity for substitution.
   *
   * @return A string representation of this strategy.
   */
  @Override
  public String toString() {
    return "HouseholdSubstitutionStrategy{" +
        "inventory=" + inventory +
        ", DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION=" + DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION +
        '}';
  }
}
