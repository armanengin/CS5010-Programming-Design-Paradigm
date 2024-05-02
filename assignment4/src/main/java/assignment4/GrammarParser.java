package assignment4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides functionality to parse a JSON string into a {@link Grammar} object. This class uses Gson
 * for parsing and custom deserialization.
 */
public class GrammarParser {

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private GrammarParser() {
  }

  /**
   * Parses a JSON string into a {@link Grammar} object.
   *
   * @param json A JSON string representing a grammar.
   * @return A {@link Grammar} object parsed from the JSON string.
   */
  public static Grammar parse(String json) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Grammar.class, new GrammarDeserializer());
    Gson gson = gsonBuilder.create();
    return gson.fromJson(json, Grammar.class);
  }

  /**
   * A custom deserializer for {@link Grammar} objects. This class is used internally by
   * {@link GrammarParser} to provide custom parsing behavior.
   */
  private static class GrammarDeserializer implements JsonDeserializer<Grammar> {

    /**
     * Deserializes the specified JSON element into a Grammar object.
     *
     * @param json    the JSON element to deserialize
     * @param typeOfT the type of the desired object
     * @param context the deserialization context
     * @return the deserialized Grammar object
     * @throws JsonParseException if the JSON is not in the expected format
     */
    @Override
    public Grammar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject jsonObject = json.getAsJsonObject();

      String titleKeyHeader = "grammarTitle";
      String descKeyHeader = "grammarDesc";

      String grammarTitle = jsonObject.get(titleKeyHeader).getAsString();
      String grammarDesc = jsonObject.get(descKeyHeader).getAsString();

      // use TreeMap for case-insensitive keys
      Map<String, Definition> definitions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

      jsonObject.entrySet().stream()
          .filter(entry -> !entry.getKey().equals(titleKeyHeader) && !entry.getKey()
              .equals(descKeyHeader))
          .forEach(entry -> {
            String key = entry.getKey();
            JsonArray productionsJson = entry.getValue().getAsJsonArray();
            List<Production> productions = new ArrayList<>();

            for (JsonElement productionElement : productionsJson) {
              String productionString = productionElement.getAsString();
              List<GrammarElement> elements = new ArrayList<>();
              // use regex to split the production string into non-terminal and terminal symbols
              Matcher matcher = Pattern.compile("<[^>]+>|[^\\s<]+").matcher(productionString);

              while (matcher.find()) {
                String part = matcher.group();
                if (isNonTerminal(part)) {
                  elements.add(new NonTerminal(part.substring(1, part.length() - 1)));
                } else {
                  elements.add(new Terminal(part));
                }
              }

              productions.add(new Production(elements));
            }

            definitions.put(key, new Definition(productions));
          });

      return new Grammar(grammarTitle, grammarDesc, definitions);
    }

    /**
     * Determines whether a given string represents a non-terminal symbol in the grammar.
     * <p>
     * Non-terminal symbols are expected to be enclosed in angle brackets, e.g.,
     * {@code <non-terminal>}.
     *
     * @param part The string to check.
     * @return {@code true} if the string represents a non-terminal symbol, {@code false} otherwise.
     */
    private boolean isNonTerminal(String part) {
      String nonTerminalRegex = "^<.*>$";
      return part.matches(nonTerminalRegex);
    }
  }
}
