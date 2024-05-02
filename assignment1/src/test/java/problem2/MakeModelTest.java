package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static problem2.PropulsionType.INBOARD_ENGINE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MakeModelTest {
  private MakeModel makeModel;

  @BeforeEach
  void setUp() {
    makeModel = new MakeModel("Toyota", "Camry");
  }

  @Test
  void testConstructorAndGetters() {
    assertEquals("Toyota", makeModel.getMake());
    assertEquals("Camry", makeModel.getModel());
  }

  @Test
  void testSetMake() {
    String newMake = "Honda";
    makeModel.setMake(newMake);
    assertEquals(newMake, makeModel.getMake());
  }

  @Test
  void testSetModel() {
    String newModel = "Civic";
    makeModel.setModel(newModel);
    assertEquals(newModel, makeModel.getModel());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(makeModel.equals(makeModel));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(makeModel.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(makeModel.equals(new Object()));
  }

  @Test
  void testEquals_EqualMakeModels() {
    MakeModel anotherMakeModel = new MakeModel("Toyota", "Camry");
    assertTrue(makeModel.equals(anotherMakeModel));
  }

  @Test
  void testEquals_DifferentMakeModels() {
    MakeModel differentMakeModel = new MakeModel("Ford", "Focus");
    assertFalse(makeModel.equals(differentMakeModel));
  }

  @Test
  void testHashCode_EqualObjects() {
    MakeModel anotherMakeModel = new MakeModel("Toyota", "Camry");
    assertEquals(makeModel.hashCode(), anotherMakeModel.hashCode());
  }

  @Test
  void testToString_Format() {
    String expectedString = "MakeModel{make='Toyota', model='Camry'}";
    assertEquals(expectedString, makeModel.toString());
  }
}