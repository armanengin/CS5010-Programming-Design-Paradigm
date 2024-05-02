package assignment4;

import java.util.Objects;

/**
 * Represents an abstract grammar element with text content. This element can be expanded to
 * generate a string based on a grammar definition.
 */
public abstract class GrammarElement implements Expandable {

  /**
   * The text content of this grammar element.
   */
  protected final String text;

  /**
   * Creates a new grammar element with the specified text.
   *
   * @param text The text content of this grammar element.
   */
  public GrammarElement(String text) {
    this.text = text;
  }

  /**
   * Indicates whether some other object is "equal to" this one by comparing texts.
   *
   * @param o The reference object with which to compare.
   * @return True if this object is the same as the {@code o} argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GrammarElement that)) {
      return false;
    }
    return Objects.equals(text, that.text);
  }

  /**
   * Returns a hash code value for this object, derived from its text.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  /**
   * Returns a string representation of this grammar element, including its text.
   *
   * @return A string representation of this grammar element.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("{");
    sb.append("text='").append(text).append('\'');
    sb.append('}');
    return sb.toString();
  }
}