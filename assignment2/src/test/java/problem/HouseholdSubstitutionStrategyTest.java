package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HouseholdSubstitutionStrategyTest {
  private Inventory inventory;
  private HouseholdSubstitutionStrategy strategy;
  private PaperTowel originalItem;
  private PaperTowel substituteItem;

  @BeforeEach
  void setUp() {
    inventory = Inventory.getInventory();
    inventory.clearInventory();
    strategy = new HouseholdSubstitutionStrategy(inventory);

    originalItem = new PaperTowel("BrandA", "Basic Towel", 2.99, 2);
    substituteItem = new PaperTowel("BrandB", "Premium Towel", 2.99, 3);

    // Add the substitute item to the inventory
    StockItem stockItem = new StockItem(substituteItem, 10);
    inventory.addNewProduct(stockItem);
  }

  @Test
  void testFindSubstituteSuccess() {
    Product foundSubstitute = strategy.findSubstitute(originalItem, 1);
    assertEquals(substituteItem, foundSubstitute, "Substitute item should be found successfully");
  }

  @Test
  void testFindSubstituteFail() {
    // Trying to find a substitute for an item not in inventory
    Product notFoundSubstitute = strategy.findSubstitute(new PaperTowel("BrandC", "Eco Towel", 3.99, 4), 1);
    assertNull(notFoundSubstitute);
  }

  @Test
  void testFindSubstituteWithInsufficientQuantity() {
    // Trying to find a substitute but the quantity in inventory is not enough
    Product notFoundSubstitute = strategy.findSubstitute(originalItem, 11); // Inventory only has 10
    assertNull(notFoundSubstitute);
  }

  @Test
  void testFindSubstituteMoreExpensive() {
    // Trying to find a substitute that is more expensive than the original
    Product notFoundSubstitute = strategy.findSubstitute(new PaperTowel("BrandA", "Basic Towel", 1.99, 2), 1);
    assertNull(notFoundSubstitute);
  }

  @Test
  void testFindSubstituteLessUnits() {
    // Trying to find a substitute that has fewer units than the original
    Product notFoundSubstitute = strategy.findSubstitute(new PaperTowel("BrandA", "Basic Towel", 2.99, 4), 1);
    assertNull(notFoundSubstitute);
  }

  @Test
  void testGetInventory() {
    assertSame(inventory, strategy.getInventory(), "getInventory should return the correct inventory");
  }

  @Test
  void testSetInventory() {
    Inventory newInventory = Mockito.mock(Inventory.class);
    strategy.setInventory(newInventory);
    assertSame(newInventory, strategy.getInventory(), "setInventory should correctly set the new inventory");
  }

  @Test
  void testGetDefaultMinQuantityForSubstitution() {
    assertEquals(1, strategy.getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION(), "getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION should return the correct value");
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(inventory, strategy.getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION());
    assertEquals(expectedHashCode, strategy.hashCode(), "hashCode should return the correct hash code");
  }

  @Test
  void testEqualsSameObject() {
    assertEquals(strategy, strategy, "Strategy should be equal to itself");
  }

  @Test
  void testEqualsDifferentObjectSameState() {
    HouseholdSubstitutionStrategy anotherStrategy = new HouseholdSubstitutionStrategy(inventory);
    assertEquals(strategy, anotherStrategy, "Strategies with the same inventory should be equal");
  }

  @Test
  void testEqualsDifferentObjectDifferentState() {
    Inventory differentInventory = Mockito.mock(Inventory.class);
    HouseholdSubstitutionStrategy anotherStrategy = new HouseholdSubstitutionStrategy(differentInventory);
    assertNotEquals(strategy, anotherStrategy, "Strategies with different inventories should not be equal");
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(strategy, null, "Strategy should not be equal to null");
  }

  @Test
  void testEqualsDifferentClass() {
    Object differentClassObject = new Object();
    assertNotEquals(strategy, differentClassObject, "Strategy should not be equal to an object of a different class");
  }

  @Test
  void testEqualsWithDifferentDefaultMinQuantity() {
    HouseholdSubstitutionStrategy anotherStrategy = new HouseholdSubstitutionStrategy(inventory) {
      @Override
      public int getDEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION() {
        return 2; // different default min quantity
      }
    };
    assertNotEquals(strategy, anotherStrategy, "Strategies with different default min quantities should not be equal");
  }

  @Test
  void testToString() {
    String expectedString = "HouseholdSubstitutionStrategy{" +
        "inventory=" + inventory +
        ", DEFAULT_MIN_QUANTITY_FOR_SUBSTITUTION=1}";
    assertEquals(expectedString, strategy.toString(), "toString should return the correct string representation");
  }
}