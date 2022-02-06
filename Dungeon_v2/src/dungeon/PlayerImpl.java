package dungeon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents an individual player.
 * It tells you the treasure player has, and the position he is in currently.
 * Empty bag represents no treasure and empty position list represents the game is not yet started.
 */
class PlayerImpl implements Player {
  private Map<Treasure, Integer> bag;
  private Cells position;
  private int arrowCount;
  private static final int INTITIAL_ARROWS = 3;

  /**
   * Constructor for Player class.
   */
  PlayerImpl() {
    this.bag = new HashMap<>();
    this.position = null;
    this.arrowCount = INTITIAL_ARROWS;
  }

  @Override
  public Map<Treasure, Integer> getBag() {
    return bag;
  }


  /**
   * Adds treasure picked up to the bag.
   * @param treasureList - treasure list.
   */
  void setBag(List<Treasure> treasureList) {
    if (treasureList == null) {
      throw new IllegalArgumentException("Treasure cannot be null.");
    }
    for (Treasure treasure: treasureList) {
      if (bag.containsKey(treasure)) {
        bag.put(treasure, bag.get(treasure) + 1);
      } else {
        bag.put(treasure, 1);
      }
    }
  }

  @Override
  public Cells getPosition() {
    return position;
  }


  /**
   * Updates the position of the player inside the dungeon.
   * @param position - position
   */
  void setPosition(Cells position) {
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null.");
    }
    this.position = position;
  }

  @Override
  public int getArrowCount() {
    return this.arrowCount;
  }

  /**
   * Adds or subtracts from the number of arrows.
   * @param add - true/false
   * @param count - number of arrows
   */
  void setArrowCount(boolean add, int count) {
    if (add) {
      this.arrowCount += count;
    } else {
      if (count > this.arrowCount) {
        throw new IllegalArgumentException("Insufficient number of Arrows.");
      }
      this.arrowCount -= count;
    }
  }
}
