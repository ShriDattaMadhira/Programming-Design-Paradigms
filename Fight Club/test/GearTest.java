import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import gear.Gear;
import gear.GearFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests if gears and their attributes are created as expected.
 */
public class GearTest {
  private Gear headGear;
  private Gear potion;
  private Gear belt;
  private Gear footWear;
  private static final GearFactory gearFactory = new GearFactory();

  /**
   * Creating the gear objects and setting up for testing.
   */
  @Before
  public void setUp() {
    headGear = gearFactory.createHeadGear();
    potion = gearFactory.createPotions();
    footWear = gearFactory.createFootWear();
  }

  /**
   * Test for checking if max no of same type equipment allowed at a time (limit) is as expected.
   */
  @Test
  public void testGetLimit() {
    assertEquals(1, headGear.getLimit());
    assertNotEquals(3, headGear.getLimit());

    assertEquals(1, footWear.getLimit());
    assertNotEquals(2, footWear.getLimit());

    assertEquals(-1, potion.getLimit());
    assertNotEquals(4, potion.getLimit());

    belt = gearFactory.createBelts(0);
    assertEquals(1, belt.getLimit());
    assertNotEquals(4, belt.getLimit());

    belt = gearFactory.createBelts(1);
    assertEquals(2, belt.getLimit());
    assertNotEquals(1, belt.getLimit());

    belt = gearFactory.createBelts(2);
    assertEquals(4, belt.getLimit());
    assertNotEquals(5, belt.getLimit());
  }

  /**
   * Test for checking if the names of the gears are as expected.
   */
  @Test
  public void testGetName() {
    assertEquals("Head Gear", headGear.getName());
    assertNotEquals("HeadGear", headGear.getName());
    assertNotEquals("head gear", headGear.getName());

    assertEquals("Foot Wear", footWear.getName());
    assertNotEquals("FootWear", footWear.getName());
    assertNotEquals("footwear", footWear.getName());

    assertEquals("Potion", potion.getName());
    assertNotEquals("potions", potion.getName());
    assertNotEquals("potion", potion.getName());

    belt = gearFactory.createBelts(0);
    assertEquals("Belt-Small", belt.getName());
    assertNotEquals("small belt", belt.getName());
    assertNotEquals("belt-small", belt.getName());

    belt = gearFactory.createBelts(1);
    assertEquals("Belt-Medium", belt.getName());
    assertNotEquals("medium belt", belt.getName());
    assertNotEquals("belt-medium", belt.getName());

    belt = gearFactory.createBelts(2);
    assertEquals("Belt-Large", belt.getName());
    assertNotEquals("large belt", belt.getName());
    assertNotEquals("belt-large", belt.getName());
  }

  /**
   * Test for checking if the copy constructor is copying the object as expected.
   */
  @Test
  public void testGetCopy() {
    Gear hGCopy = headGear.getCopy();
    assertEquals(hGCopy.getName(), headGear.getName());
    assertEquals(hGCopy.getLimit(), headGear.getLimit());
    assertEquals(hGCopy.getEffects(), headGear.getEffects());
    assertNotEquals("Name", hGCopy.getName());
    assertNotEquals(5, hGCopy.getLimit());

    Gear potionCopy = potion.getCopy();
    assertEquals(potionCopy.getName(), potion.getName());
    assertEquals(potionCopy.getLimit(), potion.getLimit());
    assertEquals(potionCopy.getEffects(), potion.getEffects());
    assertNotEquals("Name", potionCopy.getName());
    assertNotEquals(5, potionCopy.getLimit());

    belt = gearFactory.createBelts(0);
    Gear beltCopy = belt.getCopy();
    assertEquals(beltCopy.getName(), belt.getName());
    assertEquals(beltCopy.getLimit(), belt.getLimit());
    assertEquals(beltCopy.getEffects(), belt.getEffects());
    assertNotEquals("Name", beltCopy.getName());
    assertNotEquals(5, beltCopy.getLimit());

    Gear footWearCopy = footWear.getCopy();
    assertEquals(footWearCopy.getName(), footWear.getName());
    assertEquals(footWearCopy.getLimit(), footWear.getLimit());
    assertEquals(footWearCopy.getEffects(), footWear.getEffects());
    assertNotEquals("Name", footWearCopy.getName());
    assertNotEquals(5, footWearCopy.getLimit());
  }

  /**
   * Test to check if the random generator is generating the values the same way for every run.
   */
  @Test
  public void testGetEffects() {
    ArrayList<Integer> effects = new ArrayList<>();
    effects.add(1);
    assertEquals(effects, headGear.getEffects());
    assertEquals(1, headGear.getEffects().size());
    effects.add(2);
    assertNotEquals(effects, headGear.getEffects());
    assertNotEquals(2, headGear.getEffects().size());

    effects = new ArrayList<>();
    effects.add(2);
    assertEquals(effects, footWear.getEffects());
    assertEquals(1, footWear.getEffects().size());
    effects.add(0);
    assertNotEquals(effects, footWear.getEffects());
    assertNotEquals(2, footWear.getEffects().size());

    belt = gearFactory.createBelts(2);
    effects = new ArrayList<>();
    effects.add(1);
    effects.add(3);
    assertEquals(effects, belt.getEffects());
    assertEquals(2, belt.getEffects().size());
    effects.add(0);
    assertNotEquals(effects, belt.getEffects());
    assertNotEquals(4, belt.getEffects().size());

    effects = new ArrayList<>();
    effects.add(0);
    effects.add(3);
    assertEquals(effects, potion.getEffects());
    assertEquals(2, potion.getEffects().size());
    effects.add(0);
    assertNotEquals(effects, potion.getEffects());
    assertNotEquals(4, potion.getEffects().size());
  }
}
