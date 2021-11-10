package sentence;

/**
 * It is a word node that contains a word and link to the rest of the sentence.
 */
public class WordNode implements Sentence {
  private final String word;
  private final Sentence rest;

  /**
   * Constructor for the WordNode.
   * @param word - word.
   * @param rest - rest of the sentence.
   */
  public WordNode(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    return 1 + rest.getNumberOfWords();
  }

  @Override
  public String longestWordHelper(String currLongest) {
    if (word.length() > currLongest.length()) {
      return rest.longestWordHelper(word);
    }
    return rest.longestWordHelper(currLongest);
  }

  @Override
  public String longestWord() {
    return longestWordHelper("");
  }

  @Override
  public Sentence merge(Sentence other) {
    return new WordNode(word, rest.merge(other));
  }

  @Override
  public Sentence clone() {
    return new WordNode(word, rest.clone());
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  @Override
  public String toStringHelper(StringBuilder curr) {
    return rest.toStringHelper(curr.append(" ").append(word));
  }
}
