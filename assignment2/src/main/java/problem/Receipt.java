package problem;

import java.util.List;
import java.util.Objects;

/**
 * Represents a receipt for a customer's order in a supermarket system.
 * The receipt includes details such as the total price paid, lists of products received, products out of stock and not substituted,
 * and products removed due to age requirements.
 *
 * @author Arman Engin Sucu
 */
public class Receipt {
  private double totalPricePaid;
  private List<Product> productsCustomerReceived;
  private List<Product> productsOutOfStockAndNotSubstituted;
  private List<Product> productsRemovedByAgeReq;

  /**
   * Constructs a new Receipt with the given details of the customer's order.
   *
   * @param totalPricePaid The total price paid for the order.
   * @param productsCustomerReceived The list of products the customer received.
   * @param productsOutOfStockAndNotSubstituted The list of products that were out of stock and not substituted.
   * @param productsRemovedByAgeReq The list of products removed due to age restrictions.
   */
  public Receipt(double totalPricePaid, List<Product> productsCustomerReceived,
      List<Product> productsOutOfStockAndNotSubstituted, List<Product> productsRemovedByAgeReq) {
    this.totalPricePaid = totalPricePaid;
    this.productsCustomerReceived = productsCustomerReceived;
    this.productsOutOfStockAndNotSubstituted = productsOutOfStockAndNotSubstituted;
    this.productsRemovedByAgeReq = productsRemovedByAgeReq;
  }

  /**
   * Retrieves the total price paid for the products in the receipt.
   *
   * @return The total price paid.
   */
  public double getTotalPricePaid() {
    return totalPricePaid;
  }

  /**
   * Sets the total price paid for the products in the receipt.
   *
   * @param totalPricePaid The total price to be set for the receipt.
   */
  public void setTotalPricePaid(double totalPricePaid) {
    this.totalPricePaid = totalPricePaid;
  }

  /**
   * Retrieves the list of products that the customer received.
   *
   * @return A list of products received by the customer.
   */
  public List<Product> getProductsCustomerReceived() {
    return productsCustomerReceived;
  }

  /**
   * Sets the list of products that the customer received.
   *
   * @param productsCustomerReceived The list of received products to be set in the receipt.
   */
  public void setProductsCustomerReceived(List<Product> productsCustomerReceived) {
    this.productsCustomerReceived = productsCustomerReceived;
  }

  /**
   * Retrieves the list of products that were out of stock and not substituted.
   *
   * @return A list of products that were out of stock and not substituted.
   */
  public List<Product> getProductsOutOfStockAndNotSubstituted() {
    return productsOutOfStockAndNotSubstituted;
  }

  /**
   * Sets the list of products that were out of stock and not substituted.
   *
   * @param productsOutOfStockAndNotSubstituted The list of out-of-stock and not substituted products to be set in the receipt.
   */
  public void setProductsOutOfStockAndNotSubstituted(
      List<Product> productsOutOfStockAndNotSubstituted) {
    this.productsOutOfStockAndNotSubstituted = productsOutOfStockAndNotSubstituted;
  }

  /**
   * Retrieves the list of products that were removed from the order due to age requirements.
   *
   * @return A list of products removed due to age requirements.
   */
  public List<Product> getProductsRemovedByAgeReq() {
    return productsRemovedByAgeReq;
  }

  /**
   * Sets the list of products that were removed from the order due to age requirements.
   *
   * @param productsRemovedByAgeReq The list of age-restricted products to be set in the receipt.
   */
  public void setProductsRemovedByAgeReq(List<Product> productsRemovedByAgeReq) {
    this.productsRemovedByAgeReq = productsRemovedByAgeReq;
  }

  /**
   * Checks if this Receipt is equal to another object.
   * Equality is based on the total price paid and the lists of products in different categories.
   *
   * @param o The object to compare with this Receipt for equality.
   * @return true if the specified object is equal to this Receipt; false otherwise.
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Receipt receipt)) {
      return false;
    }
    return Double.compare(getTotalPricePaid(), receipt.getTotalPricePaid()) == 0
        && Objects.equals(getProductsCustomerReceived(),
        receipt.getProductsCustomerReceived()) && Objects.equals(
        getProductsOutOfStockAndNotSubstituted(), receipt.getProductsOutOfStockAndNotSubstituted())
        && Objects.equals(getProductsRemovedByAgeReq(),
        receipt.getProductsRemovedByAgeReq());
  }

  /**
   * Returns a hash code value for this Receipt.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this Receipt.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getTotalPricePaid(), getProductsCustomerReceived(),
        getProductsOutOfStockAndNotSubstituted(), getProductsRemovedByAgeReq());
  }

  /**
   * Returns a string representation of this Receipt.
   * This typically includes the total price paid and the lists of products in different categories.
   *
   * @return A string representation of this Receipt.
   */

  @Override
  public String toString() {
    return "Receipt{" +
        "totalPricePaid=" + totalPricePaid +
        ", productsCustomerReceived=" + productsCustomerReceived +
        ", productsOutOfStockAndNotSubstituted=" + productsOutOfStockAndNotSubstituted +
        ", productsRemovedByAgeReq=" + productsRemovedByAgeReq +
        '}';
  }
}
