package assignment5.sequentialSolution;

import java.util.Objects;

/**
 * Represents a course with its unique module code, presentation code, and the length of its
 * presentation. This class encapsulates the details of a course including its identifier codes and
 * the duration of its presentation.
 */
public class Course {

  /**
   * The module code of the course.
   */
  private String codeModule;
  private String codePresentation;
  private int modulePresentationLength;

  /**
   * Course constructor.
   *
   * @param codeModule               the module code
   * @param codePresentation         the presentation code
   * @param modulePresentationLength the length of the presentation
   */
  public Course(String codeModule, String codePresentation, int modulePresentationLength) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.modulePresentationLength = modulePresentationLength;
  }

  /**
   * Returns the module code.
   *
   * @return the module code
   */
  public String getCodeModule() {
    return codeModule;
  }

  /**
   * Sets the module code.
   *
   * @param codeModule the module code
   */
  public void setCodeModule(String codeModule) {
    this.codeModule = codeModule;
  }

  /**
   * Returns the presentation code.
   *
   * @return the presentation code
   */
  public String getCodePresentation() {
    return codePresentation;
  }

  /**
   * Sets the presentation code.
   *
   * @param codePresentation the presentation code
   */
  public void setCodePresentation(String codePresentation) {
    this.codePresentation = codePresentation;
  }

  /**
   * Returns the module presentation length.
   *
   * @return the module presentation length
   */
  public int getModulePresentationLength() {
    return modulePresentationLength;
  }

  /**
   * Sets the module presentation length.
   *
   * @param modulePresentationLength the module presentation length
   */
  public void setModulePresentationLength(int modulePresentationLength) {
    this.modulePresentationLength = modulePresentationLength;
  }

  /**
   * Returns a string representation of the course.
   *
   * @return a string representation of the course
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return modulePresentationLength == course.modulePresentationLength && Objects.equals(
        codeModule, course.codeModule) && Objects.equals(codePresentation,
        course.codePresentation);
  }

  /**
   * Returns the hash code of the course.
   *
   * @return the hash code of the course
   */
  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, modulePresentationLength);
  }

  /**
   * Returns the string representation of the course.
   *
   * @return the string representation of the course
   */
  @Override
  public String toString() {
    String sb = "Course{" + "codeModule='" + codeModule + '\''
        + ", codePresentation='" + codePresentation + '\''
        + ", modulePresentationLength=" + modulePresentationLength
        + '}';
    return sb;
  }
}
