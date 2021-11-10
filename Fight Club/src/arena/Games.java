package arena;

/**
 * Games is the interface that Arena implements and sets up a thrilling match between two players.
 */
public interface Games {
  /**
   * Players start the battle and fight till one of the player dies or number moves go beyond 1000.
   * The assumption is that the players get tired after 1000 rounds of non-stop fighting.
   */
  void startBattle();
}
