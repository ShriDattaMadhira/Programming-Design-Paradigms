package dungeon;

/**
 * A network of tunnels and caves that are interconnected so that player can explore the entire
 * world by traveling from cave to cave through the tunnels that connect them.
 */
public interface Dungeon {
  /**
   * Moves the player from one position to another.
   * @param direction - direction the player want to move. (N/S/E/W)
   */
  void move(Direction direction);

  /**
   * Shoots the arrow to kill the monsters by distance specified.
   * @param direction - direction in which the player wants to shoot the arrow. (N/S/E/W)
   * @param distance - distance till which he wants to shoot the arrow.
   */
  void shoot(Direction direction, int distance);

  /**
   * Every cell has either one or both of treasure and arrows available.
   * Player can pick these up for future use and collections.
   * @param pickTreasure - true/false for picking treasure.
   * @param pickArrows - true/false for picking arrows.
   */
  void pick(boolean pickTreasure, boolean pickArrows);

  /**
   * Tells us if the game has reached its end or not.
   * @return - game state.
   */
  boolean isGameOver();

  /**
   * Return the details of the game which includes details of the location and the player.
   * @return - Returns the player details.
   */
  StringBuilder getGameDetails();

  /**
   * Return the coordinates of the starting cell.
   * @return - coordinates of the starting cell.
   */
  int[] getStartLocation();

  /**
   * Return the coordinates of the ending cell.
   * @return - coordinates of the ending cell.
   */
  int[] getEndLocation();

  /**
   * Returns the coordinates of the player during the game.
   * @return - [x,y] coordinates
   */
  int[] getCurrPlayerPosition();

  /**
   * Returns if the player is eaten by an Otyugh or not.
   * @return - true/false
   */
  boolean getEaten();

  /**
   * Returns a copy of the dungeon for testing purposes.
   * @return - copy of dungeon
   */
  Cells[][] dungeonDump();
}
