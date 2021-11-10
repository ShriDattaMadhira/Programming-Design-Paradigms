package dungeon;

import player.Player;
import player.PlayerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A network of tunnels and caves that are interconnected so that player can explore the entire
 * world by traveling from cave to cave through the tunnels that connect them.
 */
public class DungeonImpl implements Dungeon {

  private final Player player;
  private Cell start;
  private Cell end;
  private Cell[][] dungeon;
  private boolean gameOver;

  /**
   * Constructs a dungeon for the player to play the game.
   * @param width - width of the dungeon.
   * @param height - height of the dungeon.
   * @param wrap - wrap the edge cases.
   * @param interconnectivity - interconnectivity in the dungeon.
   * @param treasurePcnt - percentage of cells with treasure.
   */
  public DungeonImpl(int width, int height, boolean wrap, int interconnectivity, int treasurePcnt) {
    if (width <= 0) {
      throw new IllegalArgumentException("Width cannot be < or = 0.");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("Height cannot be < or = 0.");
    }
    if (width <= 4 && height <= 4) {
      throw new IllegalArgumentException("Dungeon is too small.");
    }
    if (interconnectivity < 0 || interconnectivity >= width * height) {
      throw new IllegalArgumentException("Invalid Interconnectivity.");
    }
    if (treasurePcnt < 0) {
      throw new IllegalArgumentException("Treasure percentage cannot be < 0.");
    }
    this.gameOver = false;
    createDungeon(width, height, wrap, interconnectivity, treasurePcnt);
    getStartNEnd();
    player = new PlayerImpl();
    initPosition();
  }

  // Creates a dungeon using several helper methods in the process.
  private void createDungeon(int width, int height, boolean wrap, int interconnectivity,
                             int treasurePercentage) {
    // creating a matrix of cells.
    dungeon = new Cell[height][width];
    initializeDungeon();

    // get possible paths.
    List<Path> possiblePaths = getPossiblePaths(wrap);
    //System.out.println("Number of possible paths: " + possiblePaths.size());

    // Apply kruskal's as per video.
    convertDungeon(possiblePaths, width, height, interconnectivity);

    // update caves and tunnels
    updateCavesNTunnels();

    // put treasure in the dungeon.
    putTreasure(treasurePercentage);
  }

  // Initialize the dungeon with Cells.
  private void initializeDungeon() {
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[0].length; j++) {
        dungeon[i][j] = new Cell(new int[]{i, j});
      }
    }
  }

  // Returns all the possible paths in the dungeon depending on wrapping/non-wrapping.
  private List<Path> getPossiblePaths(boolean wrap) {
    List<Path> paths = new ArrayList<>();

    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[0].length; j++) {

        Path p = makePath(Direction.NORTH, Direction.SOUTH, i, j, wrap);
        if (p != null) {
          if (!inPaths(p, paths)) {
            paths.add(p);
          }
        }
        p = makePath(Direction.SOUTH, Direction.NORTH, i, j, wrap);
        if (p != null) {
          if (!inPaths(p, paths)) {
            paths.add(p);
          }
        }
        p = makePath(Direction.EAST, Direction.WEST, i, j, wrap);
        if (p != null) {
          if (!inPaths(p, paths)) {
            paths.add(p);
          }
        }
        p = makePath(Direction.WEST, Direction.EAST, i, j, wrap);
        if (p != null) {
          if (!inPaths(p, paths)) {
            paths.add(p);
          }
        }

      }
    }
    return paths;
  }

  // Check if the current path is already in possible paths list. Avoid duplicates or reverse paths.
  private boolean inPaths(Path p, List<Path> paths) {
    for (Path path: paths) {
      if (path.getSrc().equals(p.getSrc()) && path.getDest().equals(p.getDest())
              || path.getSrc().equals(p.getDest()) && path.getDest().equals(p.getSrc())) {
        return true;
      }
    }
    return false;
  }

  // Create a path object with source & destination and add it to possiblePaths list.
  private Path makePath(Direction direction, Direction reverse, int i, int j, boolean wrap) {
    if (direction.getX() != 0) {
      int nextX = i + direction.getX();
      if (nextX == dungeon.length) {
        if (wrap) {
          return new Path(dungeon[i][j], dungeon[0][j], direction, reverse);
        } else {
          return null;
        }
      } else if (nextX < 0) {
        if (wrap) {
          return new Path(dungeon[i][j], dungeon[dungeon.length - 1][j], direction, reverse);
        } else {
          return null;
        }
      } else {
        return new Path(dungeon[i][j], dungeon[nextX][j], direction, reverse);
      }
    } else { // if (direction.getY() != 0)
      int nextY = j + direction.getY();
      if (nextY == dungeon[0].length) {
        if (wrap) {
          return new Path(dungeon[i][j], dungeon[i][0], direction, reverse);
        } else {
          return null;
        }
      } else if (nextY < 0) {
        if (wrap) {
          return new Path(dungeon[i][j], dungeon[i][dungeon[0].length - 1], direction, reverse);
        } else {
          return null;
        }
      } else {
        return new Path(dungeon[i][j], dungeon[i][nextY], direction, reverse);
      }
    }
  }

  // Convert the dungeon into MST using the updated kruskal's algorithm from the video.
  private List<Path> convertDungeon(List<Path> possPaths, int w, int h, int interConnect) {
    Random rand = new Random(0);
    List<Path> selectedPaths = new ArrayList<>();
    List<Path> leftOver = new ArrayList<>();
    List<List<Cell>> set = new ArrayList<>();

    // iterate until selectedPaths.size() <= number of vertices - 1.
    while (selectedPaths.size() < (w * h) - 1) {
      // pick a random path from possPaths.
      Path randPath = possPaths.get(rand.nextInt(possPaths.size()));
      if (selectedPaths.contains(randPath)) {
        randPath = possPaths.get(rand.nextInt(possPaths.size()));
      }
      // check if source and destination in same set. if not, add to selectedPaths.
      if (!checkSet(set, randPath.getSrc(), randPath.getDest())) {
        selectedPaths.add(randPath);
        set = addToSet(set, randPath.getSrc(), randPath.getDest());
      }
    }
    // add the remaining paths to leftOver paths.
    for (Path p: possPaths) {
      if (!selectedPaths.contains(p) ) {
        leftOver.add(p);
      }
    }
    // adding the extra edges according to desired interconnectivity.
    int temp = interConnect;
    for (Path path : leftOver) {
      selectedPaths.add(path);
      temp -= 1;
      if (temp == 0) {
        break;
      }
    }
    //System.out.println("Number of selected paths after interconnectivity: "+selectedPaths.size());
    //System.out.println("Number of left over paths after interconnectivity: "
    // + (leftOver.size()-interConnect));

    // update neighbors in cells.
    for (Path path : selectedPaths) {
      Cell src = path.getSrc();
      Direction sd = path.getSrdToDest();
      Cell dest = path.getDest();
      Direction ds = path.getDestToSrc();
      src.setNeighborList(sd, dest);
      src.setEntrances();
      dest.setNeighborList(ds, src);
      dest.setEntrances();
    }

    return selectedPaths;
  }

  // Helper method to see if source and destination are in same or different sets.
  private boolean checkSet(List<List<Cell>> set, Cell src, Cell dest) {
    if (set.size() == 0) {
      return false;
    } else {
      int srcListIndex = -1;
      int destListIndex = -1;
      for (List<Cell> cellList : set) {
        if (cellList.contains(src)) {
          srcListIndex = set.indexOf(cellList);
        }
        if (cellList.contains(dest)) {
          destListIndex = set.indexOf(cellList);
        }
      }
      return srcListIndex == destListIndex;
    }
  }

  // Helper method that adds the cells to appropriate sets.
  private List<List<Cell>> addToSet(List<List<Cell>> set, Cell src, Cell dest) {
    if (set.size() == 0) {
      set.add(new ArrayList<>() {
        {
          add(src);
          add(dest);
        }
      });
    } else {
      int srcListIndex = -1;
      int destListIndex = -1;
      for (List<Cell> cellList : set) {
        if (cellList.contains(src)) {
          srcListIndex = set.indexOf(cellList);
        }
        if (cellList.contains(dest)) {
          destListIndex = set.indexOf(cellList);
        }
      }
      // if both are not there in any list.
      if (srcListIndex == -1 && destListIndex == -1) {
        set.add(new ArrayList<>() {
          {
            add(src);
            add(dest);
          }
        });
      } else if (srcListIndex == -1) {
        // if only destination is present in a list.
        set.get(destListIndex).add(src);
      } else if (destListIndex == -1) {
        // if only source is present in a list.
        set.get(srcListIndex).add(dest);
      } else if (srcListIndex != destListIndex) {
        set.get(srcListIndex).addAll(set.get(destListIndex));
      }
    }
    return set;
  }

  // Update Cell to be a cave or a tunnel based on the number of entrances.
  private void updateCavesNTunnels() {
    for (Cell[] cells : dungeon) {
      for (Cell cell : cells) {
        int entrances = cell.getEntrances();
        if (entrances == 1 || entrances == 3 || entrances >= 4) {
          cell.setIsCave(true);
        } else if (entrances == 2) {
          cell.setIsTunnel(true);
        }
      }
    }
  }

  // Put treasure in the caves depending on the treasure percentage.
  private void putTreasure(int treasurePercentage) {
    Random rand = new Random(0);
    // get viable cells. Cells with entraces = 1,3,4. (Caves).
    List<Cell> caves = new ArrayList<>();
    for (Cell[] cells : dungeon) {
      for (Cell cell : cells) {
        if (cell.getIsCave()) {
          caves.add(cell);
        }
      }
    }
    //System.out.println("Number of caves (Entrances# = 1,3,4) are: " + caves.size());
    // Count the caves and divide them by percentage to get the no that should have treasure.
    int cavesWithTreasure = (int) Math.ceil((caves.size() * treasurePercentage) / 100);
    //System.out.println("Number of caves with treasure allowed: " + cavesWithTreasure);
    // randomly pick a cave and update its treasureList.
    for (int i = 0; i < cavesWithTreasure; i++) {
      Cell cave = caves.get(rand.nextInt(caves.size()));
      cave.setTreasureList(Treasure.SAPPHIRE,  1);
      cave.setTreasureList(Treasure.DIAMONDS,  2);
      cave.setTreasureList(Treasure.RUBIES,  3);
    }
  }

  // Get start and end cave for the player to play the game. Distance should be > 5.
  private void getStartNEnd() {
    Random rand = new Random(0);
    // get starting location and ending location.
    start = dungeon[rand.nextInt(dungeon.length)][rand.nextInt(dungeon[0].length)];
    while (start.getIsTunnel()) {
      start = dungeon[rand.nextInt(dungeon.length)][rand.nextInt(dungeon[0].length)];
    }

    List<Cell> farthest = new ArrayList<>();
    double distance = 0.0;
    for (Cell[] cellList: dungeon) {
      for (Cell cell: cellList) {
        // check distance between starting and ending location using euclidean/manhattan distance.
        distance = Math.sqrt(Math.pow((cell.getCoordinates()[0] - start.getCoordinates()[0]), 2)
                + (Math.pow((cell.getCoordinates()[1] - start.getCoordinates()[1]), 2)));
        if (distance >= 5) {
          farthest.add(cell);
        }
      }
    }
    if (farthest.size() == 0) {
      throw new IllegalArgumentException("Cannot find the start & end nodes as per rules given.");
    } else {
      end = farthest.get(rand.nextInt(farthest.size()));
      while (end.getIsTunnel()) {
        end = farthest.get(rand.nextInt(farthest.size()));
      }
    }
  }

  // Set player's initial position.
  private void initPosition() {
    // put player in the starting location.
    player.setPosition(start.getCoordinates());
  }

  @Override
  public void play(Direction direction, String pickTreasure) {
    if (direction != Direction.NORTH && direction != Direction.SOUTH && direction != Direction.WEST
            && direction != Direction.EAST) {
      throw new IllegalArgumentException("Invalid Direction specified.");
    }
    if (calcNextLocation(direction, pickTreasure)) {
      if (Arrays.equals(player.getPosition(), end.getCoordinates())) {
        this.gameOver = true;
      }
    }
  }

  // calculates the next location, moves the player and sets the player's treasure bag.
  private boolean calcNextLocation(Direction direction, String pick) {
    Cell curr = dungeon[player.getPosition()[0]][player.getPosition()[1]];
    if (curr.getNeighborList().containsKey(direction)) {
      Cell next = curr.getNeighborList().get(direction);
      player.setPosition(next.getCoordinates());
      if ("yes".equalsIgnoreCase(pick)) {
        player.setBag(next.getTreasureList());
        next.emptyTreasure();
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isGameOver() {
    return this.gameOver;
  }

  @Override
  public StringBuilder getGameDetails() {
    String temp = "";
    if (dungeon[player.getPosition()[0]][player.getPosition()[1]].getIsTunnel()) {
      temp += " || In a Tunnel";
    } else {
      temp += " || In a Cave";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Position: ").append(Arrays.toString(player.getPosition())).append(temp)
            .append(" || ").append("Bag: ").append(player.getBag()).append("\nPossible moves: ")
            .append(dungeon[player.getPosition()[0]][player.getPosition()[1]].getNeighborList()
                    .keySet());
    return sb;
  }

  @Override
  public int[] getStartLocation() {
    return start.getCoordinates();
  }

  @Override
  public int[] getEndLocation() {
    return end.getCoordinates();
  }

  @Override
  public Cells[][] dungeonDump() {
    Cell[][] copy = new Cell[dungeon.length][dungeon[0].length];
    for (int i = 0; i < dungeon.length; i++) {
      System.arraycopy(dungeon[i], 0, copy[i], 0, dungeon[0].length);
    }
    return copy;
  }

  @Override
  public int[] getCurrPlayerPosition() {
    return player.getPosition();
  }

  // end of class.
}
