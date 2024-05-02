package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * Class that uses Singleton Pattern (for that reason equals and hashcode aren't implemented)
 * Represents the inventory of a supermarket, holding both grocery and household items.
 * It uses separate collections to manage grocery and household items, allowing for efficient
 * management and retrieval of items based on their type.
 *
 * @author Arman Engin Sucu
 */
public class Inventory {
  private static Inventory inventory;

  private Map<String, List<StockItem>> groceryStock;
  private Map<String, List<StockItem>> householdStock;

  /**
   * Private constructor for Inventory to support the Singleton pattern.
   */
  private Inventory() {
    groceryStock = new HashMap<>();
    householdStock = new HashMap<>();
  }

  /**
   * Provides access to the single instance of the Inventory class, creating it if it does not exist.
   *
   * @return The singleton instance of the Inventory.
   */
  public static synchronized Inventory getInventory() {
    if (inventory == null) {
      inventory = new Inventory();
    }
    return inventory;
  }

  /**
   * Adds a new product to the appropriate category in the inventory.
   *
   * @param product The StockItem to be added to the inventory.
   */
  public void addNewProduct(StockItem product){
    String productType = product.getProduct().getClass().getSimpleName();

    if (product.getProduct() instanceof Grocery) {
      groceryStock.computeIfAbsent(productType, k -> new ArrayList<>()).add(product);
    } else if (product.getProduct() instanceof Household) {
      householdStock.computeIfAbsent(productType, k -> new ArrayList<>()).add(product);
    }
  }

  /**
   * Removes a product and all its stock items from the inventory.
   * It determines whether the product is a Grocery or Household item and removes it from the respective stock.
   *
   * @param product The product to be removed from the inventory.
   */
  public void removeProduct(Product product) {
    String productType = product.getClass().getSimpleName();

    if (product instanceof Grocery) {
      removeProductFromStock(groceryStock, productType, product);
    } else if (product instanceof Household) {
      removeProductFromStock(householdStock, productType, product);
    }
  }

  /**
   * Removes all stock items of a specific product from a given stock category.
   * If after removal the stock items list is empty, the product type entry is removed from the stock map.
   *
   * @param stock The stock map (either groceryStock or householdStock) from which the product is to be removed.
   * @param productType The type of the product as a String, used as a key in the stock map.
   * @param product The product whose stock items are to be removed.
   */
  private void removeProductFromStock(Map<String, List<StockItem>> stock, String productType, Product product) {
    List<StockItem> stockItems = stock.get(productType);
    if (stockItems != null) {
      stockItems.removeIf(stockItem -> stockItem.getProduct().equals(product));
      if (stockItems.isEmpty()) {
        stock.remove(productType);
      }
    }
  }

  /**
   * Calculates the total retail value of all items in the inventory.
   *
   * @return The total retail value.
   */
  public double getTotalRetailValue() {
    double sum = 0;
    sum += getTotalRetailValueForType(groceryStock);
    sum += getTotalRetailValueForType(householdStock);
    return sum;
  }

  /**
   * Private helper method to calculate the total retail value for a specific category of items.
   *
   * @param stock The map representing a category of items (either grocery or household).
   * @return The total retail value for the given category.
   */
  private double getTotalRetailValueForType(Map<String, List<StockItem>> stock) {
    return stock.values().stream()
        .flatMap(List::stream)
        .mapToDouble(stockItem -> stockItem.getProduct().getPrice() * stockItem.getQuantity())
        .sum();
  }

  /**
   * Checks whether there is enough stock of a specified product to fulfill a desired quantity.
   *
   * @param product The product to check.
   * @param numItem The desired quantity.
   * @return true if there is enough stock, false otherwise.
   */
  public boolean isEnoughItemInStock(Product product, int numItem) {
    String productType = product.getClass().getSimpleName();
    List<StockItem> stockItems = product instanceof Grocery ?
        groceryStock.get(productType) :
        householdStock.get(productType);

    return stockItems != null && stockItems.stream()
        .anyMatch(stockItem -> stockItem.getProduct().equals(product) &&
            stockItem.getQuantity() >= numItem);
  }

  /**
   * Processes a purchase of a specified product, reducing its stock by the specified quantity.
   *
   * @param purchasedItem The product being purchased.
   * @param numItem       The quantity of the product to purchase.
   */
  public void makePurchase(Product purchasedItem, int numItem) {
    String productType = purchasedItem.getClass().getSimpleName();
    Map<String, List<StockItem>> stockMap = purchasedItem instanceof Grocery ?
        groceryStock :
        householdStock;
    List<StockItem> stockItems = stockMap.get(productType);
    try {
      if (stockItems == null) {
        throw new NoSuchElementException("Product not found in inventory.");
      }
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      return;
    }
    try {
      for (StockItem stockItem : stockItems) {
        if (stockItem.getProduct().equals(purchasedItem)) {
          if (stockItem.getQuantity() < numItem) {
            throw new NotEnoughItemInStockException("Not enough stock available for purchase.");
          }
          int newQuantity = stockItem.getQuantity() - numItem;
          stockItem.setQuantity(newQuantity);
          return;
        }
      }
    } catch (NotEnoughItemInStockException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Retrieves a list of all grocery stock items in the inventory.
   *
   * @return A list of StockItems in the grocery category.
   */
  // Generic getters for groceries and households
  public List<StockItem> getGroceries() {
    return new ArrayList<>(groceryStock.values().stream().flatMap(List::stream).toList());
  }

  /**
   * Clears all items from both grocery and household stocks in the inventory.
   * This method resets the inventory, removing all products and their quantities.
   */
  public void clearInventory() {
    groceryStock.clear();
    householdStock.clear();
  }

  /**
   * Retrieves a list of all household stock items in the inventory.
   *
   * @return A list of StockItems in the household category.
   */
  public List<StockItem> getHouseholds() {
    return new ArrayList<>(householdStock.values().stream().flatMap(List::stream).toList());
  }

  /**
   * Returns a string representation of the Inventory object, including details of both grocery and household stocks.
   *
   * @return A string representation of the Inventory.
   */
  @Override
  public String toString() {
    return "Inventory{" +
        "groceryStock=" + groceryStock +
        ", householdStock=" + householdStock +
        '}';
  }

  //P.S: no equals and hashcode implemented because of the nature of singleton classes
}


