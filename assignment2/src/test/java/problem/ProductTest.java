package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

  private Cheese cheeseWithAgeRestriction;
  private Cheese cheeseWithoutAgeRestriction;
  private TestProduct testProduct;
  private static class TestProduct extends Product {
    //create new class that doesn't override toString so we can test Product's toString
    TestProduct(String manufacturer, String productName, double price, int minAge) {
      super(manufacturer, productName, price, minAge);
    }

    TestProduct(String manufacturer, String productName, double price) {
      super(manufacturer, productName, price);
    }
  }
  @BeforeEach
  void setUp() {
    cheeseWithAgeRestriction = new Cheese("TestManufacturer", "TestCheese", 10.0, 18, 5.0);
    cheeseWithoutAgeRestriction = new Cheese("TestManufacturer", "TestCheese", 10.0, 5.0);
    testProduct = new TestProduct("TestManufacturer", "TestProduct", 10.0, 18);
  }

  @Test
  void testProductWithAgeRestriction() {
    assertEquals(18, cheeseWithAgeRestriction.getMinAge(), "Age restriction should be 18");
  }

  @Test
  void testProductWithoutAgeRestriction() {
    assertEquals(0, cheeseWithoutAgeRestriction.getMinAge(), "Default age restriction should be 0");
  }

  @Test
  void testGetManufacturer() {
    assertEquals("TestManufacturer", cheeseWithAgeRestriction.getManufacturer(), "Manufacturer should match");
  }

  @Test
  void testGetProductName() {
    assertEquals("TestCheese", cheeseWithAgeRestriction.getProductName(), "Product name should match");
  }

  @Test
  void testGetPrice() {
    assertEquals(10.0, cheeseWithAgeRestriction.getPrice(), 0.01, "Price should match");
  }

  @Test
  void testEqualsSameAttributes() {
    Cheese anotherCheese = new Cheese("TestManufacturer", "TestCheese", 10.0, 18, 5.0);
    assertEquals(cheeseWithAgeRestriction, anotherCheese, "Cheese products with same attributes should be equal");
  }

  @Test
  void testEqualsDifferentManufacturer() {
    Cheese differentManufacturerCheese = new Cheese("DifferentManufacturer", "TestCheese", 10.0, 18, 5.0);
    assertNotEquals(cheeseWithAgeRestriction, differentManufacturerCheese, "Different manufacturer should make products not equal");
  }

  @Test
  void testEqualsDifferentProductName() {
    Cheese differentNameCheese = new Cheese("TestManufacturer", "DifferentCheese", 10.0, 18, 5.0);
    assertNotEquals(cheeseWithAgeRestriction, differentNameCheese, "Different product name should make products not equal");
  }

  @Test
  void testEqualsDifferentPrice() {
    Cheese differentPriceCheese = new Cheese("TestManufacturer", "TestCheese", 11.0, 18, 5.0);
    assertNotEquals(cheeseWithAgeRestriction, differentPriceCheese, "Different price should make products not equal");
  }

  @Test
  void testEqualsDifferentMinAge() {
    Cheese differentMinAgeCheese = new Cheese("TestManufacturer", "TestCheese", 10.0, 21, 5.0);
    assertNotEquals(cheeseWithAgeRestriction, differentMinAgeCheese, "Different minimum age should make products not equal");
  }

  @Test
  void testEqualsWithNull() {
    assertNotEquals(cheeseWithAgeRestriction, null, "Product should not be equal to null");
  }

  @Test
  void testEqualsWithDifferentClassObject() {
    Object differentClassObject = new Object();
    assertNotEquals(cheeseWithAgeRestriction, differentClassObject, "Product should not be equal to an object of a different class");
  }

  @Test
  void testHashCode() {
    Cheese anotherCheese = new Cheese("TestManufacturer", "TestCheese", 10.0, 18, 5.0);
    assertEquals(cheeseWithAgeRestriction.hashCode(), anotherCheese.hashCode(), "Hash codes should be equal");
  }

  @Test
  void testToString() {
    String expectedString = "Product{manufacturer='TestManufacturer', productName='TestProduct', price=10.0, minAge=18}";
    assertEquals(expectedString, testProduct.toString(), "String representation should match");
  }
}