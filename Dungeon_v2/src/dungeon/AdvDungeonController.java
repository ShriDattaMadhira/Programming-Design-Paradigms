package dungeon;

/**
 * Represents a Controller for Dungeon: handle user moves by executing them using the model;
 * convey move outcomes to the user.
 */
public interface AdvDungeonController {
  /**
   * Execute a single game of dungeon given a AdvancedDungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param d a non-null AdvancedDungeon Model
   */
  void playGame(Dungeon d);
}
