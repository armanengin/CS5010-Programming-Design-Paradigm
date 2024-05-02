package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static problem2.PropulsionType.INBOARD_ENGINE;
import static problem2.PropulsionType.OUTBOARD_ENGINE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.MilesBalance;

class BoatTest {
  private Boat boat;
  private MakeModel makeAndModel;
  private PropulsionType propulsionType;

  @BeforeEach
  void setUp() {
    makeAndModel = new MakeModel("Make", "Model"); // Assuming MakeModel is a class you have defined
    propulsionType = INBOARD_ENGINE; // Assuming PropulsionType is an enum you have defined
    boat = new Boat(2021, makeAndModel, 50000.0, 30.5f, 10, propulsionType);
  }

  @Test
  void testGetManufacturingYear() {
    assertEquals(2021, boat.getManufacturingYear());
  }

  @Test
  void testGetMakeAndModel() {
    assertEquals(makeAndModel, boat.getMakeAndModel());
  }

  @Test
  void testGetMsrp() {
    assertEquals(50000.0, boat.getMSRP());
  }

  @Test
  void testGetLength() {
    assertEquals(30.5f, boat.getLength());
  }

  @Test
  void testGetNumOfPassengers() {
    assertEquals(10, boat.getNumOfPassengers());
  }

  @Test
  void testGetPropulsionType() {
    assertEquals(propulsionType, boat.getPropulsionType());
  }

  @Test
  void testSetLength() {
    Float newLength = 35.0f;
    boat.setLength(newLength);
    assertEquals(newLength, boat.getLength());
  }

  @Test
  void testSetNumOfPassengers() {
    Integer newNumOfPassengers = 12;
    boat.setNumOfPassengers(newNumOfPassengers);
    assertEquals(newNumOfPassengers, boat.getNumOfPassengers());
  }

  @Test
  void testSetPropulsionType() {
    PropulsionType newPropulsionType = PropulsionType.JET_PROPULSION;
    boat.setPropulsionType(newPropulsionType);
    assertEquals(newPropulsionType, boat.getPropulsionType());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(boat.equals(boat));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(boat.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(boat.equals(new Object()));
  }

  @Test
  void testEquals_EqualBoats() {
    Boat anotherBoat = new Boat(2021, makeAndModel, 50000.0, 30.5f, 10, propulsionType);
    assertTrue(boat.equals(anotherBoat));
  }

  @Test
  void testEquals_DifferentBoats() {
    Boat differentBoat = new Boat(2020, makeAndModel, 55000.0, 32.0f, 12, OUTBOARD_ENGINE);
    assertFalse(boat.equals(differentBoat));
  }

  @Test
  void testHashCode_EqualObjects() {
    Boat anotherBoat = new Boat(2021, makeAndModel, 50000.0, 30.5f, 10, propulsionType);
    assertEquals(boat.hashCode(), anotherBoat.hashCode());
  }

  @Test
  void testToString_Format() {
    String expectedString = "Boat{" +
        "length=30.5" +
        ", numOfPassengers=10" +
        ", propulsionType=INBOARD_ENGINE" +
        '}';
    assertEquals(expectedString, boat.toString());
  }
}