package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
  private Inventory inventory;
  private Product groceryProduct;
  private Product householdProduct;
  private StockItem groceryStockItem;
  private StockItem householdStockItem;

  @BeforeEach
  void setUp() {
    inventory = Inventory.getInventory();
    inventory.clearInventory();
    // Create sample products
    groceryProduct = new Cheese("BrandA", "Gouda Cheese", 5.00, 0, 10.0);
    householdProduct = new PaperTowel("BrandB", "Premium Towel", 6.00, 4);

    // Create stock items
    groceryStockItem = new StockItem(groceryProduct, 20);
    householdStockItem = new StockItem(householdProduct, 15);

    // add products to inventory for a fresh start in each test
    inventory.addNewProduct(groceryStockItem);
    inventory.addNewProduct(householdStockItem);
  }

  @Test
  void testSingleton() {
    Inventory anotherInstance = Inventory.getInventory();
    assertSame(inventory, anotherInstance, "Inventory should be a singleton");
  }

  @Test
  void testAddNewGroceryProduct() {
    List<StockItem> groceries = inventory.getGroceries();
    assertTrue(groceries.contains(groceryStockItem), "Grocery stock should include the added grocery product");
  }

  @Test
  void testAddNewHouseholdProduct() {
    List<StockItem> households = inventory.getHouseholds();
    assertTrue(households.contains(householdStockItem), "Household stock should include the added household product");
  }

  @Test
  void testGetTotalRetailValue() {
    double totalValue = inventory.getTotalRetailValue();
    assertEquals(190.0, totalValue, 0.01, "Total retail value should be calculated correctly");
  }

  @Test
  void testIsEnoughItemInStockSufficient() {
    assertTrue(inventory.isEnoughItemInStock(groceryProduct, 5), "Should return true for sufficient stock");
  }

  @Test
  void testIsEnoughItemInStockInsufficient() {
    assertFalse(inventory.isEnoughItemInStock(groceryProduct, 25), "Should return false for insufficient stock");
  }

  @Test
  void testMakePurchaseSuccessfully() {
    inventory.makePurchase(groceryProduct, 5);
    int remainingStock = groceryStockItem.getQuantity();
    assertEquals(15, remainingStock, "Stock should be reduced after purchase");
  }

  @Test
  void testMakePurchaseInsufficientStock() {
    // Initial stock quantity before purchase attempt
    int initialQuantity = groceryStockItem.getQuantity();

    // Attempt to purchase more items than in stock
    inventory.makePurchase(groceryProduct, 30);

    // Fetch the current stock quantity after purchase attempt
    int remainingStock = groceryStockItem.getQuantity();

    // The stock quantity should remain unchanged
    assertEquals(initialQuantity, remainingStock, "Stock should not be reduced for insufficient stock");
  }

  @Test
  void testRemoveGroceryProduct() {
    inventory.removeProduct(groceryProduct);
    assertFalse(inventory.getGroceries().stream()
            .anyMatch(item -> item.getProduct().equals(groceryProduct)),
        "Grocery product should be removed from inventory");
  }

  @Test
  void testRemoveHouseholdProduct() {
    inventory.removeProduct(householdProduct);
    assertFalse(inventory.getHouseholds().stream()
            .anyMatch(item -> item.getProduct().equals(householdProduct)),
        "Household product should be removed from inventory");
  }

  @Test
  void testRemoveNonExistentProduct() {
    Product nonExistentProduct = new Cheese("BrandC", "Blue Cheese", 8.00, 0, 5.0);
    inventory.removeProduct(nonExistentProduct);
    // Asserting that removing a non-existent product does not affect existing inventory
    assertTrue(inventory.getGroceries().contains(groceryStockItem) &&
            inventory.getHouseholds().contains(householdStockItem),
        "Inventory should remain unchanged when removing a non-existent product");
  }

  @Test
  void testToString() {
    String inventoryString = inventory.toString();
    assertNotNull(inventoryString, "toString should return a non-null string representation");
  }
}