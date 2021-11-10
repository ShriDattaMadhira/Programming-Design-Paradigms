import org.junit.Test;

import armory.WeaponFactory;
import armory.Weapons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test to check if the creation and usage of weapons is as expected.
 */
public class WeaponsTest {
  private final Weapons katana;
  private final Weapons broadSword;
  private final Weapons twoHandedSword;
  private final Weapons axe;
  private final Weapons flail;
  private final Weapons defaultWeapon;
  private final static WeaponFactory weaponFactory = new WeaponFactory();

  /**
   * Constructor to set up testing for weapons.
   */
  public WeaponsTest() {
    katana = weaponFactory.createKatana();
    broadSword = weaponFactory.createBroadSword();
    twoHandedSword = weaponFactory.createTwoHandedSword();
    axe = weaponFactory.createAxe();
    flail = weaponFactory.createFlail();
    defaultWeapon = weaponFactory.createDefaultWeapon();
  }

  /**
   * Test to check if the weapons' minimum capability is as expected.
   */
  @Test
  public void testGetMinAbility() {
    assertEquals(8, katana.getMinAbility());
    assertEquals(6, broadSword.getMinAbility());
    assertEquals(8, twoHandedSword.getMinAbility());
    assertEquals(6, axe.getMinAbility());
    assertEquals(8, flail.getMinAbility());
    assertEquals(0, defaultWeapon.getMinAbility());

    assertNotEquals(6, katana.getMinAbility());
    assertNotEquals(8, broadSword.getMinAbility());
    assertNotEquals(6, twoHandedSword.getMinAbility());
    assertNotEquals(4, axe.getMinAbility());
    assertNotEquals(1, flail.getMinAbility());
    assertNotEquals(100, defaultWeapon.getMinAbility());
  }

  /**
   * Test to check if the weapons' maximum capability is as expected.
   */
  @Test
  public void testGetMaxAbility() {
    assertEquals(12, katana.getMaxAbility());
    assertEquals(10, broadSword.getMaxAbility());
    assertEquals(12, twoHandedSword.getMaxAbility());
    assertEquals(10, axe.getMaxAbility());
    assertEquals(12, flail.getMaxAbility());
    assertEquals(0, defaultWeapon.getMaxAbility());

    assertNotEquals(10, katana.getMaxAbility());
    assertNotEquals(12, broadSword.getMaxAbility());
    assertNotEquals(10, twoHandedSword.getMaxAbility());
    assertNotEquals(40, axe.getMaxAbility());
    assertNotEquals(999, flail.getMaxAbility());
    assertNotEquals(533, defaultWeapon.getMaxAbility());
  }

  /**
   * Test to check if the names of the weapons are as expected.
   */
  @Test
  public void testGetName() {
    assertEquals("Katana", katana.getName());
    assertEquals("Broad Sword", broadSword.getName());
    assertEquals("Two Handed Sword", twoHandedSword.getName());
    assertEquals("Axe", axe.getName());
    assertEquals("Flail", flail.getName());
    assertEquals("Hands", defaultWeapon.getName());

    assertNotEquals("katana", katana.getName());
    assertNotEquals("broadsword", broadSword.getName());
    assertNotEquals("twoHandedsword", twoHandedSword.getName());
    assertNotEquals("axe", axe.getName());
    assertNotEquals("flail", flail.getName());
    assertNotEquals("machinegun", defaultWeapon.getName());
  }

  /**
   * Test to check if the comparison with flail is working as expected.
   */
  @Test
  public void testIsFlail() {
    assertTrue(flail.isFlail());
    assertNotEquals(true, katana.isFlail());
    assertFalse(twoHandedSword.isFlail());
    assertFalse(broadSword.isFlail());
    assertFalse(axe.isFlail());
    assertFalse(defaultWeapon.isFlail());
  }

  /**
   * Test to check if the comparison with two handed sword is working as expected.
   */
  @Test
  public void testIsTwoHandedSword() {
    assertTrue(twoHandedSword.isTwoHandedSword());
    assertNotEquals(true, broadSword.isTwoHandedSword());
    assertFalse(axe.isTwoHandedSword());
    assertFalse(flail.isTwoHandedSword());
    assertFalse(katana.isTwoHandedSword());
    assertFalse(defaultWeapon.isTwoHandedSword());
  }

  /**
   * Test to check if the comparison with no weapon (hands) is working as expected.
   */
  @Test
  public void testIsNoWeapon() {
    assertTrue(defaultWeapon.isNoWeapon());
    assertNotEquals(true, katana.isNoWeapon());
    assertFalse(axe.isNoWeapon());
    assertFalse(flail.isNoWeapon());
    assertFalse(katana.isNoWeapon());
    assertFalse(twoHandedSword.isNoWeapon());
  }

  /**
   * Test for checking if the abilities are being modified when called setAbilities().
   * Used to modify flail & two handed sword capabilities if player strength or dexterity is <= 14.
   */
  @Test
  public void testSetAbilities() {
    // only two handed sword and flail values should change.
    katana.setAbilities();
    assertEquals(8, katana.getMinAbility());
    assertEquals(12, katana.getMaxAbility());
    assertNotEquals(6, katana.getMinAbility());
    assertNotEquals(10, katana.getMaxAbility());

    broadSword.setAbilities();
    assertEquals(6, broadSword.getMinAbility());
    assertEquals(10, broadSword.getMaxAbility());
    assertNotEquals(4, broadSword.getMinAbility());
    assertNotEquals(6, broadSword.getMaxAbility());

    twoHandedSword.setAbilities();
    assertEquals(4, twoHandedSword.getMinAbility());
    assertEquals(6, twoHandedSword.getMaxAbility());
    assertNotEquals(8, twoHandedSword.getMinAbility());
    assertNotEquals(12, twoHandedSword.getMaxAbility());

    axe.setAbilities();
    assertEquals(6, axe.getMinAbility());
    assertEquals(10, axe.getMaxAbility());
    assertNotEquals(4, axe.getMinAbility());
    assertNotEquals(6, axe.getMaxAbility());

    flail.setAbilities();
    assertEquals(4, flail.getMinAbility());
    assertEquals(6, flail.getMaxAbility());
    assertNotEquals(6, flail.getMinAbility());
    assertNotEquals(12, flail.getMaxAbility());

    defaultWeapon.setAbilities();
    assertEquals(0, defaultWeapon.getMinAbility());
    assertEquals(0, defaultWeapon.getMaxAbility());
    assertNotEquals(4, defaultWeapon.getMinAbility());
    assertNotEquals(12, defaultWeapon.getMaxAbility());
  }
}
