package assignment4;

import java.util.List;
import java.util.Random;

/**
 * A record that represents a definition in a context-free grammar. It consists of a list of
 * productions that define how a non-terminal can be expanded.
 */
public record Definition(List<Production> productions) implements Expandable {

  /**
   * Randomly selects one of the productions associated with this definition and expands it using
   * the given grammar.
   *
   * @param grammar the {@link Grammar} object used for expansion, which contains the definitions of
   *                other non-terminals.
   * @return a {@link String} that represents the expanded production.
   */
  public String expand(Grammar grammar) {
    Random rand = new Random();
    Production selectedProduction = productions.get(rand.nextInt(productions.size()));
    return selectedProduction.expand(grammar);
  }
}