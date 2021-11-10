import org.junit.Test;

import arena.Games;
import arena.Arena;
import arena.LiveStream;
import player.Player;
import player.Players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test to check if the battle results are as expected.
 * Test to check if the player attributes are effected properly by their equipment.
 * Test to check if the liveStream is working as expected.
 */
public class GamesTest {
  private Games arena;
  private final Players player1;
  private final Players player2;
  private Players player1Copy;
  private Players player2Copy;
  private final LiveStream liveStream;

  /**
   * Initializes all the necessary things for testing.
   */
  public GamesTest() {
    player1 = new Player(1);
    player2 = new Player(2);
    liveStream = new LiveStream();
  }

  /**
   * Test to check winner is returned as expected.
   */
  @Test
  public void testStartBattle() {
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    arena.startBattle();
    assertEquals(1, liveStream.getWinner());
    assertNotEquals(2, liveStream.getWinner());

    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    arena.startBattle();
    assertEquals(1, liveStream.getWinner());
    assertNotEquals(2, liveStream.getWinner());

    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    arena.startBattle();
    assertEquals(2, liveStream.getWinner());
    assertNotEquals(1, liveStream.getWinner());
  }

  /**
   * Test to check if the players' attributes are effected as expected by their equipment.
   */
  @Test
  public void testToString() {
    String expected = "Player ID = 1\n" + "Strength = 16\n" + "Constitution = 13\n"
            + "Dexterity = 19\n" + "Charisma = 24\n" + "Health = 72\n"
            + "Weapon = Two Handed Sword\n"
            + "Gear = [ Head Gear, Potion, Potion, Potion, Potion, Potion, Potion, Potion, Potion, "
            + "Potion, Potion, Belt-Large, Belt-Small, Belt-Medium, Belt-Medium, Foot Wear]\n\n"
            + "Player ID = 2\n" + "Strength = 6\n" + "Constitution = 7\n" + "Dexterity = 6\n"
            + "Charisma = 20\n" + "Health = 39\n" + "Weapon = Broad Sword\n"
            + "Gear = [ Head Gear, Potion, Potion, Potion, Potion, Potion, Potion, Potion, Potion, "
            + "Potion, Potion, Potion, Belt-Medium, Belt-Large, Belt-Large]";
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    assertEquals(expected, arena.toString());

    expected = "Player ID = 1\n" + "Strength = 13\n" + "Constitution = 11\n" + "Dexterity = 11\n"
            + "Charisma = 15\n" + "Health = 50\n" + "Weapon = Broad Sword\n"
            + "Gear = [ Head Gear, Potion, Potion, Potion, Potion, Potion, Potion, Potion, Potion, "
            + "Belt-Large, Belt-Medium, Belt-Small, Belt-Medium, Foot Wear]\n" + "\n"
            + "Player ID = 2\n" + "Strength = 7\n" + "Constitution = 7\n" + "Dexterity = 9\n"
            + "Charisma = 19\n" + "Health = 42\n" + "Weapon = Axe\n"
            + "Gear = [ Head Gear, Potion, Potion, Potion, Potion, Potion, Potion, Potion,"
            + " Potion, Potion, Belt-Medium, Belt-Large, Belt-Large, Foot Wear]";
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    assertEquals(expected, arena.toString());
  }

  /**
   * Test for live streaming.
   */
  @Test
  public void testLiveStream() {
    String expected = "";
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    assertEquals(expected, liveStream.toString());

    expected = "turn = Player-2, successfulStrike = 0, damage = 0, opponent health = 50\n"
            + "turn = Player-1, successfulStrike = 1, damage = 14, opponent health = 28\n"
            + "turn = Player-2, successfulStrike = 0, damage = 0, opponent health = 50\n"
            + "turn = Player-1, successfulStrike = 1, damage = 14, opponent health = 14\n"
            + "turn = Player-2, successfulStrike = 1, damage = 3, opponent health = 47\n"
            + "turn = Player-1, successfulStrike = 1, damage = 15, opponent health = -1\n";
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    arena.startBattle();
    assertEquals(expected, liveStream.toString());

    expected = "turn = Player-2, successfulStrike = 1, damage = 9, opponent health = 29\n"
            + "turn = Player-1, successfulStrike = 1, damage = 10, opponent health = 39\n"
            + "turn = Player-2, successfulStrike = 1, damage = 11, opponent health = 18\n"
            + "turn = Player-1, successfulStrike = 1, damage = 12, opponent health = 27\n"
            + "turn = Player-2, successfulStrike = 1, damage = 11, opponent health = 7\n"
            + "turn = Player-1, successfulStrike = 0, damage = 0, opponent health = 27\n"
            + "turn = Player-2, successfulStrike = 1, damage = 9, opponent health = -2\n";
    player1Copy = new Player(player1);
    player2Copy = new Player(player2);
    arena = new Arena(player1Copy, player2Copy);
    arena.startBattle();
    assertEquals(expected, liveStream.toString());
  }
}
