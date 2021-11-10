package player;

import dungeon.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents an individual player.
 * It tells you the treasure player has, and the position he is in currently.
 * Empty bag represents no treasure and empty position list represents the game is not yet started.
 */
public class PlayerImpl implements Player {
  private List<Treasure> bag;
  private int[] position;

  /**
   * Constructor for Player class.
   */
  public PlayerImpl() {
    this.bag = new ArrayList<>();
    this.position = new int[2];
  }

  @Override
  public List<Treasure> getBag() {
    return bag;
  }

  @Override
  public void setBag(List<Treasure> t) {
    if (t == null) {
      throw new IllegalArgumentException("Treasure cannot be null.");
    }
    bag.addAll(t);
  }

  @Override
  public int[] getPosition() {
    return position;
  }

  @Override
  public void setPosition(int[] position) {
    if (position == null || position.length == 0) {
      throw new IllegalArgumentException("Position cannot be null.");
    }
    if (position[0] < 0 || position[1] < 0) {
      throw new IllegalArgumentException("Position Invalid.");
    }
    this.position = position;
  }
}
