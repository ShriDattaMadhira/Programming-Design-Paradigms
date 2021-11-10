import org.junit.Test;

import java.util.Arrays;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for the Question interface where we test if everything is working as expected.
 */
public class QuestionTest {
  private Question qTest;

  /**
   * Test for True/False type questions.
   */
  @Test
  public void trueFalseTest() {
    qTest = new TrueFalse("Is black blue?", "F");
    assertEquals("Is black blue?", qTest.getText());
    assertEquals("Incorrect", qTest.answer("f"));
    assertEquals("Correct", qTest.answer("F"));
    assertEquals("Incorrect", qTest.answer("T"));
  }

  /**
   * Test for multiple choice type questions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleChoiceTest() {
    qTest = new MultipleChoice("How many moons for Earth?", "1", "0", "1", "3", "7");
    assertEquals("How many moons for Earth?", qTest.getText());
    assertEquals("Correct", qTest.answer("1"));
    assertEquals("Incorrect", qTest.answer("7"));

    // Less number of options exception
    qTest = new MultipleChoice("How many planets in solar system?", "8", "3", "9");

    // Answer not in options provided exception.
    qTest = new MultipleChoice("What is capital of the US?", "Toronto",
            "Boston", "WashingtonD.C.", "LosAngeles", "NewYork");
  }

  /**
   * Test for multiple select type questions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectTest() {
    qTest = new MultipleSelect("Cities that are in the US.", "0 2 3",
            "Boston", "Toronto", "Chicago", "NewYork", "Milan");
    assertEquals("Cities that are in the US.", qTest.getText());
    assertEquals("Correct", qTest.answer("0 2 3"));
    assertEquals("Correct", qTest.answer("0   2   3"));
    assertEquals("Incorrect", qTest.answer("0 1 3"));
    assertNotEquals("Incorrect", qTest.answer("2 0 3"));
    assertNotEquals("Incorrect", qTest.answer("3    2  0  "));

    qTest = new MultipleSelect("Cities that are in the US.", "Boston",
            "Boston", "Milan");

    qTest = new MultipleSelect("Cities that are in the US.", "Boston Chicago NewYork",
            "Boston", "Toronto", "Chicago", "NewYork", "Milan", "Madrid", "Barcelona",
            "Manchester", "Amsterdam");

    qTest = new MultipleSelect("Cities that are in the US.", "Boston NewYork",
            "Illinois", "Toronto", "Chicago", "SanFrancisco", "Milan");
  }

  /**
   * Test for likert type questions.
   */
  @Test
  public void likertTest() {
    qTest = new Likert("How satisfied are you with the purchase?");
    assertEquals("How satisfied are you with the purchase?", qTest.getText());
    assertEquals("Correct", qTest.answer("1"));

    qTest = new Likert("How satisfied are you with our company?");
    assertNotEquals("Incorrect", qTest.answer("3"));

    qTest = new Likert("How likely are you to recommend our product?");
    assertEquals("Incorrect", qTest.answer("7"));
  }

  /**
   * Test to see if the questions are ordered as expected.
   * Expected ordering: True/False, multiple choice, multiple select, likert.
   */
  @Test
  public void questionsOrderingTest() {
    Question q1;
    Question q2;
    Question q3;
    Question q4;
    Question q5;
    Question q6;

    q1 = new Likert("How satisfied are you with the purchase?");
    q6 = new Likert("How satisfied are you with the purchase?");
    q2 = new MultipleSelect("Cities that are in the US.", "1",
            "Boston", "Toronto", "Chicago", "NewYork", "Milan");
    q3 = new MultipleChoice("How many moons for Mars?", "0", "0", "1", "3", "7");
    q4 = new Likert("How likely are you to recommend our product?");
    q5 = new TrueFalse("Is black blue?", "F");

    Question[] questionnaire = {q1, q2, q3, q4, q5, q6};

    Question[] correctOrder = {q5, q3, q2, q4, q1, q6};
    Arrays.sort(questionnaire);
    assertEquals(correctOrder, questionnaire);
  }

  @Test
  public void trueFalseOrdering() {
    Question q1 = new TrueFalse("Is this that?", "T");
    Question q2 = new MultipleSelect("What can be that?", "1 2", "1", "2", "3", "4");
    Question q3 = new Likert("Yes");

    assertEquals(-1, q1.compareTo(q2));
    assertEquals(-1, q1.compareTo(q3));
  }

  @Test
  public void multipleChoiceOrdering() {
    Question q1 = new TrueFalse("Is this that?", "T");
    Question q2 = new MultipleChoice("Multiple choice", "1", "1", "2", "3", "4");
    Question q3 = new MultipleSelect("What can be that?", "1 2", "1", "2", "3", "4");
    
    assertEquals(1, q2.compareTo(q1));
    assertEquals(-1, q2.compareTo(q3));
  }

  @Test
  public void multipleSelectOrdering() {
    Question q1 = new MultipleChoice("Multiple choice", "1", "1", "2", "3", "4");
    Question q2 = new MultipleSelect("What can be that?", "1 2", "1", "2", "3", "4");
    Question q3 = new Likert("Fire fires the fire");

    assertEquals(1, q2.compareTo(q1));
    assertEquals(-1, q2.compareTo(q3));
  }

}
