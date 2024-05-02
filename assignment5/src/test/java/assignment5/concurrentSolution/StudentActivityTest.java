package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentActivityTest {

  private StudentActivity studentActivity;

  @BeforeEach
  void setUp() {
    studentActivity = new StudentActivity("CS101", "2021F", 123, 456, 7, 89);
  }

  @Test
  void getCodeModule_ReturnsCorrectCodeModule() {
    assertEquals("CS101", studentActivity.getCodeModule(),
        "getCodeModule should return correct code module.");
  }

  @Test
  void setCodeModule_SetsCodeModuleCorrectly() {
    studentActivity.setCodeModule("CS102");
    assertEquals("CS102", studentActivity.getCodeModule(),
        "setCodeModule should set code module correctly.");
  }

  // Similar tests for other getters and setters...

  @Test
  void testEqualsWithSameObject() {
    StudentActivity activity = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    assertEquals(activity, activity, "A student activity should be equal to itself.");
  }

  @Test
  void testEqualsWithIdenticalObject() {
    StudentActivity activity1 = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    StudentActivity activity2 = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    assertEquals(activity1, activity2,
        "Two student activities with identical values should be considered equal.");
  }

  @Test
  void testEqualsWithDifferentObject() {
    StudentActivity activity1 = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    StudentActivity activity2 = new StudentActivity("CS102", "2021F", 2, 101, 8, 90);
    assertNotEquals(activity1, activity2,
        "Two student activities with different values should not be considered equal.");
  }

  @Test
  void testEqualsWithDifferentType() {
    StudentActivity activity = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    String notAnActivity = "Not a StudentActivity";
    assertNotEquals(activity, notAnActivity,
        "A student activity should not be equal to an object of a different type.");
  }

  @Test
  void testEqualsWithNull() {
    StudentActivity activity = new StudentActivity("CS101", "2021F", 1, 100, 7, 89);
    assertNotEquals(null, activity, "A student activity should not be considered equal to null.");
  }

  @Test
  void hashCode_IdenticalProperties_ReturnsSameHashCode() {
    StudentActivity other = new StudentActivity("CS101", "2021F", 123, 456, 7, 89);
    assertEquals(studentActivity.hashCode(), other.hashCode(),
        "hashCode should return the same value for two objects with identical properties.");
  }

  @Test
  void toString_ContainsCorrectInformation() {
    String expected = "StudentActivity{codeModule='CS101', codePresentation='2021F', idStudent=123, idSite=456, date=7, sumClick=89}";
    assertEquals(expected, studentActivity.toString(),
        "toString should contain correct information.");
  }

  @Test
  void getCodeModule() {
    assertEquals("CS101", studentActivity.getCodeModule());
  }

  @Test
  void setCodeModule() {
    studentActivity.setCodeModule("CS102");
    assertEquals("CS102", studentActivity.getCodeModule());
  }

  @Test
  void getCodePresentation() {
    assertEquals("2021F", studentActivity.getCodePresentation());
  }

  @Test
  void setCodePresentation() {
    studentActivity.setCodePresentation("2022S");
    assertEquals("2022S", studentActivity.getCodePresentation());
  }

  @Test
  void getIdStudent() {
    assertEquals(123, studentActivity.getIdStudent());
  }

  @Test
  void setIdStudent() {
    studentActivity.setIdStudent(456);
    assertEquals(456, studentActivity.getIdStudent());
  }

  @Test
  void getIdSite() {
    assertEquals(456, studentActivity.getIdSite());
  }

  @Test
  void setIdSite() {
    studentActivity.setIdSite(789);
    assertEquals(789, studentActivity.getIdSite());
  }

  @Test
  void getDate() {
    assertEquals(7, studentActivity.getDate());
  }

  @Test
  void setDate() {
    studentActivity.setDate(8);
    assertEquals(8, studentActivity.getDate());
  }

  @Test
  void getSumClick() {
    assertEquals(89, studentActivity.getSumClick());
  }

  @Test
  void setSumClick() {
    studentActivity.setSumClick(90);
    assertEquals(90, studentActivity.getSumClick());
  }

  @Test
  void testEquals() {
    assertNotEquals(studentActivity, null);
    assertNotEquals(studentActivity, "string");
    assertNotEquals(studentActivity, new StudentActivity("CS102", "2021F", 123, 456, 7, 89));
  }
}