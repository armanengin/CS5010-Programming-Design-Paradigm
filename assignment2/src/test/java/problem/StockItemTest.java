package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockItemTest {
  private StockItem stockItem;
  private Product product;

  @BeforeEach
  void setUp() {
    // Assuming Cheese class extends Product
    product = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    stockItem = new StockItem(product, 10);
  }

  @Test
  void testIsEnoughItemInStock() {
    assertTrue(stockItem.isEnoughItemInStock(5));
  }

  @Test
  void testIsNotEnoughItemInStock() {
    assertFalse(stockItem.isEnoughItemInStock(15));
  }

  @Test
  void testMakePurchase() {
    stockItem.makePurchase(5);
    assertEquals(5, stockItem.getQuantity());
  }

  @Test
  void testGetProduct() {
    assertEquals(product, stockItem.getProduct());
  }

  @Test
  void testSetProduct() {
    Product newProduct = new Cheese("BrandB", "Gouda", 6.0, 0, 12.0);
    stockItem.setProduct(newProduct);
    assertEquals(newProduct, stockItem.getProduct());
  }

  @Test
  void testGetQuantity() {
    assertEquals(10, stockItem.getQuantity());
  }

  @Test
  void testSetQuantity() {
    stockItem.setQuantity(15);
    assertEquals(15, stockItem.getQuantity());
  }

  @Test
  void testEquals() {
    StockItem anotherStockItem = new StockItem(product, 10);
    assertEquals(stockItem, anotherStockItem);
  }

  @Test
  void testNotEquals() {
    StockItem differentStockItem = new StockItem(product, 5);
    assertNotEquals(stockItem, differentStockItem);
  }

  @Test
  void testHashCode() {
    StockItem anotherStockItem = new StockItem(product, 10);
    assertEquals(stockItem.hashCode(), anotherStockItem.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "StockItem{product=Grocery{manufacturer='BrandA', productName='Cheddar', price=5.0, minAge=0, weight=10.0}, quantity=10}";
    assertEquals(expectedString, stockItem.toString());
  }
}