import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;

/**
 * Tests written to check if the Sentence interface functionality is working as expected.
 */
public class SentenceTest {

  private Sentence sentence1;
  private Sentence sentence2;
  private Sentence sentence3;
  private Sentence sentence4;

  /**
   * Setting up all the objects for the tests.
   */
  @Before
  public void setUp() {
    sentence1 = new EmptyNode();
    sentence2 = new WordNode("Scuderia", new WordNode("Ferrari", new EmptyNode()));
    sentence3 = new WordNode("Ferrari", new PunctuationNode(";",
            new WordNode("F1", new EmptyNode())));
    sentence4 = new WordNode("Scuderia", new WordNode("Ferrrari",
            new PunctuationNode(".", new EmptyNode())));
  }

  /**
   * Test to check if the number of words returned are as expected.
   */
  @Test
  public void testGetNumberOfWords() {
    assertEquals(0, sentence1.getNumberOfWords());
    assertEquals(2, sentence2.getNumberOfWords());
    assertEquals(2, sentence3.getNumberOfWords());
    assertNotEquals(3, sentence3.getNumberOfWords());
    assertNotEquals(3, sentence4.getNumberOfWords());
    assertEquals(2, sentence4.getNumberOfWords());
  }

  /**
   * Test to check if the longest word returned is as expected.
   */
  @Test
  public void testLongestWord() {
    assertEquals("", sentence1.longestWord());
    assertEquals("Scuderia", sentence2.longestWord());
    assertEquals("Ferrari", sentence3.longestWord());
    assertEquals("Scuderia", sentence4.longestWord());
    assertNotEquals("Ferrrari", sentence4.longestWord());
  }

  /**
   * Test to check if the merge functionality is working as expected or not.
   */
  @Test
  public void testMerge() {
    Sentence merged = sentence1.merge(sentence2);
    assertEquals("Scuderia Ferrari", merged.toString());
    merged = sentence4.merge(sentence2);
    assertEquals("Scuderia Ferrrari. Scuderia Ferrari", merged.toString());
    merged = sentence2.merge(sentence3);
    assertEquals("Scuderia Ferrari Ferrari; F1", merged.toString());
  }

  /**
   * Test to check if cloning the sentence is working as expected.
   */
  @Test
  public void testClone() {
    Sentence cloned = sentence1.clone();
    assertEquals(0, cloned.getNumberOfWords());
    assertEquals("", cloned.longestWord());
    cloned = sentence2.clone();
    assertEquals(2, cloned.getNumberOfWords());
    assertEquals("Scuderia", cloned.longestWord());
    cloned = sentence3.clone();
    assertEquals(2, cloned.getNumberOfWords());
    assertEquals("Ferrari", cloned.longestWord());
  }

  /**
   * Test to check if the overridden toString method is working as expected.
   */
  @Test
  public void testToString() {
    assertEquals("", sentence1.toString());
    assertEquals("Scuderia Ferrari", sentence2.toString());
    assertEquals("Ferrari; F1", sentence3.toString());
    assertEquals("Scuderia Ferrrari.", sentence4.toString());
  }

}