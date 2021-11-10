import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * Testing the RIteartor interface to make sure all the features are working as expected.
 */
public class RIteratorTest {
  private RIterator<BigInteger> iterator;

  /**
   * This test verifies that the two argument constructor in LookAndSay Class behaves as expected
   * when given invalid inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTwoArgConstructor() {
    // seed is negative number, therefore invalid.
    iterator = new LookAndSayIterator(new BigInteger("-1"), new BigInteger("9".repeat(10)));

    // seed is greater than end, therefore invalid.
    iterator = new LookAndSayIterator(new BigInteger("312211"), new BigInteger("222"));
  }

  /**
   * This test verifies that the one argument constructor in LookAndSay Class behaves as expected
   * when given invalid inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOneArgConstructor() {
    // seed is negative number, therefore invalid.
    iterator = new LookAndSayIterator(new BigInteger("-1"));
  }

  /**
   * Test to check if next() is working as expected.
   * Should return a next value if there is one possible.
   * Should throw a NoSuchElementException if there is none possible.
   */
  @Test(expected = NoSuchElementException.class)
  public void testNext() {
    iterator = new LookAndSayIterator(new BigInteger("111221"), new BigInteger("9".repeat(7)));
    // valid cases
    assertEquals("111221", iterator.next().toString());
    assertEquals("312211", iterator.next().toString());
    // invalid cases, returns a NoSuchElementException because seed > end.
    assertEquals("13112211", iterator.next().toString());
  }

  /**
   * Test to check if prev() is working as expected.
   * Should return a previous value if there is one possible.
   * Should throw a NoSuchElementException if there is none possible.
   */
  @Test(expected = NoSuchElementException.class)
  public void testPrev() {
    iterator = new LookAndSayIterator(new BigInteger("21"), new BigInteger("9".repeat(7)));
    // valid cases.
    assertEquals("11", iterator.prev().toString());
    assertEquals("1", iterator.prev().toString());
    // invalid case, returns a NoSuchElementException
    iterator.prev();
  }

  /**
   * Test that verifies that hasNext() is working as expected.
   * Should return true if there is a next value possible.
   * Should return false if there is no next value possible.
   */
  @Test
  public void testHasNext() {
    iterator = new LookAndSayIterator(new BigInteger("111221"), new BigInteger("9".repeat(7)));
    // returns true
    assertTrue(iterator.hasNext());
    iterator.next(); // will return - 111221
    iterator.next(); // will return - 311211
    // returns false
    assertFalse(iterator.hasNext());
  }

  /**
   * Test that verifies that hasPrev() is working as expected.
   * Should return true if there is a previous value possible.
   * Should return false if there is no previous value possible.
   */
  @Test
  public void testHasPrev() {
    iterator = new LookAndSayIterator(new BigInteger("21"), new BigInteger("9".repeat(7)));
    // returns true
    assertTrue(iterator.hasPrevious());
    iterator.prev(); // will return - 11
    iterator.prev(); // will return - 1
    // returns false
    assertFalse(iterator.hasPrevious());
  }
}
