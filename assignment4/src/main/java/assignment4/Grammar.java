package assignment4;

import java.util.Map;

/**
 * Represents a context-free grammar with a specific title and description, including a collection
 * of definitions for non-terminal symbols. This grammar can be used to generate strings by
 * expanding these definitions according to the grammar's rules.
 */
public record Grammar(String grammarTitle, String grammarDesc,
                      Map<String, Definition> definitions) {

  /**
   * Generates a string by starting the expansion process from the "start" definition. This method
   * initiates the recursive expansion of the grammar's rules to produce a string.
   *
   * @return A {@link String} generated by expanding the "start" definition according to the grammar
   * rules.
   * @throws IllegalArgumentException      if the grammar does not contain a "start" definition,
   *                                       indicating that the starting point for generation is
   *                                       missing.
   * @throws UndefinedNonTerminalException if the expansion process encounters a non-terminal symbol
   *                                       that does not have a corresponding definition in the
   *                                       grammar, indicating a broken or incomplete grammar.
   */
  public String generate() {
    String startKeyHeader = "start";
    if (!definitions.containsKey(startKeyHeader)) {
      throw new IllegalArgumentException("No <start> definition found in grammar.");
    }
    return definitions.get(startKeyHeader).expand(this);
  }

  /**
   * Retrieves the definition associated with a specific non-terminal symbol within the grammar.
   *
   * @param key The key (non-terminal symbol) whose definition is to be retrieved.
   * @return The {@link Definition} associated with the specified key.
   * @throws UndefinedNonTerminalException if the key does not correspond to any definition within
   *                                       the grammar, indicating an attempt to use an undefined
   *                                       non-terminal symbol.
   */
  public Definition getDefinition(String key) {
    if (!definitions.containsKey(key)) {
      throw new UndefinedNonTerminalException("ERROR!: Undefined non-terminal encountered: " + key);
    }
    return definitions.get(key);
  }
}