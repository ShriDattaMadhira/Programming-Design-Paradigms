package questions;

import java.util.ArrayList;

/**
 * Multiple select Question type class.
 * For a given question, there are multiple correct answers.
 */
public class MultipleSelect extends AbstractQClass {
  private ArrayList<String> options = new ArrayList<>();

  /**
   * Constructor for multiple select question type class.
   * @param q - question.
   * @param ans - correct answer set for the question.
   * @param opts - options for the question.
   */
  public MultipleSelect(String q, String ans, String... opts) {
    this.question = q;

    if (opts.length < 3 || opts.length > 8) {
      throw new IllegalArgumentException("Too less (<3) or too many (>8) options.");
    }
    for (int i = 0; i < opts.length; i++) {
      options.add(String.valueOf(i + 1));
    }

    String[] temp = ans.split("\\s+");
    for (String s: temp) {
      if (!options.contains(s)) {
        throw new IllegalArgumentException("Answer can only be between 0 and "
                + (opts.length - 1) + ".");
      }
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

  protected int compare(MultipleChoice mc) {
    return 1;
  }

  protected int compare(TrueFalse tf) {
    return 1;
  }

  protected int compare(MultipleSelect ms) {
    return 0;
  }
}
