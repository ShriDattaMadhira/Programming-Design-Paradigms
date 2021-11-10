package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A look-and-say sequence is a sequence of numbers.
 * Given the current number, the next number is obtained by reading the current number out loud,
 * writing what we say. For example, if the current number is "1", then we read that as "one 1" and
 * thus the next number in the sequence will be "11" .
 * Similarly, if the current number is "112321", then we read that as "two 1s, one 2, one 3, one 2,
 * one 1", producing the next number as "2112131211", and so on.
 * A look-and-say sequence is a simple example of run-length encoding.
 * The look-and-say sequence can also be computed in reverse.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private BigInteger current;
  private BigInteger prev;
  private final BigInteger end;

  private static final BigInteger DEFAULT_SEED = new BigInteger("1");
  private static final BigInteger DEFAULT_END = new BigInteger("9".repeat(100));

  /**
   * Constructor for Look and Say Iterator. Constructs a new iterator with a seed and an end.
   * @param seed - Initial number for the sequence.
   * @param end - limit of the sequence.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) {
    if (seed.compareTo(end) >= 0 || seed.toString().contains("0") || seed.signum() <= 0) {
      throw new IllegalArgumentException();
    }
    this.current = seed;
    this.end = end;
    this.prev = seed;
  }

  /**
   * Constructor for Look and Say Iterator. Constructs a new iterator with a seed and a default end.
   * @param seed - Initial number for the sequence.
   */
  public LookAndSayIterator(BigInteger seed) {
    this(seed, DEFAULT_END);
  }

  /**
   * Constructor for Look and say Iterator. Constructs a new iterator with a default seed and end.
   */
  public LookAndSayIterator() {
    this(DEFAULT_SEED, DEFAULT_END);
  }

  @Override
  public BigInteger prev() {
    if (hasPrevious()) {
      current = prev;
      prev = getPrevious(current);
      return prev;
    }
    throw new NoSuchElementException();
  }

  @Override
  public boolean hasPrevious() {
    return prev.toString().length() % 2 == 0 && getPrevious(prev).compareTo(end) < 0;
  }

  private BigInteger getPrevious(BigInteger curr) {
    StringBuilder prevNum = new StringBuilder();
    char[] currString = curr.toString().toCharArray();
    for (int i = 0; i < currString.length; i += 2) {
      char count = currString[i];
      char num = currString[i + 1];
      prevNum.append(String.valueOf(num).repeat(Character.getNumericValue(count)));
    }
    return new BigInteger(prevNum.toString());
  }

  @Override
  public BigInteger next() {
    if (hasNext()) {
      prev = current;
      current = getNext(current);
      return prev;
    }
    throw new NoSuchElementException();
  }

  @Override
  public boolean hasNext() {
    return current.compareTo(end) < 0;
  }

  private BigInteger getNext(BigInteger curr) {
    StringBuilder nextNum = new StringBuilder();
    char[] currString = curr.toString().toCharArray();
    Stack<Character> stk = new Stack<>();
    stk.push(currString[0]);
    int count = 0;
    char t = 0;
    for (int i = 1; i < currString.length; i++) {
      if (stk.peek() == (int) currString[i]) {
        stk.push(currString[i]);
      } else {
        while (!stk.isEmpty()) {
          t = stk.pop();
          count += 1;
        }
        stk.push(currString[i]);
        nextNum.append(count).append(t);
        count = 0;
      }
    }
    while (!stk.isEmpty()) {
      t = stk.pop();
      count += 1;
    }
    nextNum.append(count).append(t);
    return new BigInteger(nextNum.toString());
  }
}
