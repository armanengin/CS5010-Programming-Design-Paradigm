package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {
  private ShoppingCart shoppingCart;
  private Product product;

  @BeforeEach
  void setUp() {
    shoppingCart = new ShoppingCart();
    // Assuming Cheese class extends Product
    product = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    Inventory inventory = Inventory.getInventory();
    inventory.clearInventory();
    inventory.addNewProduct(new StockItem(product, 20));
  }

  @Test
  void addItemToCart() {
    shoppingCart.addItemToCart(product, 2);
    assertEquals(2, shoppingCart.getProductQuantity(product));
  }

  @Test
  void removeItemFromCart() {
    shoppingCart.addItemToCart(product, 3);
    shoppingCart.removeItemFromCart(product, 1);
    assertEquals(2, shoppingCart.getProductQuantity(product));
  }

  @Test
  void removeItemCompletelyFromCart() {
    shoppingCart.addItemToCart(product, 1);
    shoppingCart.removeItemFromCart(product, 1);
    assertNull(shoppingCart.getProductQuantity(product));
  }

  @Test
  void getTotalCostOfCart() {
    shoppingCart.addItemToCart(product, 2);
    assertEquals(10.0, shoppingCart.getTotalCostOfCart());
  }

  @Test
  void clearShoppingCart() {
    shoppingCart.addItemToCart(product, 2);
    shoppingCart.clearShoppingCart();
    assertEquals(0, shoppingCart.getShoppingCartItems().size());
  }

  @Test
  void testEquals() {
    ShoppingCart anotherCart = new ShoppingCart();
    anotherCart.addItemToCart(product, 2);
    shoppingCart.addItemToCart(product, 2);
    assertEquals(shoppingCart, anotherCart);
  }

  @Test
  void testHashCode() {
    ShoppingCart anotherCart = new ShoppingCart();
    anotherCart.addItemToCart(product, 2);
    shoppingCart.addItemToCart(product, 2);
    assertEquals(shoppingCart.hashCode(), anotherCart.hashCode());
  }

  @Test
  void testToString() {
    shoppingCart.addItemToCart(product, 2);
    String expectedString = "ShoppingCart{shoppingCartItems={" + product.toString() + "=2}}";
    assertEquals(expectedString, shoppingCart.toString());
  }
}