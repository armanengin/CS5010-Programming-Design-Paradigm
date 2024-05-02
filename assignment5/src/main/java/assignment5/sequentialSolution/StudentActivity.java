package assignment5.sequentialSolution;

import java.util.Objects;

/**
 * Represents a student activity with its unique module code, presentation code, student ID, site
 * ID, date, and sum of clicks. This class encapsulates the details of a student activity including
 * the module code, presentation code, student ID, site ID, date, and sum of clicks.
 */
public class StudentActivity {

  private String codeModule;
  private String codePresentation;
  private int idStudent;
  private int idSite;
  private int date;
  private int sumClick;

  /**
   * StudentActivity constructor.
   *
   * @param codeModule       the module code
   * @param codePresentation the presentation code
   * @param idStudent        the student ID
   * @param idSite           the site ID
   * @param date             the date
   * @param sumClick         the sum of clicks
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
   * Returns the student ID.
   *
   * @return the student ID
   */
  public int getIdStudent() {
    return idStudent;
  }

  /**
   * Sets the student ID.
   *
   * @param idStudent the student ID
   */
  public void setIdStudent(int idStudent) {
    this.idStudent = idStudent;
  }

  /**
   * Returns the site ID.
   *
   * @return the site ID
   */
  public int getIdSite() {
    return idSite;
  }

  /**
   * Sets the site ID.
   *
   * @param idSite the site ID
   */
  public void setIdSite(int idSite) {
    this.idSite = idSite;
  }

  /**
   * Returns the date.
   *
   * @return the date
   */
  public int getDate() {
    return date;
  }

  /**
   * Sets the date.
   *
   * @param date the date
   */
  public void setDate(int date) {
    this.date = date;
  }

  /**
   * Returns the sum of clicks.
   *
   * @return the sum of clicks
   */
  public int getSumClick() {
    return sumClick;
  }

  /**
   * Sets the sum of clicks.
   *
   * @param sumClick the sum of clicks
   */
  public void setSumClick(int sumClick) {
    this.sumClick = sumClick;
  }

  /**
   * Compares this StudentActivity object with another object for equality.
   *
   * @param o the object to compare
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StudentActivity that)) {
      return false;
    }
    return getIdStudent() == that.getIdStudent() && getIdSite() == that.getIdSite()
        && getDate() == that.getDate() && getSumClick() == that.getSumClick()
        && Objects.equals(getCodeModule(), that.getCodeModule())
        && Objects.equals(getCodePresentation(), that.getCodePresentation());
  }

  /**
   * Returns the hash code of the StudentActivity object.
   *
   * @return the hash code of the StudentActivity object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCodeModule(), getCodePresentation(), getIdStudent(), getIdSite(),
        getDate(), getSumClick());
  }

  /**
   * Returns a string representation of the StudentActivity object.
   *
   * @return a string representation of the StudentActivity object
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
