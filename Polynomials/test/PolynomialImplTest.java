import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests to see if our implementation is correct or not.
 */
public class PolynomialImplTest {
  Polynomial one;
  Polynomial two;
  Polynomial three;
  Polynomial four;

  /**
   * Setting up for tests by creating all the necessary objects.
   */
  @Before
  public void setUp() {
    one = new PolynomialImpl();
    two = new PolynomialImpl("3x^3 +2x^2 +1");
    three = new PolynomialImpl("4x^4 +2x^2 +1 3x^3 5x^5");
    four = new PolynomialImpl("2x^222");
  }

  /**
   * Test to see if adding two polynomials is working as expected.
   *
   * @throws IllegalArgumentException if parameter is not the same concrete type
   *                                  as the current object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAdd() {
    assertEquals("3x^3 +2x^2 +1", one.add(two).toString());
    assertEquals("5x^5 +4x^4 +6x^3 +4x^2 +2", two.add(three).toString());
    assertEquals("2x^222 +3x^3 +2x^2 +1", four.add(two).toString());
    one.add(null);
  }

  /**
   * Test to see if the adding a term to a polynomial is working as expected.
   *
   * @throws IllegalArgumentException if the power is negative
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTerm() {
    one.addTerm(3, 3);
    assertEquals("3x^3", one.toString());
    one.addTerm(2, 2);
    assertEquals("3x^3 +2x^2", one.toString());

    two.addTerm(5, 3);
    assertEquals("8x^3 +2x^2 +1", two.toString());

    three.addTerm(99, -99);
  }

  /**
   * Test to see if this polynomial is the same as the parameter polynomial.
   */
  @Test
  public void testIsSame() {
    assertTrue(one.isSame(new PolynomialImpl()));
    assertFalse(one.isSame(new PolynomialImpl("1x^1")));

    assertFalse(two.isSame(new PolynomialImpl("5x^3 +6x^1")));
    assertFalse(three.isSame(new PolynomialImpl("6x^6 +1x^1 +99")));

  }

  /**
   * Test to see if evaluating the value of this polynomial at the given value of the variable is
   * working as expected.
   */
  @Test
  public void testEvaluate() {
    assertEquals(6, two.evaluate(1), 0);
    assertEquals(15, three.evaluate(1), 0);

    assertEquals(2, four.evaluate(-1), 0);
    assertEquals(-1, three.evaluate(-1), 0);
  }

  /**
   * Test to see if the coefficient of a term is returned as expected.
   */
  @Test
  public void testGetCoefficient() {
    assertEquals(3, two.getCoefficient(3));
    assertEquals(5, three.getCoefficient(5));

    assertEquals(0, two.getCoefficient(4));
    assertEquals(0, three.getCoefficient(6));
  }

  /**
   * Test to see if the degree of polynomial is returned as expected.
   */
  @Test
  public void testGetDegree() {
    assertEquals(0, one.getDegree());
    assertNotEquals(1, one.getDegree());

    assertEquals(5, three.getDegree());
    assertEquals(3, two.getDegree());
  }

  /**
   * Testing if the toString() is behaving as expected.
   */
  @Test
  public void testToString() {
    assertEquals("3x^3 +2x^2 +1", two.toString());
    one.addTerm(1, 1);
    assertEquals("1x^1", one.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringConstructor() {
    // negative power not allowed.
    Polynomial temp = new PolynomialImpl("2x^-2");

    //string is null
    temp = new PolynomialImpl(null);

    // empty string
    temp = new PolynomialImpl("");
    assertEquals("", temp.toString());

    //single element string
    temp = new PolynomialImpl("5");
    assertEquals("5", temp.toString());

    temp = new PolynomialImpl("2x^2 -1x^2 +1x^1");
    assertEquals("1x^2 +1x^1", temp.toString());

    assertEquals("3x^3 +2x^2 +1", two.toString());

    assertEquals("0", one.toString());
  }
}
