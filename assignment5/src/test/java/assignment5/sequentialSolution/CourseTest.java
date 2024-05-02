package assignment5.sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {
  private Course course;

  @BeforeEach
  void setUp() {
    course = new Course("CS101", "Fall2021", 120);
  }

  @Test
  void getCodeModule_ReturnsCorrectCodeModule() {
    assertEquals("CS101", course.getCodeModule(), "getCodeModule should return correct code module.");
  }

  @Test
  void setCodeModule_SetsCodeModuleCorrectly() {
    course.setCodeModule("CS102");
    assertEquals("CS102", course.getCodeModule(), "setCodeModule should set code module correctly.");
  }

  @Test
  void getCodePresentation_ReturnsCorrectCodePresentation() {
    assertEquals("Fall2021", course.getCodePresentation(), "getCodePresentation should return correct code presentation.");
  }

  @Test
  void setCodePresentation_SetsCodePresentationCorrectly() {
    course.setCodePresentation("Spring2022");
    assertEquals("Spring2022", course.getCodePresentation(), "setCodePresentation should set code presentation correctly.");
  }

  @Test
  void getModulePresentationLength_ReturnsCorrectLength() {
    assertEquals(120, course.getModulePresentationLength(), "getModulePresentationLength should return correct module presentation length.");
  }

  @Test
  void setModulePresentationLength_SetsLengthCorrectly() {
    course.setModulePresentationLength(130);
    assertEquals(130, course.getModulePresentationLength(), "setModulePresentationLength should set module presentation length correctly.");
  }


  @Test
  void equals_Reflexive_ReturnsTrue() {
    assertTrue(course.equals(course), "A course should be equal to itself.");
  }

  @Test
  void equals_Null_ReturnsFalse() {
    assertFalse(course.equals(null), "A course should not be equal to null.");
  }

  @Test
  void equals_DifferentClass_ReturnsFalse() {
    Object differentClass = new Object();
    assertFalse(course.equals(differentClass), "A course should not be equal to an object of a different class.");
  }

  @Test
  void equals_DifferentCodeModule_ReturnsFalse() {
    Course differentCodeModule = new Course("CS102", "Fall2021", 120);
    assertFalse(course.equals(differentCodeModule), "Courses with different code modules should not be equal.");
  }

  @Test
  void equals_DifferentCodePresentation_ReturnsFalse() {
    Course differentCodePresentation = new Course("CS101", "Spring2021", 120);
    assertFalse(course.equals(differentCodePresentation), "Courses with different code presentations should not be equal.");
  }

  @Test
  void equals_DifferentModulePresentationLength_ReturnsFalse() {
    Course differentLength = new Course("CS101", "Fall2021", 100);
    assertFalse(course.equals(differentLength), "Courses with different module presentation lengths should not be equal.");
  }

  @Test
  void equals_AllFieldsMatch_ReturnsTrue() {
    Course identical = new Course("CS101", "Fall2021", 120);
    assertTrue(course.equals(identical), "Courses with all matching fields should be equal.");
  }

  @Test
  void hashCode_EqualsForEqualObjects() {
    Course anotherCourse = new Course("CS101", "Fall2021", 120);
    assertEquals(course.hashCode(), anotherCourse.hashCode(), "hashCode should be equal for two objects with the same values.");
  }

  @Test
  void toString_ContainsCorrectInformation() {
    String expected = "Course{codeModule='CS101', codePresentation='Fall2021', modulePresentationLength=120}";
    assertEquals(expected, course.toString(), "toString should return a string containing the correct information.");
  }
}