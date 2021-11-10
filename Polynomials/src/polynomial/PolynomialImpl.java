package polynomial;

import java.util.Scanner;

/**
 * A polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights are numeric). Each term may either be an
 * integer power of that variable, or some function in that variable, but never
 * both (i.e. (log x)^2 is not allowed).
 */
public class PolynomialImpl implements Polynomial {

  private ListOfTerms head;

  /**
   * A constructor with no parameters.
   * Creates a polynomial with no terms, i.e., the polynomial 0.
   */
  public PolynomialImpl() {
    head = new TermEmptyNode();
  }

  /**
   * A constructor that takes a polynomial as a string, parses it, and creates the polynomial.
   * @param s - polynomial given as a String.
   */
  public PolynomialImpl(String s) {
    if (s == null) {
      throw new IllegalArgumentException("No string given to parse.");
    }

    ListOfTerms terms = new TermEmptyNode();
    Scanner scanner = new Scanner(s);
    scanner.useDelimiter(" ");
    while (scanner.hasNext()) {
      String temp = scanner.next();
      String[] res = temp.split("x\\^");
      int coeff = Integer.parseInt(res[0]);
      int pow = 0;
      if (res.length > 1) {
        pow = Integer.parseInt(res[1]);
      }
      if (pow < 0) {
        throw new IllegalArgumentException("Power of the term " + temp
                + " in polynomial is less than zero.");
      }
      terms = terms.correctInsert(coeff, pow);
    }
    head = terms;
  }

  private PolynomialImpl(ListOfTerms start) {
    this.head = start;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    ListOfTerms result;
    if (other == null) {
      throw new IllegalArgumentException("Given polynomial cannot be null");
    } else if (other instanceof PolynomialImpl) {
      PolynomialImpl temp = (PolynomialImpl) other;
      result = head.add(temp.head);
    } else {
      throw new IllegalArgumentException("Given polynomial should be of same type");
    }
    return new PolynomialImpl(result);
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("power cannot be less than zero.");
    }
    head = head.correctInsert(coefficient, power);
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (poly instanceof PolynomialImpl) {
      PolynomialImpl temp = (PolynomialImpl) poly;
      return head.isSame(temp.head);
    }
    return false;
  }

  @Override
  public double evaluate(double x) {
    return head.doMath(x);
  }

  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return head.getPower(0);
  }

  @Override
  public String toString() {
    return head.toString();
  }
}
