import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import primates.Primates;
import primates.nmwSize;
import primates.NWMonkeys;
import primates.nwmSpace;
import sanctuary.ISanctuary;
import sanctuary.Sanctuary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests if the Sanctuary is functioning as expected or not.
 */
public class ISanctuaryTest {
  private ISanctuary is;
  private static int counter = 0;

  /**
   * Setting up for the tests.
   */
  @Before
  public void setUp() {
    is = new Sanctuary("Jungle Friends Primate Sanctuary", 3,
            3);
  }

  /**
   * Test if exception total enclosures is thrown or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTotalEnclosures() {
    is = new Sanctuary("Jungle Friends Primate Sanctuary", -1, 3);
  }

  /**
   * Test if exception for total cages is thrown not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTotalCages() {
    is = new Sanctuary("Jungle Friends Primate Sanctuary", 3, 0);
  }

  /**
   * Test if the total sanctuary name is inserted properly or not.
   */
  @Test
  public void testGetSanctuaryName() {
    setUp();
    assertEquals("Jungle Friends Primate Sanctuary", is.getSanctuaryName());
  }

  /**
   * Test if the total enclosures is inserted properly or not.
   */
  @Test
  public void testGetTotalEnclosures() {
    setUp();
    assertEquals(3, is.getTotalEnclosures());
  }

  /**
   * Test if the total cages is inserted properly or not.
   */
  @Test
  public void testGetTotalCages() {
    setUp();
    assertEquals(3, is.getTotalCages());
  }

  /**
   * Test to check if food details for enclosure are updated properly or not.
   */
  @Test
  public void testGetFoodDetails() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    boolean t = is.newMonkey(prim1);
    HashMap<String, Integer> fD = new HashMap<>();
    fD.put("nuts", prim1.getFoodQty());
    assertEquals(fD, is.getFoodDetails());
  }

  /**
   * Test if we are able to increase the total number of enclosures.
   */
  @Test
  public void testIncTotalEnclosures() {
    is.incTotalEnclosures(1);
    assertEquals(4, is.getTotalEnclosures());
    is.incTotalEnclosures(2);
    assertEquals(6, is.getTotalEnclosures());
  }

  /**
   * Test if we are able to increase the total number of cages.
   */
  @Test
  public void testIncTotalCages() {
    is.incTotalCages(1);
    assertEquals(4, is.getTotalCages());
    is.incTotalCages(4);
    assertEquals(8, is.getTotalCages());
  }

  /**
   * Test id we are able to increase the size of enclosure or not.
   */
  @Test
  public void testIncEnclosureSize() {
    assertTrue(is.incEnclosureSize(1, 100));
    assertTrue(is.incEnclosureSize(2, 50));
    assertFalse(is.incEnclosureSize(0, 10));
  }

  /**
   * Test to see if monkeys are getting added properly or not.
   */
  @Test(expected = IllegalStateException.class)
  public void testNewMonkey() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    assertTrue(is.newMonkey(prim1));

    Primates prim2 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    assertTrue(is.newMonkey(prim2));

    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    assertTrue(is.newMonkey(prim3));

    Primates prim4 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    assertTrue(is.newMonkey(prim4));
  }

  /**
   * Test if species location search results are correct or not.
   */
  @Test
  public void testSearchSpecies() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "Driller", "leaves",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    try {
      is.moveToEnclosure(prim1.getMonkeyName());
      is.moveToEnclosure(prim2.getMonkeyName());
      is.moveToEnclosure(prim3.getMonkeyName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    ArrayList<String> sS = new ArrayList<>();
    sS.add("Enclosure-0");
    assertEquals(sS, is.searchSpecies("driller"));
  }

  /**
   * Test if the all available species are returned with their correct locations or not.
   */
  @Test
  public void testGetAllSpecies() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Spider", "fruits",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    try {
      is.moveToEnclosure(prim1.getMonkeyName());
      is.moveToEnclosure(prim2.getMonkeyName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    TreeMap<String, ArrayList<String>> aS = new TreeMap<>();
    ArrayList<String> loc = new ArrayList<>();
    loc.add("Enclosure-0");
    aS.put("driller", loc);
    loc = new ArrayList<>();
    loc.add("Enclosure-1");
    aS.put("mangabey", loc);
    loc = new ArrayList<>();
    aS.put("spider", loc);
    loc.add("Isolation-2");

    assertEquals(aS, is.getAllSpecies());
  }

  /**
   * Test if the shopping list for the sanctuary is generated is correctly or not.
   */
  @Test
  public void testGetShoppingList() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
            150, nmwSize.MEDIUM, nwmSpace.MEDIUM, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            100, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);
    HashMap<String, Integer> fD = new HashMap<>();
    fD.put("nuts", 90);
    fD.put("leaves", 150);
    fD.put("fruits", 100);
    assertEquals(fD, is.getShoppingList());
  }

  /**
   * Test if the signs are produced correctly or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProduceSign() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
            150, nmwSize.MEDIUM, nwmSpace.MEDIUM, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            100, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    try {
      is.moveToEnclosure(prim1.getMonkeyName());
      is.moveToEnclosure(prim2.getMonkeyName());
      is.moveToEnclosure(prim3.getMonkeyName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    ArrayList<ArrayList<String>> sign = new ArrayList<>();
    ArrayList<String> s = new ArrayList<>();
    s.add("m-00");
    s.add(Character.toString('m'));
    s.add("nuts");
    sign.add(s);
    s = new ArrayList<>();
    s.add("m-03");
    s.add(Character.toString('f'));
    s.add("fruits");
    assertEquals(sign, is.produceSign(0));

    sign = new ArrayList<>();
    s = new ArrayList<>();
    s.add("m-01");
    s.add(Character.toString('f'));
    s.add("leaves");
    sign.add(s);
    assertEquals(sign, is.produceSign(1));

    is.produceSign(5);
  }

  /**
   * Test to see if all available monkeys location information is returned correctly or not.
   */
  @Test
  public void testGetAllMonkeys() {
    try {
      TreeMap<String, String> am = new TreeMap<>();

      Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
              90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
      is.newMonkey(prim1);
      is.moveToEnclosure(prim1.getMonkeyName());
      am.put("m-0" + counter, "Enclosure-0");
      counter += 1;

      Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
              90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 7, 4);
      is.newMonkey(prim2);
      am.put("m-0" + counter, "Isolation-0");
      counter += 1;

      Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
              90, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
      is.newMonkey(prim3);
      is.moveToEnclosure(prim3.getMonkeyName());
      am.put("m-0" + counter, "Enclosure-0");
      counter += 1;

      assertEquals(am, is.getAllMonkeys());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test to see if monkeys are properly inserted into enclosures or not.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveToEnclosure() {
    is = new Sanctuary("JFPS", 1, 3);

    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.LARGE, nwmSpace.LARGE, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "Driller", "leaves",
            150, nmwSize.LARGE, nwmSpace.LARGE, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            100, nmwSize.LARGE, nwmSpace.LARGE, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    is.moveToEnclosure(prim1.getMonkeyName());
    is.moveToEnclosure(prim2.getMonkeyName());
    // should throw exception because of no space in enclosure.
    is.moveToEnclosure(prim3.getMonkeyName());
  }

  /**
   * Test if monkeys are properly moved to isolatoin or not.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveToIsolation() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
            150, nmwSize.MEDIUM, nwmSpace.MEDIUM, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            100, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    // throws exception as monkey already in isolation.
    is.moveToIsolation(prim1.getMonkeyName());
    is.moveToEnclosure(prim2.getMonkeyName());
    is.moveToIsolation(prim2.getMonkeyName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSearchFood() {
    Primates prim1 = new NWMonkeys("m-0" + counter, "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    counter += 1;
    is.newMonkey(prim1);
    Primates prim2 = new NWMonkeys("m-0" + counter, "MangaBey", "leaves",
            150, nmwSize.MEDIUM, nwmSpace.MEDIUM, 'F', 7, 4);
    counter += 1;
    is.newMonkey(prim2);
    Primates prim3 = new NWMonkeys("m-0" + counter, "Driller", "fruits",
            100, nmwSize.SMALL, nwmSpace.SMALL, 'F', 9, 2);
    counter += 1;
    is.newMonkey(prim3);

    Integer quant = 150;
    assertEquals(quant, is.searchFood("leaves"));
    quant = 100;
    assertEquals(quant, is.searchFood("fruits"));
    quant = 95;
    assertNotEquals(quant, is.searchFood("nuts"));

    //should throw an exception because the food item is not there.
    is.searchFood("bananas");
  }

}
