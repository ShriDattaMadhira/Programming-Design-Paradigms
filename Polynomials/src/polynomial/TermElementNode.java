package polynomial;

/**
 * Element node for term.
 */
public class TermElementNode implements ListOfTerms {
  int coefficient;
  int power;
  ListOfTerms rest;

  /**
   * Constructor a term element node with given information.
   * @param coefficient - coefficient of the term.
   * @param power - power of the term.
   * @param term - rest of the terms in the polynomial.
   */
  public TermElementNode(int coefficient, int power, ListOfTerms term) {
    this.coefficient = coefficient;
    this.power = power;
    this.rest = term;
  }

  @Override
  public ListOfTerms correctInsert(int coefficient, int power) {
    if (this.power > power) {
      return new TermElementNode(this.coefficient, this.power,
              rest.correctInsert(coefficient, power));
    } else if (this.power == power) {
      return new TermElementNode(this.coefficient + coefficient, power, rest);
    } else {
      return new TermElementNode(coefficient, power, this);
    }
  }

  @Override
  public ListOfTerms add(ListOfTerms terms) {
    return terms.addHelper(this);
  }

  @Override
  public ListOfTerms addHelper(TermElementNode term) {
    return term.correctInsert(coefficient, power).add(rest);
  }

  @Override
  public ListOfTerms addHelper(TermEmptyNode term) {
    return term.correctInsert(coefficient, power).add(rest);
  }

  @Override
  public int getPower(int index) {
    if (index == 0) {
      return power;
    }
    return rest.getPower(index - 1);
  }

  @Override
  public int getCoefficient(int power) {
    if (this.power == power) {
      return coefficient;
    }
    return rest.getCoefficient(power);
  }

  @Override
  public double doMath(double d) {
    return coefficient * Math.pow(d, power) + rest.doMath(d);
  }

  @Override
  public boolean isSame(ListOfTerms term) {
    return term.compare(this);
  }

  @Override
  public boolean compare(TermElementNode elt) {
    return power == elt.power && coefficient == elt.coefficient && rest.isSame(elt.rest);
  }

  @Override
  public String toStringHelper(StringBuilder currPoly) {
    if (power == 0) {
      return rest.toStringHelper(currPoly.append(String.format("%+d", coefficient)).append(" "));
    }
    return rest.toStringHelper(currPoly.append(String.format("%+dx^%d", coefficient, power))
            .append(" "));
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }
}
