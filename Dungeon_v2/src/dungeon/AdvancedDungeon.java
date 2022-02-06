package dungeon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Extension of the dungeon we created for project 3.
 * Has additional features like monsters, smell, arrows that makes the player's job of surviving &
 * coming out of the dungeon alive that much harder.
 */
public class AdvancedDungeon extends DungeonImpl {
  private boolean eaten;
  private final Random rand;

  /**
   * Constructs a dungeon for the player to play the game.
   *
   * @param width             - width of the dungeon.
   * @param height            - height of the dungeon.
   * @param wrap              - wrap the edge cases.
   * @param interconnectivity - interconnectivity in the dungeon.
   * @param treasurePcnt      - percentage of cells with treasure.
   * @param noOfOtyughs - number of Otyughs
   */
  public AdvancedDungeon(int width, int height, boolean wrap, int interconnectivity,
                         int treasurePcnt, int noOfOtyughs) {
    super(width, height, wrap, interconnectivity, treasurePcnt);
    if (noOfOtyughs < 1) {
      throw new IllegalArgumentException("There must be at least one Otyugh in your dungeon.");
    }
    if (noOfOtyughs > (width * height) / 2) {
      throw new IllegalArgumentException("Too many Otyughs");
    }
    this.eaten = false;
    rand = new Random(0);
    // put arrows in the dungeon.
    putArrows(width, height, treasurePcnt);
    // place Otyughs in the dungeon.
    putOtyugh(noOfOtyughs);
  }

  private void putArrows(int width, int height, int treasurePercentage) {
    // Random rand = new Random(0);
    int posNum = (int) Math.ceil((width * height * treasurePercentage) / 100.0);
    for (int i = 0; i < posNum; i++) {
      int x = rand.nextInt(height);
      int y = rand.nextInt(width);
      dungeon[x][y].setArrow(true, 5);
    }
  }

  private void putOtyugh(int noOfOtyughs) {
    end.setOtyugh(new Otyugh(end.getCoordinates()));
    // Random rand = new Random(0);
    List<Cell> caves = getAllCaves();
    int i = 0;
    while (i < noOfOtyughs - 1) {
      Cell cave = caves.get(rand.nextInt(caves.size()));
      if (!cave.equals(end)) {
        cave.setOtyugh(new Otyugh(cave.getCoordinates()));
        i += 1;
      }
    }
  }

  @Override
  public void move(Direction direction) {
    if (direction != Direction.NORTH && direction != Direction.SOUTH && direction != Direction.WEST
            && direction != Direction.EAST) {
      throw new IllegalArgumentException("Invalid Direction specified.");
    }
    if (calcNextLocation(direction)) {
      if (this.eaten || player.getPosition().equals(end)) {
        setGameOver(true);
      }
    }
  }

  // calculates the next location, moves the player and sets the player's treasure bag.
  private boolean calcNextLocation(Direction direction) {
    // current position of the player.
    Cell curr = dungeon[player.getPosition().getCoordinates()[0]]
            [player.getPosition().getCoordinates()[1]];

    if (curr.getNeighborList().containsKey(direction)) {
      Cell next = curr.getNeighborList().get(direction);
      player.setPosition(next);
      if (next.getOtyugh() != null) {
        if (player.getPosition().getOtyugh().getHealth() == 100) {
          this.eaten = true;
        } else if (player.getPosition().getOtyugh().getHealth() == 50) {
          this.eaten = rand.nextDouble() > 0.5;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void pick(boolean pickTreasure, boolean pickArrows) {
    Cell curr = dungeon[player.getPosition().getCoordinates()[0]]
            [player.getPosition().getCoordinates()[1]];
    super.pick(pickTreasure, false);
    if (pickArrows) {
      player.setArrowCount(true, curr.getArrows());
      curr.setArrow(false, curr.getArrows());
    }
  }

  @Override
  public void shoot(Direction direction, int distance) {
    if (direction != Direction.NORTH && direction != Direction.SOUTH && direction != Direction.WEST
            && direction != Direction.EAST) {
      throw new IllegalArgumentException("Invalid Direction specified.");
    }
    if (distance <= 0) {
      throw new IllegalArgumentException("Can't shoot. Distance too low.");
    }
    if (player.getArrowCount() == 0) {
      throw new IllegalArgumentException("Can't shoot. No Arrows.");
    }
    calcArrowDestination(direction, distance);
  }

  private void calcArrowDestination(Direction direc, int dist) {
    Cells currCell = player.getPosition();
    boolean flag = false;

    Map<Direction, Direction> opposite = new HashMap<>();
    opposite.put(Direction.NORTH, Direction.SOUTH);
    opposite.put(Direction.SOUTH, Direction.NORTH);
    opposite.put(Direction.EAST, Direction.WEST);
    opposite.put(Direction.WEST, Direction.EAST);

    int i = 0;
    while (i < dist) {
      // if its a tunnel,
      if (currCell.getIsTunnel()) {
        // get its neighbor, go there, flag = true.
        Map<Direction, Cell> neighbors = currCell.getNeighborList();
        if (neighbors.containsKey(direc)) {
          // Tunnel contains the direction in which the arrow is travelling.
          currCell = currCell.getNeighborList().get(direc);
        } else {
          // If it doesn't, we will choose the neighbor that is not opposite of current direction.
          for (Direction d : neighbors.keySet()) {
            if (d != opposite.get(direc)) {
              currCell = currCell.getNeighborList().get(d);
              direc = d;
              break;
            }
          }
        }
        if (currCell.getIsCave()) {
          i++;
        }
        flag = true;
      } else if (currCell.getIsCave()) {
        if (currCell.getNeighborList().containsKey(direc)) {
          currCell = currCell.getNeighborList().get(direc);
          if (currCell.getIsCave()) {
            i ++;
          }
          flag = true;
        } else {
          flag = false;
        }
      }
      // if flag = false,
      if (!flag) {
        // break out of the loop (hits the wall).
        break;
      }
    }
    // If the distance mentioned is exact, then hit Otyugh.
    if (i == dist) {
      if (currCell.getOtyugh() != null) {
        currCell.getOtyugh().setHealth();
      }
    }
    // subtract 1 arrow from player as he will shoot it now.
    player.setArrowCount(false, 1);
  }

  @Override
  public StringBuilder getGameDetails() {
    Cell curr = dungeon[player.getPosition().getCoordinates()[0]]
            [player.getPosition().getCoordinates()[1]];
    // is this a cave or tunnel.
    String location = "Current location: " + Arrays.toString(player.getPosition().getCoordinates());
    if (curr.getIsTunnel()) {
      location += ". This is a Tunnel.\n";
    } else {
      location += ". This is a Cave.\n";
    }
    // is there any smell coming.
    String smell = "";
    if (curr.getOtyugh() != null) {
      if (curr.getOtyugh().getHealth() == 50) {
        smell += "Otyugh here is injured. We have a chance to escape. ";
      } else if (curr.getOtyugh().getHealth() == 0) {
        smell += "Otyugh in this cave is dead. ";
      }
    }
    smell += checkOtyugh(curr) + "\n";

    // is there treasure here.
    String treasure = "";
    if (curr.getTreasureList().size() > 0) {
      treasure += "There is treasure here.";
    } else {
      treasure += "There is no treasure here.";
    }
    // are there arrows here.
    String arrows = "";
    if (curr.getArrows() > 0) {
      arrows += " There are " + curr.getArrows() + " arrows here.\n";
    } else {
      arrows += " There are no arrows here.\n";
    }
    // where can we go from here.
    StringBuilder move = new StringBuilder("You can go ");
    Set<Direction> d = curr.getNeighborList().keySet();
    for (Direction direction: d) {
      move.append(direction.name()).append(", ");
    }
    move.delete(move.length() - 2, move.length());
    move.append(" from here.\n");

    StringBuilder sb = new StringBuilder();
    sb.append(location).append(treasure).append(arrows).append("Current bag: ")
            .append(player.getBag()).append("\nCurrent Arrow Count: ")
            .append(player.getArrowCount()).append("\n").append(smell).append(move).append("\n");
    return sb;
  }

  private StringBuilder checkOtyugh(Cell curr) {
    int otyughCount = 0;
    StringBuilder temp = new StringBuilder();
    for (Direction direction1: curr.getNeighborList().keySet()) {
      // checking +1 from current player's position
      if (curr.getNeighborList().get(direction1).getOtyugh() != null) {
        if (curr.getNeighborList().get(direction1).getOtyugh().getHealth() == 100) {
          temp.append("More pungent Smell. Otyugh in very close proximity.");
          return temp;
        } else if (curr.getNeighborList().get(direction1).getOtyugh().getHealth() == 50) {
          temp.append("More pungent Smell. Otyugh in very close proximity. Otyugh injured.");
          return temp;
        } else {
          temp.append("Otyugh in very close proximity is dead.");
        }
      } else {
        if (curr.getNeighborList().get(direction1).getNeighborList().containsKey(direction1)) {
          if (curr.getNeighborList().get(direction1).getNeighborList().get(direction1).getOtyugh()
                  != null) {
            if (curr.getNeighborList().get(direction1).getNeighborList().get(direction1).getOtyugh()
                    .getHealth() > 0) {
              otyughCount += 1;
            }
          }
        }
      }
    }
    if (otyughCount > 1) {
      temp.append("More pungent smell. Multiple Otyughs in close proximity.");
    } else if (otyughCount == 1) {
      temp.append("Less pungent smell. Otyugh in close proximity.");
    } else {
      temp.append("No smell. No Otyugh nearby.");
    }
    return temp;
  }

  @Override
  public boolean getEaten() {
    return this.eaten;
  }
  // end of class.
}
