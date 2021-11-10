import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dungeon.Treasure;
import player.Player;
import player.PlayerImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test to check if the player is working properly or not.
 */
public class PlayerTest {
  private final Player player;

  public PlayerTest() {
    player = new PlayerImpl();
  }

  /**
   * Test if getter and setter for player's bag(whatever treasures picked up are stored in this)
   * is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBag() {
    List<Treasure> t = null;
    player.setBag(t);

    t = new ArrayList<>();
    assertEquals(0, player.getBag().size());
    assertEquals(t, player.getBag());

    t.add(Treasure.RUBIES);
    t.add(Treasure.DIAMONDS);
    player.setBag(t);
    assertEquals(t, player.getBag());

    t.add(Treasure.SAPPHIRE);
    t.add(Treasure.SAPPHIRE);
    player.setBag(new ArrayList<>() {
      {
        add(Treasure.SAPPHIRE);
        add(Treasure.SAPPHIRE);
      }
    });
    assertEquals(t, player.getBag());
  }

  /**
   * Test if getter and setter for player position is working as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPosition() {
    int[] pos = null;
    player.setPosition(pos);

    pos = new int[]{1, 2};
    player.setPosition(pos);
    assertArrayEquals(pos, player.getPosition());

    pos = new int[]{0,0};
    player.setPosition(pos);
    assertArrayEquals(pos, player.getPosition());

    pos = new int[]{-1, 0};
    player.setPosition(pos);
  }
}
