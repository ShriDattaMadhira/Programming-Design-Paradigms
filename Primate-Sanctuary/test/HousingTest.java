import org.junit.Before;
import org.junit.Test;

import primates.Primates;
import primates.nmwSize;
import primates.NWMonkeys;
import primates.nwmSpace;
import sanctuary.Housing;
import sanctuary.Enclosure;
import sanctuary.Isolation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test for Housing interface.
 */
public class HousingTest {
  private Housing e;
  private Housing i;

  @Before
  public void setUp() {
    e = new Enclosure(1, 100, "any");
    i = new Isolation(1);
  }

  @Test
  public void testAddMonkeyEnclosure() {
    Primates primate = new NWMonkeys("m-01", "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    assertTrue(e.addMonkey(primate));
  }

  @Test
  public void testGetIDEnclosure() {
    assertEquals(1, e.getID());
  }

  @Test
  public void testGetIDIsolation() {
    assertEquals(1, i.getID());
  }

  @Test
  public void testAddMonkeyIsolation() {
    Primates primate = new NWMonkeys("m-02", "Spider", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    assertTrue(i.addMonkey(primate));
    primate = new NWMonkeys("m-03", "Mangabey", "nuts",
            412, nmwSize.LARGE, nwmSpace.LARGE, 'F', 20, 1);
    assertFalse(i.addMonkey(primate));
  }
}
