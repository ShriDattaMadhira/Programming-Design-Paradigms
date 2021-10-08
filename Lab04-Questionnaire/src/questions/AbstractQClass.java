package questions;

/**
 * Abstract class for all question types.
 */
public abstract class AbstractQClass implements Question {

  protected String question;
  protected String correctAnswer;

  /**
   * Determines if the answer is correct for a given question. If the answer is
   * correct, this method returns "Correct"; and "Incorrect" otherwise.
   *
   * @param answer the answer given
   * @return "Correct" or "Incorrect"
   */
  @Override
  public String answer(String answer) {
    return ansCheck(answer);
  }

  protected abstract String ansCheck(String answer);

  /**
   * Returns the text of the question.
   *
   * @return the text
   */
  @Override
  public String getText() {
    return this.question;
  }

  protected abstract int compare(Likert l);

  protected abstract int compare(MultipleChoice mc);

  protected abstract int compare(MultipleSelect ms);

  protected abstract int compare(TrueFalse tf);
}
