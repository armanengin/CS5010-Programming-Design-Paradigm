package problem;

import java.util.Objects;

/**
 * Represents an item in the inventory of a supermarket.
 * Each StockItem associates a Product with its quantity available in stock.
 *
 * @author Arman Engin Sucu
 */
public class StockItem {
  private Product product;
  private int quantity;

  /**
   * Constructs a StockItem with a specified product and its quantity.
   *
   * @param product  The product associated with this StockItem.
   * @param quantity The quantity of the product in stock.
   */

  public StockItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * Checks if there is enough quantity of this item in stock for a given purchase amount.
   *
   * @param amountToBuy The amount of the product to buy.
   * @return true if there is sufficient quantity in stock; false otherwise.
   */
  public boolean isEnoughItemInStock(int amountToBuy){
    return this.quantity >= amountToBuy;
  }

  /**
   * Reduces the quantity of this item in stock by the specified amount.
   * This method should be called when a purchase is made.
   *
   * @param amountToBuy The amount of the product being purchased.
   */
  public void makePurchase(int amountToBuy){
    //Reduce the quantity of the item in the event of a purchase.
    this.quantity -= amountToBuy;
  }

  /**
   * Retrieves the product associated with this stock item.
   *
   * @return The product of this stock item.
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Sets the product for this stock item.
   *
   * @param product The product to be set for this stock item.
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Retrieves the quantity of this product in stock.
   *
   * @return The quantity of the product available in stock.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of this product in stock.
   *
   * @param quantity The new quantity of the product in stock.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Checks if this StockItem is equal to another object.
   * Equality is based on the equality of the product and the quantity in stock.
   *
   * @param o The object to compare with this StockItem for equality.
   * @return true if the specified object is equal to this StockItem; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StockItem stockItem)) {
      return false;
    }
    return getQuantity() == stockItem.getQuantity() && Objects.equals(getProduct(),
        stockItem.getProduct());
  }

  /**
   * Returns a hash code value for this StockItem.
   * This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this StockItem.
   */

  @Override
  public int hashCode() {
    return Objects.hash(getProduct(), getQuantity());
  }

  @Override
  public String toString() {
    return "StockItem{" +
        "product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
