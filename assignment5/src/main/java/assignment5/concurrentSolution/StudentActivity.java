package assignment5.concurrentSolution;

import java.util.Objects;

/**
 * Represents the activity of a student within a particular module and presentation. This class
 * stores details about the student's interactions, such as clicks on the learning platform, along
 * with identifying information about the module, presentation, and the student themselves.
 */
public class StudentActivity {

  /**
   * The module code associated with this activity.
   */
  private String codeModule;
  /**
   * The presentation code associated with this activity.
   */
  private String codePresentation;
  /**
   * The unique identifier of the student.
   */
  private int idStudent;
  /**
   * The unique identifier of the site or resource the student interacted with.
   */
  private int idSite;
  /**
   * The date of the activity, often represented as the number of days relative to the start of the presentation.
   */
  private int date;
  /**
   * The total number of clicks or interactions the student made on this date.
   */
  private int sumClick;

  /**
   * Constructs a new StudentActivity instance.
   *
   * @param codeModule        the module code.
   * @param codePresentation  the presentation code.
   * @param idStudent         the student's identifier.
   * @param idSite            the site or resource identifier.
   * @param date              the date of the activity.
   * @param sumClick          the total number of clicks or interactions.
   */
  public StudentActivity(String codeModule, String codePresentation, int idStudent, int idSite,
      int date, int sumClick) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.idStudent = idStudent;
    this.idSite = idSite;
    this.date = date;
    this.sumClick = sumClick;
  }

  /**
   * Retrieves the code module of the student activity or course.
   *
   * @return A string representing the code module.
   */
  public String getCodeModule() {
    return codeModule;
  }

  /**
   * Sets the code module for the student activity or course.
   *
   * @param codeModule A string representing the code module to be set.
   */
  public void setCodeModule(String codeModule) {
    this.codeModule = codeModule;
  }

  /**
   * Retrieves the code presentation of the student activity or course.
   *
   * @return A string representing the code presentation.
   */
  public String getCodePresentation() {
    return codePresentation;
  }

  /**
   * Sets the code presentation for the student activity or course.
   *
   * @param codePresentation A string representing the code presentation to be set.
   */
  public void setCodePresentation(String codePresentation) {
    this.codePresentation = codePresentation;
  }

  /**
   * Gets the student's ID.
   *
   * @return the unique identifier of the student.
   */
  public int getIdStudent() {
    return idStudent;
  }

  /**
   * Sets the student's ID.
   *
   * @param idStudent the unique identifier to set for the student.
   */
  public void setIdStudent(int idStudent) {
    this.idStudent = idStudent;
  }

  /**
   * Gets the site's ID.
   *
   * @return the unique identifier of the site or resource the student interacted with.
   */
  public int getIdSite() {
    return idSite;
  }

  /**
   * Sets the site's ID.
   *
   * @param idSite the unique identifier to set for the site or resource.
   */
  public void setIdSite(int idSite) {
    this.idSite = idSite;
  }

  /**
   * Gets the date of the student's activity.
   *
   * @return the date of the activity, often represented as the number of days relative to the start of the presentation.
   */
  public int getDate() {
    return date;
  }

  /**
   * Sets the date of the student's activity.
   *
   * @param date the date of the activity to set, often represented as the number of days relative to the start of the presentation.
   */
  public void setDate(int date) {
    this.date = date;
  }

  /**
   * Gets the total number of clicks or interactions made by the student.
   *
   * @return the total number of clicks or interactions.
   */
  public int getSumClick() {
    return sumClick;
  }

  /**
   * Sets the total number of clicks or interactions made by the student.
   *
   * @param sumClick the total number of clicks or interactions to set.
   */
  public void setSumClick(int sumClick) {
    this.sumClick = sumClick;
  }

  /**
   * Compares this StudentActivity to another object for equality. Two StudentActivity instances
   * are considered equal if all their fields match.
   *
   * @param o the object to be compared for equality with this StudentActivity.
   * @return {@code true} if the specified object is equal to this StudentActivity; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentActivity that = (StudentActivity) o;
    return idStudent == that.idStudent && idSite == that.idSite && date == that.date
        && sumClick == that.sumClick && Objects.equals(codeModule, that.codeModule)
        && Objects.equals(codePresentation, that.codePresentation);
  }

  /**
   * Returns the hash code value for this StudentActivity. The hash code is computed based on
   * the fields of the StudentActivity.
   *
   * @return the hash code value for this StudentActivity.
   */
  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, idStudent, idSite, date, sumClick);
  }

  /**
   * Returns a string representation of this StudentActivity. The string representation includes
   * the class name and all the fields of the StudentActivity.
   *
   * @return a string representation of this StudentActivity.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StudentActivity{");
    sb.append("codeModule='").append(codeModule).append('\'');
    sb.append(", codePresentation='").append(codePresentation).append('\'');
    sb.append(", idStudent=").append(idStudent);
    sb.append(", idSite=").append(idSite);
    sb.append(", date=").append(date);
    sb.append(", sumClick=").append(sumClick);
    sb.append('}');
    return sb.toString();
  }
}
