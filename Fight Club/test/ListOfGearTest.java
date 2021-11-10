import org.junit.Test;

import java.util.Comparator;

import gear.Gear;
import gear.GearFactory;
import gear.ListOfGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests if list of gears are behaving as expected when created, sorted, & during other operations.
 */
public class ListOfGearTest {
  private static final GearFactory gearFactory = new GearFactory();
  private final Gear headGear;
  private final Gear potion;
  private final Gear footWear;
  private final ListOfGear log1;
  private final ListOfGear log2;

  /**
   * Default constructor that creates all the gears and gear lists necessary for testing.
   */
  public ListOfGearTest() {
    headGear = gearFactory.createHeadGear();
    potion = gearFactory.createPotions();
    Gear belt = gearFactory.createBelts(1);
    footWear = gearFactory.createFootWear();

    ListOfGear emptyNode = gearFactory.createGearEmptyNode();

    log1 = gearFactory.createGearElementNode(headGear, gearFactory.createGearElementNode(potion,
            gearFactory.createGearElementNode(belt, gearFactory.createGearElementNode(footWear,
                    emptyNode))));
    log2 = gearFactory.createGearElementNode(potion, gearFactory.createGearElementNode(footWear,
            gearFactory.createGearElementNode(headGear, gearFactory.createGearElementNode(belt,
                    emptyNode))));
  }

  /**
   * Testing if the gears are getting created properly or not.
   */
  @Test
  public void testGear() {
    assertEquals("Head Gear", headGear.getName());
    assertEquals("Potion", potion.getName());
    assertEquals(1, footWear.getEffects().size());
  }

  /**
   * Testing if the length of the list is being returned as expected.
   */
  @Test
  public void testLength() {
    assertEquals(4, log1.length());
    assertNotEquals(3, log1.length());
  }

  /**
   * Testing if the element retrieval is working properly as expected.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet() {
    int i = 3;
    assertEquals(footWear.getName(), log1.get(i).getName());

    log1.get(5);
  }

  /**
   * Testing if sorting and insertion(implicitly) are working as expected.
   */
  @Test
  public void testSort() {
    assertEquals(log1, log2.sort(Comparator.reverseOrder()));
    assertEquals(log1, log2.sort((g1, g2) -> g2.compareTo(g1)));
    assertNotEquals(log1, log2.sort(Comparator.naturalOrder()));
  }
}
