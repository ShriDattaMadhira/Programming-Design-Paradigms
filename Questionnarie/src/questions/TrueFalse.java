package questions;

import java.util.ArrayList;

/**
 * True/False Question type class.
 * For a given statement answer whether it is true or false.
 */
public class TrueFalse extends AbstractQClass {
  protected ArrayList<String> options = new ArrayList<>() {
    {
      add("t");
      add("f");
    }
  };

  /**
   * Constructor for True/False question type.
   * @param q - question/statement to be determined true or false.
   * @param ans - the correct answer.
   */
  public TrueFalse(String q, String ans) {
    this.question = q;
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
    return -1;
  }

  protected int compare(MultipleSelect ms) {
    return -1;
  }

  protected int compare(TrueFalse tf) {
    return 0;
  }
}
