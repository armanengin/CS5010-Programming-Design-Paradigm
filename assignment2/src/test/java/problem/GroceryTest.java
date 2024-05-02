package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroceryTest {
  private Cheese cheese;

  @BeforeEach
  void setUp() {
    cheese = new Cheese("TestBrand", "TestCheese", 10.0, 0, 2.0);
  }

  @Test
  void getWeight() {
    assertEquals(2.0, cheese.getWeight());
  }

  @Test
  void setWeight() {
    cheese.setWeight(3.0);
    assertEquals(3.0, cheese.getWeight());
  }

  @Test
  void testEquals() {
    Cheese sameCheese = new Cheese("TestBrand", "TestCheese", 10.0, 0, 2.0);
    assertEquals(cheese, sameCheese);
  }

  @Test
  void testNotEqualsDifferentWeight() {
    Cheese differentCheese = new Cheese("TestBrand", "TestCheese", 10.0, 0, 3.0);
    assertNotEquals(cheese, differentCheese);
  }

  @Test
  void testHashCode() {
    Cheese sameCheese = new Cheese("TestBrand", "TestCheese", 10.0, 0, 2.0);
    assertEquals(cheese.hashCode(), sameCheese.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Grocery{manufacturer='TestBrand'," +
        " productName='TestCheese', price=10.0, minAge=0, weight=2.0}";
    assertEquals(expectedString, cheese.toString());
  }
}