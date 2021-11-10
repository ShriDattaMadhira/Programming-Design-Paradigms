package questions;

import java.util.ArrayList;

/**
 * Likert Question type.
 * For a given question select your option on a scale of Strongly agree-4 to strongly disagree-0.
 */
public class Likert extends AbstractQClass {

  protected ArrayList<String> options = new ArrayList<String>() {
    {
      add("1");
      add("2");
      add("3");
      add("4");
      add("5");
    }
  };

  /**
   * Constructor for Likert Question type class.
   * @param question - Question asked.
   */
  public Likert(String question) {
    this.question = question;
  }

  @Override
  protected String ansCheck(String answer) {
    if (options.contains(answer)) {
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

  protected int compare(MultipleChoice mc) {
    return 1;
  }

  protected int compare(MultipleSelect ms) {
    return 1;
  }

  protected int compare(TrueFalse tf) {
    return 1;
  }

  protected int compare(Likert l) {
    return 0;
  }

}
