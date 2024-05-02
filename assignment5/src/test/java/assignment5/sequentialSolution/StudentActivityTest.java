package assignment5.sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentActivityTest {

  private StudentActivity studentActivity;
  private StudentActivity activity;

  @BeforeEach
  void setUp() {
    studentActivity = new StudentActivity("CS101", "2021F", 123, 456, 789, 10);
    activity = new StudentActivity("CS101", "2021F", 123, 456, 789, 10);
  }

  @Test
  void getCodeModule_ReturnsExpectedValue() {
    assertEquals("CS101", studentActivity.getCodeModule());
  }

  @Test
  void setCodeModule_UpdatesValueCorrectly() {
    studentActivity.setCodeModule("CS102");
    assertEquals("CS102", studentActivity.getCodeModule());
  }

  @Test
  void getCodePresentation_ReturnsExpectedValue() {
    assertEquals("2021F", studentActivity.getCodePresentation());
  }

  @Test
  void setCodePresentation_UpdatesValueCorrectly() {
    studentActivity.setCodePresentation("2022S");
    assertEquals("2022S", studentActivity.getCodePresentation());
  }

  @Test
  void getIdStudent_ReturnsExpectedValue() {
    assertEquals(123, studentActivity.getIdStudent());
  }

  @Test
  void setIdStudent_UpdatesValueCorrectly() {
    studentActivity.setIdStudent(124);
    assertEquals(124, studentActivity.getIdStudent());
  }

  @Test
  void getIdSite_ReturnsExpectedValue() {
    assertEquals(456, studentActivity.getIdSite());
  }

  @Test
  void setIdSite_UpdatesValueCorrectly() {
    studentActivity.setIdSite(457);
    assertEquals(457, studentActivity.getIdSite());
  }

  @Test
  void getDate_ReturnsExpectedValue() {
    assertEquals(789, studentActivity.getDate());
  }

  @Test
  void setDate_UpdatesValueCorrectly() {
    studentActivity.setDate(790);
    assertEquals(790, studentActivity.getDate());
  }

  @Test
  void getSumClick_ReturnsExpectedValue() {
    assertEquals(10, studentActivity.getSumClick());
  }

  @Test
  void setSumClick_UpdatesValueCorrectly() {
    studentActivity.setSumClick(11);
    assertEquals(11, studentActivity.getSumClick());
  }

  @Test
  void equals_SameObject_ReturnsTrue() {
    assertEquals(studentActivity, studentActivity);
  }

  @Test
  void equals_DifferentObject_ReturnsFalse() {
    StudentActivity other = new StudentActivity("CS102", "2021F", 124, 456, 789, 10);
    assertNotEquals(studentActivity, other);
  }

  @Test
  void equals_Self_ReturnsTrue() {
    assertTrue(activity.equals(activity));
  }

  @Test
  void equals_Null_ReturnsFalse() {
    assertFalse(activity.equals(null));
  }

  @Test
  void equals_DifferentClass_ReturnsFalse() {
    assertFalse(activity.equals("a string"));
  }

  @Test
  void equals_DifferentCodeModule_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS102", "2021F", 123, 456, 789, 10);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_DifferentCodePresentation_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS101", "2021S", 123, 456, 789, 10);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_DifferentIdStudent_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS101", "2021F", 124, 456, 789, 10);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_DifferentIdSite_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS101", "2021F", 123, 457, 789, 10);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_DifferentDate_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS101", "2021F", 123, 456, 790, 10);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_DifferentSumClick_ReturnsFalse() {
    StudentActivity different = new StudentActivity("CS101", "2021F", 123, 456, 789, 11);
    assertFalse(activity.equals(different));
  }

  @Test
  void equals_IdenticalFields_ReturnsTrue() {
    StudentActivity identical = new StudentActivity("CS101", "2021F", 123, 456, 789, 10);
    assertTrue(activity.equals(identical));
  }

  @Test
  void toString_ContainsCorrectInformation() {
    String expected = "StudentActivity{codeModule='CS101', codePresentation='2021F', idStudent=123, idSite=456, date=789, sumClick=10}";
    assertEquals(expected, studentActivity.toString());
  }

  @Test
  void testHashCode() {
    assertEquals(activity.hashCode(), studentActivity.hashCode());
  }
}