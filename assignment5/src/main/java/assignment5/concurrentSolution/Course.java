package assignment5.concurrentSolution;

import java.util.Objects;

/**
 * Represents a course with a unique combination of module and presentation codes, along with
 * the length of the course presentation. This class encapsulates essential information that
 * defines a specific course within an educational institution.
 */
public class Course {
  /**
   * The unique code identifying the module of the course.
   */
  private String codeModule;
  /**
   * The code representing a specific presentation or offering of the module.
   */
  private String codePresentation;
  /**
   * The length of the course presentation, typically measured in days.
   */
  private int modulePresentationLength;

  /**
   * Constructs a new {@code Course} object with specified details.
   *
   * @param codeModule The module code of the course.
   * @param codePresentation The presentation code of the course.
   * @param modulePresentationLength The length of the course presentation.
   */
  public Course(String codeModule, String codePresentation, int modulePresentationLength) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.modulePresentationLength = modulePresentationLength;
  }

  /**
   * Retrieves the module code of this course.
   *
   * @return A {@code String} representing the module code.
   */
  public String getCodeModule() {
    return codeModule;
  }

  /**
   * Sets the module code for this course.
   *
   * @param codeModule A {@code String} containing the new module code.
   */
  public void setCodeModule(String codeModule) {
    this.codeModule = codeModule;
  }

  /**
   * Retrieves the presentation code of this course.
   *
   * @return A {@code String} representing the presentation code.
   */
  public String getCodePresentation() {
    return codePresentation;
  }

  /**
   * Sets the presentation code for this course.
   *
   * @param codePresentation A {@code String} containing the new presentation code.
   */
  public void setCodePresentation(String codePresentation) {
    this.codePresentation = codePresentation;
  }

  /**
   * Retrieves the length of the course presentation.
   *
   * @return An {@code int} representing the length of the presentation.
   */
  public int getModulePresentationLength() {
    return modulePresentationLength;
  }

  /**
   * Sets the presentation length for this course.
   *
   * @param modulePresentationLength An {@code int} specifying the new length of the presentation.
   */
  public void setModulePresentationLength(int modulePresentationLength) {
    this.modulePresentationLength = modulePresentationLength;
  }

  /**
   * Compares this course to the specified object. The result is {@code true} if and only if
   * the argument is not {@code null} and is a {@code Course} object that represents a course
   * with the same module code, presentation code, and module presentation length as this object.
   *
   * @param o The object to compare this {@code Course} against.
   * @return {@code true} if the given object represents a {@code Course} equivalent to this course, {@code false} otherwise.
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
   * Returns a hash code value for this course.
   *
   * @return A hash code value for this course.
   */
  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, modulePresentationLength);
  }

  /**
   * Returns a string representation of this course, including its module code, presentation code,
   * and presentation length.
   *
   * @return A string representation of this course.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Course{");
    sb.append("codeModule='").append(codeModule).append('\'');
    sb.append(", codePresentation='").append(codePresentation).append('\'');
    sb.append(", modulePresentationLength=").append(modulePresentationLength);
    sb.append('}');
    return sb.toString();
  }
}
