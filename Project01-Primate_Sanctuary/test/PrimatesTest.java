import org.junit.Before;
import org.junit.Test;

import primates.Primates;
import primates.nmwSize;
import primates.NWMonkeys;
import primates.nwmSpace;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Primates.
 */
public class PrimatesTest {
  private Primates prim1;
  private static int counter = 0;

  /**
   * Setting up for the tests.
   */
  @Before
  public void setUp() {
    counter += 1;
    prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
  }

  /**
   * Test if monkey name is inserted properly or not.
   */
  @Test
  public void testGetMonkeyName() {
    assertEquals("m-0" + counter, prim1.getMonkeyName());
  }

  /**
   * Test if exception for monkey name is thrown correctly or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMonkeyName() {
    prim1 = new NWMonkeys("m-01", "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
  }

  /**
   * Test if monkey species is inserted properly or not.
   */
  @Test
  public void testGetSpecies() {
    assertEquals("driller", prim1.getSpecies());
  }

  /**
   * Test if monkey favorite food is inserted properly or not.
   */
  @Test
  public void testGetFavFood() {
    assertEquals("nuts", prim1.getFavFood());
  }

  /**
   * Test if monkey food quantity is inserted properly or not.
   */
  @Test
  public void testGetFoodQty() {
    assertEquals(90, prim1.getFoodQty());
  }

  /**
   * Test if monkey size is inserted properly or not.
   */
  @Test
  public void testGetMonkeySize() {
    assertEquals(nmwSize.SMALL, prim1.getMonkeySize());
  }

  /**
   * Test if monkey space requirement is inserted properly or not.
   */
  @Test
  public void testGetSpaceReq() {
    assertEquals(nwmSpace.SMALL.getSpaceRequirement(), prim1.getSpaceReq());
  }

  /**
   * Test if monkey's sex is inserted properly or not.
   */
  @Test
  public void testGetSex() {
    assertEquals('m', prim1.getSex());
  }

  /**
   * Test if exception for sex is thrown correctly or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSex() {
    counter += 1;
    prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'A', 10, 2);
  }

  /**
   * Test if monkey weight is inserted properly or not.
   */
  @Test
  public void testGetHeight() {
    assertEquals(10, prim1.getWeight());
  }

  /**
   * Test if exception for height is thrown correctly or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetHeight() {
    counter += 1;
    prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 0, 2);
  }

  /**
   * Test if monkey age is inserted properly or not.
   */
  @Test
  public void testGetAge() {
    assertEquals(2, prim1.getAge());
  }

  /**
   * Test if exception for age is thrown correctly or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetAge() {
    counter += 1;
    prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, -1);
  }

  /**
   * Test if monkey favorite food is updating properly or not.
   */
  @Test
  public void testSetFavFood() {
    prim1.setFavFood("curry");
    assertEquals("curry", prim1.getFavFood());
  }

  /**
   * Test if monkey food quantity is updating properly or not.
   */
  @Test
  public void testSetFoodQty() {
    prim1.setFoodQty(100);
    assertEquals(100, prim1.getFoodQty());
  }

  /**
   * Test if monkey size is updating properly or not.
   */
  @Test
  public void testSetMonkeySize() {
    prim1.setMonkeySize(nmwSize.LARGE);
    assertEquals(nmwSize.LARGE, prim1.getMonkeySize());
  }

  /**
   * Test if monkey space requirement is updating properly or not.
   */
  @Test
  public void testSetSpaceReq() {
    prim1.setSpaceReq(nwmSpace.LARGE);
    assertEquals(nwmSpace.LARGE.getSpaceRequirement(), prim1.getSpaceReq());
  }

  /**
   * Test if monkey weight is updating properly or not.
   */
  @Test
  public void testSetWeight() {
    prim1.setWeight(15);
    assertEquals(15, prim1.getWeight());
  }

  /**
   * Test if monkey age is updating properly or not.
   */
  @Test
  public void testSetAge() {
    prim1.setAge(3);
    assertEquals(3, prim1.getAge());
  }
}
