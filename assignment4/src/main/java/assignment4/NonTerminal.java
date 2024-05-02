package assignment4;

/**
 * Represents a non-terminal element within a grammar. Non-terminal elements are placeholders that
 * need to be expanded into actual content based on grammar rules.
 */
public class NonTerminal extends GrammarElement {

  /**
   * Constructs a new NonTerminal instance with the specified identifier text.
   *
   * @param text The identifier text for this non-terminal element.
   */
  public NonTerminal(String text) {
    super(text);
  }

  /**
   * Expands this non-terminal element into a string based on the rules defined in the provided
   * grammar. If the non-terminal cannot be found within the grammar, it will handle the
   * {@link UndefinedNonTerminalException} and may return an error message.
   *
   * @param grammar The grammar to use for expansion of this non-terminal.
   * @return The expanded string representation of this non-terminal, or an error message if the
   * non-terminal is undefined in the grammar.
   */
  @Override
  public String expand(Grammar grammar) {
    try {
      return grammar.getDefinition(this.text).expand(grammar);
    } catch (UndefinedNonTerminalException e) {
      // Handle the undefined non-terminal case here. For example:
      // System.out.println("\n" + e.getMessage());
      // Return a placeholder error message or take other appropriate actions
      return e.getMessage();
    }
  }
}