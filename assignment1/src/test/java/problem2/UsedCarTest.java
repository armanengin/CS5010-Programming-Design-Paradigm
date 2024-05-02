package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsedCarTest {
  private UsedCar usedCar;
  private MakeModel makeAndModel;

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Honda", "Accord"); // Assuming MakeModel is a class you have defined
    usedCar = new UsedCar(2018, makeAndModel, 20000.0, 50000, 2, 1);
  }

  @Test
  void testGetManufacturingYear() {
    assertEquals(2018, usedCar.getManufacturingYear());
  }

  @Test
  void testGetMakeAndModel() {
    assertEquals(makeAndModel, usedCar.getMakeAndModel());
  }

  @Test
  void testGetMSRP() {
    assertEquals(20000.0, usedCar.getMSRP());
  }

  @Test
  void testGetMileage() {
    assertEquals(50000, usedCar.getMileage());
  }

  @Test
  void testGetNumOfPrevOwner() {
    assertEquals(2, usedCar.getNumOfPrevOwner());
  }

  @Test
  void testGetNumOfMinorTrafficAcc() {
    assertEquals(1, usedCar.getNumOfMinorTrafficAcc());
  }

  @Test
  void testSetMileage() {
    Integer newMileage = 55000;
    usedCar.setMileage(newMileage);
    assertEquals(newMileage, usedCar.getMileage());
  }

  @Test
  void testSetNumOfPrevOwner() {
    Integer newNumOfPrevOwner = 3;
    usedCar.setNumOfPrevOwner(newNumOfPrevOwner);
    assertEquals(newNumOfPrevOwner, usedCar.getNumOfPrevOwner());
  }

  @Test
  void testSetNumOfMinorTrafficAcc() {
    Integer newNumOfMinorTrafficAcc = 2;
    usedCar.setNumOfMinorTrafficAcc(newNumOfMinorTrafficAcc);
    assertEquals(newNumOfMinorTrafficAcc, usedCar.getNumOfMinorTrafficAcc());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(usedCar.equals(usedCar));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(usedCar.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(usedCar.equals(new Object()));
  }

  @Test
  void testEquals_EqualUsedCars() {
    UsedCar anotherUsedCar = new UsedCar(2018, makeAndModel, 20000.0, 50000, 2, 1);
    assertTrue(usedCar.equals(anotherUsedCar));
  }

  @Test
  void testEquals_DifferentUsedCars() {
    UsedCar differentUsedCar = new UsedCar(2019, makeAndModel, 22000.0, 40000, 1, 0);
    assertFalse(usedCar.equals(differentUsedCar));
  }

  @Test
  void testHashCode_EqualObjects() {
    UsedCar anotherUsedCar = new UsedCar(2018, makeAndModel, 20000.0, 50000, 2, 1);
    assertEquals(usedCar.hashCode(), anotherUsedCar.hashCode());
  }

  @Test
  void testToString_Format() {
    String expectedString = "UsedCar{mileage=50000, numOfPrevOwner=2, numOfMinorTrafficAcc=1}";
    assertEquals(expectedString, usedCar.toString());
  }
}