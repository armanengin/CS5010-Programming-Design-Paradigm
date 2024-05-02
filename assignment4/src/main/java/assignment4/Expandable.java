package assignment4;

/**
 * An interface for components that can be expanded using a specific grammar. This is typically used
 * in context-free grammars where non-terminal symbols can be expanded into terminal symbols based
 * on predefined rules or productions.
 */
public interface Expandable {

  /**
   * Expands this component using the provided grammar. The method determines how the expansion
   * should be performed based on the grammar rules associated with this Expandable element.
   *
   * @param grammar The {@link Grammar} object that contains the rules and definitions needed for
   *                the expansion process. It is assumed that this grammar provides the necessary
   *                context and rules to expand the current non-terminal symbol into one or more
   *                terminal symbols or further non-terminal symbols.
   * @return A {@link String} representing the result of expanding this Expandable element according
   * to the specified grammar rules. The returned string could be a concatenation of terminal
   * symbols or further expansions of non-terminal symbols, depending on the grammar rules.
   */
  String expand(Grammar grammar);
}
