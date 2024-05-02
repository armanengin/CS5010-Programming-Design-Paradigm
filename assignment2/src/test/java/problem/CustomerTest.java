package problem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  private ShoppingCart shoppingCart;
  private Name name;
  private StockItem stockItem;
  private Beer beer;


  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe", "Smith");
    shoppingCart = new ShoppingCart();
    customer = new Customer(name, 30, shoppingCart);
    beer = new Beer("Acme Brewery", "Acme Beer", 3.99, 0, 12.0); // Example instantiation
    stockItem = new StockItem(beer, 20);
    Inventory inventory;
    inventory = Inventory.getInventory();
    inventory.clearInventory();
    inventory.addNewProduct(stockItem); // Ensuring inventory has sufficient quantity of beer

  }

  @Test
  void addProductToShoppingCartWithQuantity() {
    int quantityToAdd = 5;
    customer.addProductToShoppingCart(stockItem, quantityToAdd);
    assertEquals(quantityToAdd, shoppingCart.getProductQuantity(beer));
  }

  @Test
  void addProductToShoppingCartDefaultQuantity() {
    customer.addProductToShoppingCart(stockItem);
    assertEquals(1, shoppingCart.getProductQuantity(beer)); // Checking if quantity increased by 1
  }

  @Test
  void removeProductFromShoppingCart() {
    int quantityToAdd = 3;
    int quantityToRemove = 2;
    customer.addProductToShoppingCart(stockItem, quantityToAdd);
    customer.removeProductToShoppingCart(stockItem, quantityToRemove);
    assertEquals(quantityToAdd - quantityToRemove, shoppingCart.getProductQuantity(beer));
  }

  @Test
  void testGetName() {
    assertEquals(name, customer.getName());
  }

  @Test
  void testSetName() {
    Name newName = new Name("Jane", "Alice", "Doe");
    customer.setName(newName);
    assertEquals(newName, customer.getName());
  }

  @Test
  void testGetAge() {
    assertEquals(30, customer.getAge());
  }

  @Test
  void testSetAge() {
    int newAge = 35;
    customer.setAge(newAge);
    assertEquals(newAge, customer.getAge());
  }

  @Test
  void testGetShoppingCart() {
    assertEquals(shoppingCart, customer.getShoppingCart());
  }

  @Test
  void testSetShoppingCart() {
    ShoppingCart newCart = new ShoppingCart();
    customer.setShoppingCart(newCart);
    assertEquals(newCart, customer.getShoppingCart());
  }

  @Test
  void testEqualsDifferentName() {
    Customer differentNameCustomer = new Customer(new Name("Jane", "Alice", "Doe"), 30, shoppingCart);
    assertNotEquals(customer, differentNameCustomer, "Customers with different names should not be equal");
  }

  @Test
  void testEqualsDifferentAge() {
    Customer differentAgeCustomer = new Customer(name, 35, shoppingCart);
    assertNotEquals(customer, differentAgeCustomer, "Customers with different ages should not be equal");
  }

  @Test
  void testEqualsDifferentShoppingCart() {
    Inventory inventory = Inventory.getInventory();
    ShoppingCart differentCart = new ShoppingCart();
    differentCart.addItemToCart(beer, 14);
    Customer differentCartCustomer = new Customer(name, 30, differentCart);
    assertNotEquals(customer, differentCartCustomer, "Customers with different shopping carts should not be equal");
  }

  @Test
  void testEqualsDifferentClass() {
    assertNotEquals(customer, new Object(), "Customer should not be equal to an object of a different class");
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(customer, null, "Customer should not be equal to null");
  }

  @Test
  void testHashCode() {
    Customer sameCustomer = new Customer(name, 30, shoppingCart);
    assertEquals(customer.hashCode(), sameCustomer.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Customer{name=Name{firstName='John', middleName='Doe', lastName='Smith'}, age=30, shoppingCart=ShoppingCart{shoppingCartItems={}}}";
    assertEquals(expectedString, customer.toString());
  }
}