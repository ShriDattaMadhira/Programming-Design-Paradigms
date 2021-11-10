package dungeon;

/**
 * A network of tunnels and caves that are interconnected so that player can explore the entire
 * world by traveling from cave to cave through the tunnels that connect them.
 */
public interface Dungeon {
  /**
   * The central method for playing the game.
   * @param direction - direction the player want to move. (N/S/E/W)
   * @param pickTreasure - If the player wants to pick treasure or not. (yes/no)
   */
  void play(Direction direction, String pickTreasure);

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
   * Returns the copy of our dungeon.
   * @return - copy of the dungeon.
   */
  Cells[][] dungeonDump();

  /**
   * Returns the coordinates of the player during the game.
   * @return - [x,y] coordinates
   */
  int[] getCurrPlayerPosition();
}
