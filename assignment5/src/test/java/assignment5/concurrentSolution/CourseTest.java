package assignment5.concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

  private Course course;

  @BeforeEach
  void setUp() {
    course = new Course("CS101", "2021F", 10);
  }

  @Test
  void getCodeModule_ShouldReturnCorrectCodeModule() {
    assertEquals("CS101", course.getCodeModule(),
        "The code module should match the one set in setUp.");
  }

  @Test
  void setCodeModule_ShouldChangeCodeModule() {
    course.setCodeModule("CS102");
    assertEquals("CS102", course.getCodeModule(), "The code module should be updated to CS102.");
  }

  @Test
  void getCodePresentation_ShouldReturnCorrectCodePresentation() {
    assertEquals("2021F", course.getCodePresentation(),
        "The code presentation should match the one set in setUp.");
  }

  @Test
  void setCodePresentation_ShouldChangeCodePresentation() {
    course.setCodePresentation("2022S");
    assertEquals("2022S", course.getCodePresentation(),
        "The code presentation should be updated to 2022S.");
  }

  @Test
  void getModulePresentationLength_ShouldReturnCorrectLength() {
    assertEquals(10, course.getModulePresentationLength(),
        "The module presentation length should match the one set in setUp.");
  }

  @Test
  void setModulePresentationLength_ShouldChangeLength() {
    course.setModulePresentationLength(15);
    assertEquals(15, course.getModulePresentationLength(),
        "The module presentation length should be updated to 15.");
  }

  @Test
  void equals_SameObject_ShouldBeEqual() {
    assertTrue(course.equals(course), "The course should be equal to itself.");
  }

  @Test
  void equals_DifferentObjectSameValues_ShouldBeEqual() {
    Course anotherCourse = new Course("CS101", "2021F", 10);
    assertEquals(course, anotherCourse, "Two courses with the same values should be equal.");
  }

  @Test
  void equals_DifferentObjectDifferentValues_ShouldNotBeEqual() {
    Course anotherCourse = new Course("CS102", "2022S", 15);
    assertNotEquals(course, anotherCourse,
        "Two courses with different values should not be equal.");
  }

  @Test
  void toString_ShouldReturnCorrectStringFormat() {
    String expected = "Course{codeModule='CS101', codePresentation='2021F', modulePresentationLength=10}";
    assertEquals(expected, course.toString(),
        "The toString method should return the correct string representation of the course.");
  }

  @Test
  void testHashCode() {
    Course anotherCourse = new Course("CS101", "2021F", 10);
    assertEquals(course.hashCode(), anotherCourse.hashCode(),
        "The hash codes of two equal courses should be the same.");
  }

  @Test
  void testEquals() {
    Course anotherCourse = new Course("CS101", "2021F", 10);
    assertNotEquals(anotherCourse, null);
    assertNotEquals(anotherCourse, "string");
    assertNotEquals(anotherCourse, new Course("", "", 0));
    assertEquals(course, anotherCourse,
        "Two courses with the same values should be equal.");
  }
}