package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static problem2.PropulsionType.INBOARD_ENGINE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewCarTest {
  private NewCar newCar;
  private MakeModel makeAndModel;

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Toyota", "Camry"); // Assuming MakeModel is a class you have defined
    newCar = new NewCar(2021, makeAndModel, 30000.0, 5);
  }

  @Test
  void testConstructorAndGetters() {
    assertEquals(2021, newCar.getManufacturingYear());
    assertEquals(makeAndModel, newCar.getMakeAndModel());
    assertEquals(30000.0, newCar.getMSRP());
    assertEquals(5, newCar.getNumOfAvailableCarsWithinFiftyMiles());
  }

  @Test
  void testSetNumOfAvailableCarsWithinFiftyMiles() {
    Integer newNumOfAvailableCars = 10;
    newCar.setNumOfAvailableCarsWithinFiftyMiles(newNumOfAvailableCars);
    assertEquals(newNumOfAvailableCars, newCar.getNumOfAvailableCarsWithinFiftyMiles());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(newCar.equals(newCar));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(newCar.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(newCar.equals(new Object()));
  }

  @Test
  void testEquals_EqualNewCars() {
    NewCar anotherNewCar = new NewCar(2021, makeAndModel, 30000.0, 5);
    assertTrue(newCar.equals(anotherNewCar));
  }

  @Test
  void testEquals_DifferentNewCars() {
    NewCar differentNewCar = new NewCar(2020, makeAndModel, 28000.0, 3);
    assertFalse(newCar.equals(differentNewCar));
  }

  @Test
  void testHashCode_EqualObjects() {
    NewCar anotherNewCar = new NewCar(2021, makeAndModel, 30000.0, 5);
    assertEquals(newCar.hashCode(), anotherNewCar.hashCode());
  }

  @Test
  void testToString_Format() {
    String expectedString = "NewCar{numOfAvailableCarsWithinFiftyMiles=5}";
    assertEquals(expectedString, newCar.toString());
  }
}