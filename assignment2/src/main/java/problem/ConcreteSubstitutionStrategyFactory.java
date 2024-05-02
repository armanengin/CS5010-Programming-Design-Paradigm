package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A concrete factory for creating instances of SubstitutionStrategy based on product types.
 * This factory maintains a mapping of product types to their corresponding substitution strategies
 * by using factory design pattern.
 *
 * @author Arman Engin Sucu
 */
public class ConcreteSubstitutionStrategyFactory implements SubstitutionStrategyFactory {
  private final Map<Class<? extends Product>, SubstitutionStrategy> strategies = new HashMap<>();

  /**
   * Constructs a ConcreteSubstitutionStrategyFactory with the provided inventory.
   * Initializes the mapping of product types to their substitution strategies.
   *
   * @param inventory The inventory of the supermarket, used by the substitution strategies.
   */
  public ConcreteSubstitutionStrategyFactory(Inventory inventory) {
    strategies.put(Grocery.class, new GrocerySubstitutionStrategy(inventory));
    strategies.put(Household.class, new HouseholdSubstitutionStrategy(inventory));
    // Other strategies can be registered here
  }

  /**
   * Retrieves the substitution strategy for a given product type.
   * This method checks for a strategy suitable for the provided product type and returns it.
   *
   * @param productType The class of the product for which the substitution strategy is needed.
   * @return The SubstitutionStrategy instance appropriate for the given product type.
   * @throws IllegalArgumentException if no strategy is found for the specified product type.
   */
  @Override
  public SubstitutionStrategy getStrategy(Class<? extends Product> productType) {
    // Loop through the map keys to find the appropriate strategy for the product superclass
    for (Class<?> key : strategies.keySet()) {
      if (key.isAssignableFrom(productType)) {
        return strategies.get(key);
      }
    }
    throw new IllegalArgumentException("No strategy found for product type: " + productType);
  }

  /**
   * Retrieves the map of product types to their corresponding substitution strategies.
   *
   * @return A map representing the association of product types with their substitution strategies.
   */
  public Map<Class<? extends Product>, SubstitutionStrategy> getStrategies() {
    return strategies;
  }

  /**
   * Checks if this ConcreteSubstitutionStrategyFactory is equal to another object.
   * Equality is based on the mapping of product types to their substitution strategies.
   *
   * @param o The object to compare with this ConcreteSubstitutionStrategyFactory for equality.
   * @return true if the specified object is equal to this factory; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConcreteSubstitutionStrategyFactory that)) {
      return false;
    }
    return Objects.equals(getStrategies(), that.getStrategies());
  }

  /**
   * Returns a hash code value for this ConcreteSubstitutionStrategyFactory.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this factory.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getStrategies());
  }

  /**
   * Returns a string representation of this ConcreteSubstitutionStrategyFactory.
   * This typically includes the mapping of product types to their substitution strategies.
   *
   * @return A string representation of this factory.
   */
  @Override
  public String toString() {
    return "ConcreteSubstitutionStrategyFactory{" +
        "strategies=" + strategies +
        '}';
  }
}