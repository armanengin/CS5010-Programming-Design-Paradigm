package assignment4;

import java.util.List;

/**
 * Represents a production in a grammar. A production is a rule that defines how a non-terminal can
 * be expanded into a sequence of grammar elements.
 */
public record Production(List<GrammarElement> elements) implements Expandable {

  /**
   * Expands this production into a string representation by expanding each of its grammar elements
   * according to the provided grammar rules.
   *
   * @param grammar The grammar to use for expanding the grammar elements.
   * @return The expanded string representation of this production.
   */
  @Override
  public String expand(Grammar grammar) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < elements.size(); i++) {
      GrammarElement element = elements.get(i);
      String expandedElement = element.expand(grammar);
      if (i > 0 && !startsWithPunctuation(expandedElement)) {
        result.append(" ");
      }
      result.append(expandedElement);
    }
    return result.toString();
  }

  /**
   * Checks if a string starts with a punctuation mark. This is used to determine whether to prepend
   * a space before appending the expanded string of an element.
   *
   * @param s The string to check.
   * @return {@code true} if the string starts with punctuation, {@code false} otherwise.
   */
  private boolean startsWithPunctuation(String s) {
    String punctuationRegex = "^[.,;:!\\-?].*";
    return s.matches(punctuationRegex);
  }
}