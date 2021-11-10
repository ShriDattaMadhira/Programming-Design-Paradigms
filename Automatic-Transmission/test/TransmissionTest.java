import transmission.AutomaticTransmission;
import transmission.Transmission;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Transmission.
 */
public class TransmissionTest {
  private Transmission t;

  /**
   * Setting up for the tests.
   */
  @Before
  public void setUp() {
    t = new AutomaticTransmission(10, 20, 30, 40, 50);
  }


  private void incSpeed(int n) {
    for (int i = 1; i <= n; i++) {
      t.increaseSpeed();
    }
  }

  private void decSpeed(int n) {
    for (int i = 1; i <= n; i++) {
      t.decreaseSpeed();
    }
  }

  /**
   * Test for threshold5 field. CONDITION: threshold1 > 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreshold1() {
    t = new AutomaticTransmission(0, 60, 90, 120, 150);
  }

  /**
   * Test for threshold5 field. CONDITION: threshold2 > threshold1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreshold2() {
    t = new AutomaticTransmission(30, 29, 90, 120, 150);
  }

  /**
   * Test for threshold5 field. CONDITION: threshold3 > threshold2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreshold3() {
    t = new AutomaticTransmission(30, 60, 59, 120, 150);
  }

  /**
   * Test for threshold5 field. CONDITION: threshold4 > threshold3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreshold4() {
    t = new AutomaticTransmission(30, 60, 90, 89, 150);
  }

  /**
   * Test for threshold5 field. CONDITION: threshold5 > threshold4.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreshold5() {
    t = new AutomaticTransmission(0, 60, 90, 120, 119);
  }

  /**
   * Tests if the overridden toString() is working as expected.
   */
  @Test
  public void testToString() {
    String expected = "Transmission (speed = 0, gear = 0)";
    assertEquals(expected, t.toString());
    incSpeed(25);
    expected = "Transmission (speed = 25, gear = 3)";
    assertEquals(expected, t.toString());
  }

  /**
   * Tests if the getter method for gear is working correctly or not.
   */
  @Test
  public void testgetGear() {
    assertEquals(0, t.getGear());
  }

  /**
   * Tests if the getter method for speed is working correctly or not.
   */
  @Test
  public void testgetSpeed() {
    assertEquals(0, t.getSpeed());
  }

  /**
   * Tests if the increaseSpeed() method is working correctly or not.
   */
  @Test
  public void testincreaseSpeed() {
    incSpeed(1);
    assertEquals(1, t.getSpeed());
    assertEquals(1, t.getGear());
    incSpeed(10);
    assertEquals(11, t.getSpeed());
    assertEquals(2, t.getGear());
    incSpeed(10);
    assertEquals(21, t.getSpeed());
    assertEquals(3, t.getGear());
    incSpeed(10);
    assertEquals(31, t.getSpeed());
    assertEquals(4, t.getGear());
    incSpeed(10);
    assertEquals(41, t.getSpeed());
    assertEquals(5, t.getGear());
    incSpeed(50);
    assertEquals(91, t.getSpeed());
    assertEquals(6, t.getGear());
  }

  /**
   * Tests if the decreaseSpeed() method is working correctly or not.
   */
  @Test
  public void testdecreaseSpeed() {
    incSpeed(61);
    decSpeed(1);
    assertEquals(60, t.getSpeed());
    assertEquals(6, t.getGear());
    decSpeed(11);
    assertEquals(49, t.getSpeed());
    assertEquals(5, t.getGear());
    decSpeed(12);
    assertEquals(37, t.getSpeed());
    assertEquals(4, t.getGear());
    decSpeed(14);
    assertEquals(23, t.getSpeed());
    assertEquals(3, t.getGear());
    decSpeed(10);
    assertEquals(13, t.getSpeed());
    assertEquals(2, t.getGear());
    decSpeed(10);
    assertEquals(3, t.getSpeed());
    assertEquals(1, t.getGear());
    try {
      decSpeed(4);
    } catch (IllegalStateException e) {
      assertEquals("Speed should not be less than 0.", e.getMessage());
    }
  }
}
