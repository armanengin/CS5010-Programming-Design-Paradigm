package problem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcreteSubstitutionStrategyFactoryTest {

  private ConcreteSubstitutionStrategyFactory factory;
  private Inventory mockInventory;

  @BeforeEach
  void setUp() {
    mockInventory = mock(Inventory.class); // Mock the Inventory
    factory = new ConcreteSubstitutionStrategyFactory(mockInventory);
  }

  @Test
  void getStrategyWithValidGroceryClass() {
    assertEquals(GrocerySubstitutionStrategy.class, factory.getStrategy(Grocery.class).getClass(),
        "Expected strategy class for Grocery to be GrocerySubstitutionStrategy");
  }

  @Test
  void getStrategyWithValidHouseholdClass() {
    assertEquals(HouseholdSubstitutionStrategy.class, factory.getStrategy(Household.class).getClass(),
        "Expected strategy class for Household to be HouseholdSubstitutionStrategy");
  }

  @Test
  void getStrategyWithInvalidProductClass() {
    assertThrows(IllegalArgumentException.class,
        () -> factory.getStrategy(UnregisteredProduct.class),
        "Expect IllegalArgumentException for an unregistered product class");
  }

  @Test
  void getStrategiesNotNull() {
    Map<Class<? extends Product>, SubstitutionStrategy> retrievedStrategies = factory.getStrategies();
    assertNotNull(retrievedStrategies, "Strategies map should not be null");
  }

  @Test
  void getStrategiesContainsGroceryKey() {
    Map<Class<? extends Product>, SubstitutionStrategy> retrievedStrategies = factory.getStrategies();
    assertTrue(retrievedStrategies.containsKey(Grocery.class), "Strategies should contain a key for Grocery class");
  }

  @Test
  void getStrategiesContainsHouseholdKey() {
    Map<Class<? extends Product>, SubstitutionStrategy> retrievedStrategies = factory.getStrategies();
    assertTrue(retrievedStrategies.containsKey(Household.class), "Strategies should contain a key for Household class");
  }


  @Test
  void testEquals() {
    ConcreteSubstitutionStrategyFactory anotherFactory = new ConcreteSubstitutionStrategyFactory(mockInventory);
    assertEquals(factory, anotherFactory, "Factories with the same setup should be considered equal");
  }

  @Test
  void testHashCode() {
    ConcreteSubstitutionStrategyFactory anotherFactory = new ConcreteSubstitutionStrategyFactory(mockInventory);
    assertEquals(factory.hashCode(), anotherFactory.hashCode(), "Hash codes should match for equal factories");
  }

  @Test
  void toStringMethod() {
    assertNotNull(factory.toString(), "toString should return a non-null string representation");
  }

  private static class UnregisteredProduct extends Product {
    public UnregisteredProduct(String manufacturer, String productName, double price) {
      super(manufacturer, productName, price);
    }
  }
}