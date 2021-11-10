package questions;

import java.util.ArrayList;

/**
 * Multiple choice question type class.
 */
public class MultipleChoice extends AbstractQClass {
  private ArrayList<String> options = new ArrayList<>();

  /**
   * Constructor for the multiple choice question.
   * @param q - question.
   * @param ans - answer for the question.
   * @param opts - options for the question.
   */
  public MultipleChoice(String q, String ans, String... opts) {
    this.question = q;

    if (opts.length < 3 || opts.length > 8) {
      throw new IllegalArgumentException("Too less (<3) or too many (>8) options.");
    }
    for (int i = 0; i < opts.length; i++) {
      options.add(String.valueOf(i));
    }
    if (!options.contains(ans)) {
      throw new IllegalArgumentException("Answer should be part of the options.");
    }
    this.correctAnswer = ans;
  }

  @Override
  protected String ansCheck(String answer) {
    if (answer.equals(this.correctAnswer)) {
      return CORRECT;
    }
    return INCORRECT;
  }

  @Override
  public int compareTo(Question o) {
    AbstractQClass absQ = (AbstractQClass) o;
    int ans = absQ.compare(this);
    if (ans == 0) {
      return this.question.compareTo(o.getText());
    }
    return -ans;
  }

  protected int compare(Likert l) {
    return -1;
  }

  protected int compare(MultipleSelect ms) {
    return -1;
  }

  protected int compare(TrueFalse tf) {
    return 1;
  }

  protected int compare(MultipleChoice mc) {
    return 0;
  }
}
