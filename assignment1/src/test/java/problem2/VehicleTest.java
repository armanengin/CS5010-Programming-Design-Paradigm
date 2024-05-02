package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {
  private Vehicle vehicle;
  private MakeModel makeAndModel;

  private static class TestVehicle extends Vehicle {
    TestVehicle(Integer manufacturingYear, MakeModel makeAndModel, Double msrp) {
      super(manufacturingYear, makeAndModel, msrp);
    }
  }

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Toyota", "Camry");
    vehicle = new TestVehicle(2021, makeAndModel, 30000.0);
  }

  @Test
  void testIdNotNull() {
    assertNotNull(vehicle.getId()); // Check if ID is generated
  }

  @Test
  void testGetManufacturingYear() {
    assertEquals(2021, vehicle.getManufacturingYear());
  }

  @Test
  void testGetMakeAndModel() {
    assertEquals(makeAndModel, vehicle.getMakeAndModel());
  }

  @Test
  void testGetMSRP() {
    assertEquals(30000.0, vehicle.getMSRP());
  }


  @Test
  void testSetManufacturingYear() {
    vehicle.setManufacturingYear(2022);
    assertEquals(2022, vehicle.getManufacturingYear());
  }

  @Test
  void testSetMakeAndModel() {
    MakeModel newMakeAndModel = new MakeModel("Honda", "Civic");
    vehicle.setMakeAndModel(newMakeAndModel);
    assertEquals(newMakeAndModel, vehicle.getMakeAndModel());
  }

  @Test
  void testSetMSRP() {
    Double newMSRP = 35000.0;
    vehicle.setMSRP(newMSRP);
    assertEquals(newMSRP, vehicle.getMSRP());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(vehicle.equals(vehicle));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(vehicle.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(vehicle.equals(new Object()));
  }

  @Test
  void testEquals_EqualVehicles() {
    Vehicle anotherVehicle = new TestVehicle(2021, makeAndModel, 30000.0);
    assertTrue(vehicle.equals(anotherVehicle));
  }

  @Test
  void testEquals_DifferentVehicles() {
    Vehicle differentVehicle = new TestVehicle(2020, makeAndModel, 28000.0);
    assertFalse(vehicle.equals(differentVehicle));
  }

  @Test
  void testHashCode_EqualObjects() {
    Vehicle anotherVehicle = new TestVehicle(2021, makeAndModel, 30000.0);
    assertEquals(vehicle.hashCode(), anotherVehicle.hashCode());
  }

  @Test
  void testToString_Format() {
    String expectedStringStart = "Vehicle{id='";
    assertTrue(vehicle.toString().startsWith(expectedStringStart));
  }
}