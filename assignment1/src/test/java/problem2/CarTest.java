package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
  private NewCar newCar;
  private MakeModel makeAndModel;

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Brand", "Model");
    int numOfAvailableCars = 5; // Example property for NewCar
    newCar = new NewCar(2021, makeAndModel, 50000.0, numOfAvailableCars);
  }

  @Test
  void testManufacturingYear() {
    assertEquals(2021, newCar.getManufacturingYear());
  }

  @Test
  void testMakeAndModel() {
    assertEquals(makeAndModel, newCar.getMakeAndModel());
  }

  @Test
  void testMSRP() {
    assertEquals(50000.0, newCar.getMSRP());
  }

  @Test
  void testNumOfAvailableCarsWithinFiftyMiles() {
    assertEquals(5, newCar.getNumOfAvailableCarsWithinFiftyMiles());
  }

}