package polynomial;

/**
 * Empty Node for polynomial term that represents the end of the polynomial.
 */
public class TermEmptyNode implements ListOfTerms {
  @Override
  public ListOfTerms correctInsert(int coefficient, int power) {
    return new TermElementNode(coefficient, power, this);
  }

  @Override
  public ListOfTerms add(ListOfTerms terms) {
    return terms;
  }

  @Override
  public ListOfTerms addHelper(TermElementNode term) {
    return term;
  }

  @Override
  public ListOfTerms addHelper(TermEmptyNode term) {
    return new TermEmptyNode();
  }

  @Override
  public int getPower(int index) {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public double doMath(double d) {
    return 0;
  }

  @Override
  public boolean isSame(ListOfTerms term) {
    return term.compare(this);
  }

  @Override
  public boolean compare(TermEmptyNode empt) {
    return true;
  }

  @Override
  public String toStringHelper(StringBuilder currPoly) {
    if (currPoly.charAt(0) == '+') {
      return currPoly.substring(1, currPoly.length() - 1);
    }
    return currPoly.substring(0, currPoly.length() - 1);
  }

  @Override
  public String toString() {
    return "0";
  }
}
