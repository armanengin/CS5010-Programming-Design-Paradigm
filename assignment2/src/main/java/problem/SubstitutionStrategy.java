package problem;

import problem.Product;

/**
 * Interface defining the strategy for finding a substitute product in a supermarket system.
 * Implementations of this interface provide specific strategies for different types of products.
 *
 * @author Arman Engin Sucu
 */
public interface SubstitutionStrategy {

  /**
   * Finds a suitable substitute product for a given out-of-stock product.
   * The method determines an appropriate substitute based on the type of the out-of-stock product and the required quantity.
   *
   * @param outOfStockProduct The product that is out of stock and needs a substitute.
   * @param quantityNeeded The quantity needed for the out-of-stock product.
   * @return A substitute product that matches the criteria, or null if no suitable substitute is found.
   */
  Product findSubstitute(Product outOfStockProduct, int quantityNeeded);
}
