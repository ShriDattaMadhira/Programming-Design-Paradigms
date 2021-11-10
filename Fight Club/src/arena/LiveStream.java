package arena;

/**
 * Streams the battle live to the spectators.
 */
public class LiveStream {

  private int turn;
  private int damage;
  private int health;
  private int successfulStrike;
  private static int winner;
  private static StringBuilder sb;

  /**
   * Setting up the cameras for live streaming.
   */
  public LiveStream() {
    sb = new StringBuilder();
  }

  LiveStream(int turn, int successfulStrike, int damage, int health) {
    this.turn = turn;
    this.damage = damage;
    this.health = health;
    this.successfulStrike = successfulStrike;
    live();
  }

  LiveStream(int winner) {
    LiveStream.winner = winner;
  }

  private void live() {
    sb.append("turn = Player-").append(turn).append(", successfulStrike = ")
            .append(successfulStrike).append(", damage = ").append(damage)
            .append(", opponent health = ").append(health).append("\n");
  }

  @Override
  public String toString() {
    String s = sb.toString();
    sb = new StringBuilder("");
    return s;
  }

  /**
   * Returns the winner of the exciting battle.
   * @return - winner.
   */
  public int getWinner() {
    int temp = winner;
    winner = 0;
    return temp;
  }
}
