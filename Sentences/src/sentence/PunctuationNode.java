package sentence;

/**
 * It is a word node that contains a punctuation and link to the rest of the sentence.
 */
public class PunctuationNode implements Sentence {
  private final String punctuation;
  private final Sentence rest;

  /**
   * Constructor for Punctuation node.
   * @param punctuation - punctuation.
   * @param rest - rest of the sentence.
   */
  public PunctuationNode(String punctuation, Sentence rest) {
    this.punctuation = punctuation;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    return rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    return rest.longestWord();
  }

  @Override
  public String longestWordHelper(String currLongest) {
    return rest.longestWordHelper(currLongest);
  }

  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(punctuation, rest.merge(other));
  }

  @Override
  public Sentence clone() {
    return new PunctuationNode(punctuation, rest.clone());
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  @Override
  public String toStringHelper(StringBuilder curr) {
    return rest.toStringHelper(curr.append(punctuation));
  }
}
