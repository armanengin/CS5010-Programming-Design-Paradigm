package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VesselTest {
  private Boat boat;
  private MakeModel makeAndModel;

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Yacht", "Model X");
    float length = 30.5f; // Example specific property for Boat
    int numOfPassengers = 10; // Example specific property for Boat
    PropulsionType propulsionType = PropulsionType.SAIL_POWER; // Example specific property for Boat
    boat = new Boat(2020, makeAndModel, 100000.0, length, numOfPassengers, propulsionType);
  }

  @Test
  void testManufacturingYear() {
    assertEquals(2020, boat.getManufacturingYear());
  }

  @Test
  void testMakeAndModel() {
    assertEquals(makeAndModel, boat.getMakeAndModel());
  }

  @Test
  void testMSRP() {
    assertEquals(100000.0, boat.getMSRP());
  }

  @Test
  void testLength() {
    assertEquals(30.5f, boat.getLength());
  }

  @Test
  void testNumOfPassengers() {
    assertEquals(10, boat.getNumOfPassengers());
  }

  @Test
  void testPropulsionType() {
    assertEquals(PropulsionType.SAIL_POWER, boat.getPropulsionType());
  }
}