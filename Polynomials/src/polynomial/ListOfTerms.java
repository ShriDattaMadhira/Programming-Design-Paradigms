package polynomial;

interface ListOfTerms {
  /**
   * Inserts the term in order. If the powers of the terms are same, the coefficients are added.
   * @param coefficient - coefficient of the term.
   * @param power - power of the term.
   * @return - terms in correct order.
   */
  ListOfTerms correctInsert(int coefficient, int power);

  ListOfTerms add(ListOfTerms terms);

  ListOfTerms addHelper(TermElementNode term);

  ListOfTerms addHelper(TermEmptyNode term);

  int getPower(int index);

  int getCoefficient(int power);

  double doMath(double d);

  boolean isSame(ListOfTerms term);

  default boolean compare(TermElementNode elt) {
    return false;
  }

  default boolean compare(TermEmptyNode empt) {
    return false;
  }

  String toStringHelper(StringBuilder currPoly);
}
