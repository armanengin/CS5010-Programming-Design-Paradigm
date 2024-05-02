package problem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class GrocerySubstitutionStrategyTest {
  private GrocerySubstitutionStrategy strategy;
  private Inventory mockInventory;
  private Cheese outOfStockCheese;
  private Cheese substituteCheese;

  @BeforeEach
  void setUp() {
    mockInventory = Mockito.mock(Inventory.class);
    strategy = new GrocerySubstitutionStrategy(mockInventory);
    outOfStockCheese = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    substituteCheese = new Cheese("BrandA", "Cheddar", 4.5, 0, 12.0); // Cheaper and heavier
    StockItem stockItem = new StockItem(substituteCheese, 10);
    when(mockInventory.getGroceries()).thenReturn(Arrays.asList(stockItem));
  }

  @Test
  void findSubstituteSuccess() {
    Product foundSubstitute = strategy.findSubstitute(outOfStockCheese, 1);
    assertEquals(substituteCheese, foundSubstitute);
  }

  @Test
  void findSubstituteFailureDueToTypeMismatch() {
    Salmon salmon = new Salmon("BrandB", "Salmon", 10.0, 0, 15.0);
    Product foundSubstitute = strategy.findSubstitute(salmon, 1);
    assertNull(foundSubstitute);
  }

  @Test
  void getInventory() {
    assertEquals(mockInventory, strategy.getInventory());
  }

  @Test
  void setInventory() {
    Inventory newInventory = Mockito.mock(Inventory.class);
    strategy.setInventory(newInventory);
    assertEquals(newInventory, strategy.getInventory());
  }

  @Test
  void getDefaultMinQuantityForSubstitution() {
    assertEquals(1, strategy.getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION());
  }

  @Test
  void testEqualsWithSameObject() {
    assertEquals(strategy, strategy, "Strategy should be equal to itself");
  }

  @Test
  void testEqualsWithNull() {
    assertNotEquals(null, strategy, "Strategy should not be equal to null");
  }

  @Test
  void testEqualsWithDifferentClassObject() {
    Object differentClassObject = new Object();
    assertNotEquals(strategy, differentClassObject, "Strategy should not be equal to an object of a different class");
  }

  @Test
  void testEqualsWithDifferentInventory() {
    Inventory differentInventory = Mockito.mock(Inventory.class);
    GrocerySubstitutionStrategy differentStrategy = new GrocerySubstitutionStrategy(differentInventory);
    assertNotEquals(strategy, differentStrategy, "Strategies with different inventories should not be equal");
  }

  @Test
  void testHashCode() {
    GrocerySubstitutionStrategy sameStrategy = new GrocerySubstitutionStrategy(mockInventory);
    assertEquals(strategy.hashCode(), sameStrategy.hashCode(), "Hash codes should be equal for strategies with the same inventory");
  }

  @Test
  void testToString() {
    String expectedString = "GrocerySubstitutionStrategy{inventory=" + mockInventory.toString() +
        ", DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION=1}";
    assertEquals(expectedString, strategy.toString());
  }
}