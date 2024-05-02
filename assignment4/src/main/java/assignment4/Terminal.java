package assignment4;

/**
 * A class representing a Terminal which stores a text and can expand itself.
 */
public class Terminal extends GrammarElement {

  /**
   * Constructs a new Terminal with the specified text.
   *
   * @param text the text of this Terminal
   */
  public Terminal(String text) {
    super(text);
  }

  /**
   * Expands this Terminal using the specified grammar.
   *
   * @param grammar the grammar to use for expansion
   * @return the expanded string
   */
  @Override
  public String expand(Grammar grammar) {
    return this.text;
  }
}