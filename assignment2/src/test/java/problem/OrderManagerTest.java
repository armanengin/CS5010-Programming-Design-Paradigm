package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderManagerTest {
  private OrderManager orderManager;
  private Customer customer;
  private ShoppingCart shoppingCart;
  private Inventory inventory;
  private Cheese cheeseInStock;
  private PaperTowel paperTowelOutOfStock;
  private Cheese ageRestrictedCheese;
  private Cheese substituteCheese;
  private PaperTowel substitutePaperTowel;
  @BeforeEach
  void setUp() {
    // Assuming Name, Customer, ShoppingCart, Inventory, and Product classes are defined appropriately
    customer = new Customer(new Name("John", "Doe", "Smith"), 20, new ShoppingCart());
    inventory = Inventory.getInventory();
    inventory.clearInventory();
    orderManager = new OrderManager(customer);

    cheeseInStock = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    paperTowelOutOfStock = new PaperTowel("BrandB", "Basic Towel", 2.0, 5);
    ageRestrictedCheese = new Cheese("BrandA", "Gouda", 6.0, 21, 8.0);
    substituteCheese = new Cheese("BrandC", "Mozzarella", 4.5, 0, 9.0);
    substitutePaperTowel = new PaperTowel("BrandB", "Basic TowelS", 1.99, 9);

    // Add products to the inventory
    inventory.addNewProduct(new StockItem(cheeseInStock, 20));
    inventory.addNewProduct(new StockItem(ageRestrictedCheese, 10));
    inventory.addNewProduct(new StockItem(substituteCheese, 15));

    // Add products to the customer's shopping cart
    customer.getShoppingCart().addItemToCart(cheeseInStock, 2);
    inventory.addNewProduct(new StockItem(paperTowelOutOfStock, 10));
    customer.getShoppingCart().addItemToCart(paperTowelOutOfStock, 3);
    inventory.removeProduct(paperTowelOutOfStock);
    customer.getShoppingCart().addItemToCart(ageRestrictedCheese, 1);
  }

  @Test
  void testProcessOrderWithSufficientStock() {
    Receipt receipt = orderManager.processOrder();
    assertEquals(1, receipt.getProductsCustomerReceived().size(), "Should process order with sufficient stock");
  }

  @Test
  void testProcessOrderWithInsufficientStock() {
    customer.getShoppingCart().addItemToCart(paperTowelOutOfStock, 20); // Adding more than in stock
    System.out.println(inventory.isEnoughItemInStock(paperTowelOutOfStock, 3));
    Receipt receipt = orderManager.processOrder();
    assertTrue(receipt.getProductsOutOfStockAndNotSubstituted().contains(paperTowelOutOfStock), "Should list out-of-stock items");
  }
  @Test
  void testProcessOrderWithAgeRestriction() {
    Receipt receipt = orderManager.processOrder();
    assertTrue(receipt.getProductsRemovedByAgeReq().contains(ageRestrictedCheese), "Should remove age-restricted items for underage customer");
  }

  /*
  @Test
  void testProcessOrderSubstitution() {
    customer.getShoppingCart().addItemToCart(paperTowelOutOfStock, 3); // Assuming it's out of stock
    Receipt receipt = orderManager.processOrder();
    assertTrue(receipt.getProductsCustomerReceived().contains(substitutePaperTowel), "Should substitute out-of-stock item");
  }
*/
  @Test
  void testInventoryUpdateAfterOrder() {
    orderManager.processOrder();
    assertFalse(inventory.isEnoughItemInStock(cheeseInStock, 20), "Inventory should be updated after order");
  }

  @Test
  void testAgeCheckForProduct() {
    boolean isAgeSufficient = orderManager.AgeCheckForProduct(ageRestrictedCheese);
    assertFalse(isAgeSufficient, "Should return false for age-restricted products for underage customers");
  }

  @Test
  void addAgeRestrictedItemsToReceipt_RemovesAgeRestrictedItems() {
    orderManager.addAgeRestrictedItemsToReceipt();
    assertFalse(customer.getShoppingCart().getShoppingCartItems().containsKey(ageRestrictedCheese),
        "Age-restricted item should be removed from the shopping cart");
  }

  @Test
  void testEquals_DifferentCustomer() {
    Customer differentCustomer = new Customer(new Name("Alice", "B", "Doe"), 25, new ShoppingCart());
    OrderManager differentCustomerOrderManager = new OrderManager(differentCustomer);
    assertNotEquals(orderManager, differentCustomerOrderManager, "OrderManagers with different customers should not be equal");
  }

  @Test
  void testEquals_DifferentShoppingCart() {
    Customer anotherCustomerWithDifferentCart = new Customer(new Name("John", "Doe", "Smith"), 20, new ShoppingCart());
    anotherCustomerWithDifferentCart.getShoppingCart().addItemToCart(substituteCheese, 2);
    OrderManager differentCartOrderManager = new OrderManager(anotherCustomerWithDifferentCart);
    assertNotEquals(orderManager, differentCartOrderManager, "OrderManagers with different shopping carts should not be equal");
  }

  @Test
  void testEquals_SameState() {
    Customer sameCustomer = new Customer(new Name("John", "Doe", "Smith"), 20, new ShoppingCart());
    sameCustomer.getShoppingCart().addItemToCart(cheeseInStock, 2);
    inventory.addNewProduct(new StockItem(paperTowelOutOfStock, 10));
    sameCustomer.getShoppingCart().addItemToCart(paperTowelOutOfStock, 3);
    inventory.removeProduct(paperTowelOutOfStock);
    sameCustomer.getShoppingCart().addItemToCart(ageRestrictedCheese, 1);

    OrderManager sameStateOrderManager = new OrderManager(sameCustomer);
    assertEquals(orderManager, sameStateOrderManager, "OrderManagers with the same state should be equal");
  }

  @Test
  void testEquals_NullComparison() {
    assertNotEquals(orderManager, null, "OrderManager should not be equal to null");
  }

  @Test
  void testEquals_DifferentClass() {
    Object notAnOrderManager = new Object();
    assertNotEquals(orderManager, notAnOrderManager, "OrderManager should not be equal to an object of a different class");
  }

  @Test
  void testHashCode_ConsistencyCheck() {
    OrderManager anotherOrderManager = new OrderManager(customer);
    assertEquals(orderManager.hashCode(), anotherOrderManager.hashCode(),
        "Hash codes should be equal for two OrderManagers with the same state");
  }

  @Test
  void testToStringNotNull() {
    assertNotNull(orderManager.toString(), "toString should return a non-null string representation");
  }
}