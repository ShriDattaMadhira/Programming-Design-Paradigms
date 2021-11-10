import org.junit.Test;

import gear.GearFactory;
import player.Player;
import player.Players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test to check if the players are created with all the attributes as expected.
 */
public class PlayersTest {
  private final Players player1;
  private final Players player2;

  /**
   * Initializes the player objects.
   */
  public PlayersTest() {
    player1 = new Player(1);
    player2 = new Player(2);
  }

  /**
   * Test to check if ID of the players is set as expected.
   */
  @Test
  public void testGetPlayerID() {
    assertEquals(1, player1.getPlayerID());
    assertEquals(2, player2.getPlayerID());
    assertNotEquals(0, player1.getPlayerID());
    assertNotEquals(1, player2.getPlayerID());
  }

  /**
   * Test to check if Strength of the players is set as expected.
   */
  @Test
  public void testGetStrength() {
    assertEquals(17, player1.getStrength());
    assertEquals(13, player2.getStrength());
    assertNotEquals(7, player1.getStrength());
    assertNotEquals(18, player2.getStrength());
  }

  /**
   * Test to check if Constitution of the players is set as expected.
   */
  @Test
  public void testGetConstitution() {
    assertEquals(16, player1.getConstitution());
    assertEquals(14, player2.getConstitution());
    assertNotEquals(15, player1.getConstitution());
    assertNotEquals(8, player2.getConstitution());
  }

  /**
   * Test to check if Dexterity of the players is set as expected.
   */
  @Test
  public void testGetDexterity() {
    assertEquals(18, player1.getDexterity());
    assertEquals(15, player2.getDexterity());
    assertNotEquals(15, player1.getDexterity());
    assertNotEquals(8, player2.getDexterity());
  }

  /**
   * Test to check if Charisma of the players is set as expected.
   */
  @Test
  public void testGetCharisma() {
    assertEquals(16, player1.getCharisma());
    assertEquals(18, player2.getCharisma());
    assertNotEquals(15, player1.getCharisma());
    assertNotEquals(8, player2.getCharisma());
  }

  /**
   * Tests to see if player is being created with his default weapon (Hands).
   * It is illegal to enter the battlefield with pre-equipped weapon.
   */
  @Test
  public void testGetWeapon() {
    assertEquals("Hands", player1.getWeapon().getName());
    assertEquals("Hands", player2.getWeapon().getName());
    assertNotEquals("Katana", player1.getWeapon().getName());
    assertNotEquals("Axe", player1.getWeapon().getName());
    assertNotEquals("Flail", player2.getWeapon().getName());
    assertNotEquals("Broad Sword", player2.getWeapon().getName());
  }

  /**
   * Tests if the player is being created with any gear.
   * This is compulsory as it is illegal to enter the battlefield with gear pre-equipped.
   */
  @Test
  public void testGetActiveGear() {
    assertEquals(new GearFactory().createGearEmptyNode(), player1.getActiveGear());
    assertEquals(new GearFactory().createGearEmptyNode(), player2.getActiveGear());
  }
}
