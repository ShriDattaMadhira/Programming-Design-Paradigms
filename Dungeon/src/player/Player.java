package player;

import dungeon.Treasure;

import java.util.List;

/**
 * Player of the game. As it is a stand-alone game, we will only have one player playing the game.
 */
public interface Player {

  /**
   * Returns the bag of treasure the player has collected while playing the game.
   * @return - bag of treasure.
   */
  List<Treasure> getBag();

  /**
   * Adds the treasure that is picked up to the bag.
   * @param t - treasure
   */
  void setBag(List<Treasure> t);

  /**
   * Returns the coordinates of the position the player is currently in.
   * @return - [x,y] of the player.
   */
  int[] getPosition();

  /**
   * Updates the position to the current position of the player.
   * @param position - position array.
   */
  void setPosition(int[] position);
}
