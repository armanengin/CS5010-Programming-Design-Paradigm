package problem;

/**
 * Interface defining the factory for creating SubstitutionStrategy instances.
 * This factory is responsible for providing the appropriate substitution strategy based on the type of product.
 *
 * @author Arman Engin Sucu
 */
public interface SubstitutionStrategyFactory {

  /**
   * Retrieves the appropriate substitution strategy for a given product type.
   * The method is responsible for returning a strategy instance that is suitable for the specific class of product.
   *
   * @param productType The class of the product for which the substitution strategy is needed.
   * @return The SubstitutionStrategy instance suitable for the given product type.
   */
  SubstitutionStrategy getStrategy(Class<? extends Product> productType);
}
