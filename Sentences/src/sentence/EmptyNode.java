package sentence;

/**
 * This is an empty node that is inserted at the end of the list.
 * If you encounter this, that means you have reached the end of the list.
 */
public class EmptyNode implements Sentence {
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public String longestWordHelper(String currLongest) {
    return currLongest;
  }

  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  @Override
  public Sentence clone() {
    return this;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public String toStringHelper(StringBuilder curr) {
    return curr.substring(1, curr.length());
  }
}
