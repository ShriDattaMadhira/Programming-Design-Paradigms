import org.junit.Test;

import java.util.Arrays;

import dungeon.Cells;
import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test to check if the dungeon creation and gameplay are behaving as expected.
 */
public class DungeonTest {
  private Dungeon dungeon;

  /**
   * Test to check if the exception cases are handled properly.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments() {
    // width is invalid (less than or equal to zero).
    dungeon = new DungeonImpl(0, 4, true, 3, 24);
    // height is invalid (less than or equal to zero).
    dungeon = new DungeonImpl(6, 0, false, 5, 9);
    // width and height are too small.
    dungeon = new DungeonImpl(4, 4, false, 1, 10);
    // interconnectivity is invalid (less than zero).
    dungeon = new DungeonImpl(5, 5, true, -1, 100);
    // interconnectivity is greater than the number of edges that can be present.
    dungeon = new DungeonImpl(5, 5, false, 26, 98);
    // treasure percentage is invalid (less than zero).
    dungeon = new DungeonImpl(10, 9, true, 31, -20);
    // all good dungeon - wrapping.
    dungeon = new DungeonImpl(7, 5, true, 8, 5);
    // all good dungeon - non-wrapping.
    dungeon = new DungeonImpl(6, 6, false, 4, 5);
  }

  /**
   * Test if the game is running correctly as per the inputs given by the player.
   * I am using the gameDetails method that returns a StringBuilder to check if the game is
   * progressing as intended (are we in the correct place after 2/3 moves, are the possible moves
   * being printed correctly).
   */
  @Test
  public void testPlay() {
    dungeon = new DungeonImpl(7, 5, true, 8, 25);
    assertFalse(dungeon.isGameOver());
    // player starts at start.
    assertEquals("[0, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "yes");
    dungeon.play(Direction.SOUTH, "no");
    dungeon.play(Direction.SOUTH, "yes");
    dungeon.play(Direction.SOUTH, "yes");
    String expected = "Position: [4, 2] || In a Cave || Bag: []\n"
            + "Possible moves: [NORTH, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.EAST, "yes");
    dungeon.play(Direction.EAST, "no");
    dungeon.play(Direction.EAST, "no");
    assertTrue(dungeon.isGameOver());

    dungeon = new DungeonImpl(6, 6, false, 4, 21);
    assertFalse(dungeon.isGameOver());
    // player starts at start.
    assertEquals("[0, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "yes");
    dungeon.play(Direction.WEST, "yes");
    dungeon.play(Direction.WEST, "yes");
    dungeon.play(Direction.WEST, "yes");
    expected = "Position: [0, 0] || In a Tunnel || Bag: [SAPPHIRE, DIAMONDS, DIAMONDS, RUBIES, "
            + "RUBIES, RUBIES]\nPossible moves: [SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "yes");
    dungeon.play(Direction.SOUTH, "no");
    dungeon.play(Direction.SOUTH, "yes");
    dungeon.play(Direction.SOUTH, "yes");
    dungeon.play(Direction.EAST, "no");
    dungeon.play(Direction.SOUTH, "yes");
    assertTrue(dungeon.isGameOver());
  }

  /**
   * Test if the game is ending when the end/goal location is reached.
   */
  @Test
  public void testIsGameOver() {
    // testing for wrapping dungeon.
    dungeon = new DungeonImpl(7, 5, true, 8, 25);
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "no");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.EAST, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.EAST, "no");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.EAST, "no");
    assertTrue(dungeon.isGameOver());

    // testing for non-wrapping dungeon.
    dungeon = new DungeonImpl(6, 6, false, 4, 21);
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.WEST, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.WEST, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.WEST, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.WEST, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "no");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.EAST, "no");
    assertFalse(dungeon.isGameOver());
    dungeon.play(Direction.SOUTH, "yes");
    assertTrue(dungeon.isGameOver());
  }

  /**
   * Test if start and end locations are generated as per the conditions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetStartNEndLocation() {
    // none of the nodes selected are caves.
    dungeon = new DungeonImpl(6, 4, true, 3, 25);
    // dungeon is too small.
    dungeon = new DungeonImpl(3, 3, false, 4, 10);

    // testing for wrapping dungeon.
    dungeon = new DungeonImpl(7, 5, true, 8, 20);
    // testing the start location generation.
    int[] start = dungeon.getStartLocation();
    assertEquals(0, start[0]);
    assertEquals(2, start[1]);
    // testing the end location generation.
    int[] end = dungeon.getEndLocation();
    assertEquals(4, end[0]);
    assertEquals(5, end[1]);
    // if distance between start and end is > 5.
    double distance = Math.sqrt(Math.pow((end[0] - start[0]), 2)
            + (Math.pow((end[1] - start[1]), 2)));
    assertTrue(distance >= 5);

    // testing for non-wrapping dungeon.
    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    start = dungeon.getStartLocation();
    assertEquals(0, start[0]);
    assertEquals(4, start[1]);
    end = dungeon.getEndLocation();
    assertEquals(5, end[0]);
    assertEquals(1, end[1]);
    distance = Math.sqrt(Math.pow((end[0] - start[0]), 2) + (Math.pow((end[1] - start[1]), 2)));
    assertTrue(distance >= 5);
  }

  /**
   * Test if the details of the current player and game state are printed as expected.
   */
  @Test
  public void testGameDetails() {
    // testing for wrapping dungeon.
    dungeon = new DungeonImpl(7, 5, true, 8, 20);
    String expected = "Position: [0, 2] || In a Cave || Bag: []\n"
            + "Possible moves: [WEST, NORTH, SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [1, 2] || In a Cave || Bag: []\nPossible moves: [SOUTH, EAST, NORTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.WEST, "no");

    expected = "Position: [1, 2] || In a Cave || Bag: []\nPossible moves: [SOUTH, EAST, NORTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [2, 2] || In a Cave || Bag: []\nPossible moves: "
            + "[SOUTH, WEST, EAST, NORTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [3, 2] || In a Tunnel || Bag: []\nPossible moves: [NORTH, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [4, 2] || In a Cave || Bag: []\nPossible moves: [NORTH, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.EAST, "no");

    expected = "Position: [4, 3] || In a Cave || Bag: []\nPossible moves: [WEST, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.EAST, "no");

    expected = "Position: [4, 4] || In a Cave || Bag: []\nPossible moves: [WEST, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.EAST, "no");

    expected = "Position: [4, 5] || In a Cave || Bag: []\nPossible moves: "
            + "[WEST, SOUTH, EAST, NORTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());

    assertTrue(dungeon.isGameOver());

    // testing for non-wrapping dungeon.
    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    expected = "Position: [0, 4] || In a Cave || Bag: []\nPossible moves: [WEST, SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.WEST, "no");

    expected = "Position: [0, 3] || In a Cave || Bag: []\nPossible moves: [WEST, SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.WEST, "no");

    expected = "Position: [0, 2] || In a Cave || Bag: []\nPossible moves: [WEST, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.WEST, "no");

    expected = "Position: [0, 1] || In a Cave || Bag: []\nPossible moves: [SOUTH, EAST, WEST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.WEST, "yes");

    expected = "Position: [0, 0] || In a Tunnel || Bag: []\nPossible moves: [SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "yes");

    expected = "Position: [1, 0] || In a Cave || Bag: []\nPossible moves: [NORTH, SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "yes");

    expected = "Position: [2, 0] || In a Cave || Bag: []\nPossible moves: [NORTH, SOUTH, EAST]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [3, 0] || In a Cave || Bag: []\nPossible moves: [NORTH, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "yes");

    expected = "Position: [4, 0] || In a Cave || Bag: []\nPossible moves: [NORTH, EAST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.EAST, "yes");

    expected = "Position: [4, 1] || In a Tunnel || Bag: []\nPossible moves: [WEST, SOUTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());
    dungeon.play(Direction.SOUTH, "no");

    expected = "Position: [5, 1] || In a Cave || Bag: []\nPossible moves: [NORTH]";
    assertEquals(expected, dungeon.getGameDetails().toString());

    assertTrue(dungeon.isGameOver());
  }

  @Test
  public void testDungeonConnectivity() {
    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    assertEquals("[0, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[0, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[1, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[2, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[3, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[4, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[5, 5]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.WEST, "no");
    assertEquals("[1, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    assertEquals("[1, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no"); //0,3
    assertEquals("[0, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    assertEquals("[0, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[1, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    assertEquals("[1, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no");
    assertEquals("[0, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    assertEquals("[0, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no"); //1,0
    assertEquals("[1, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[2, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[2, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[2, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[2, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[2, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no"); //3,4
    assertEquals("[3, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.WEST, "no"); //2,2
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[3, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[3, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[4, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no"); //5,2
    assertEquals("[5, 2]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.EAST, "no");
    assertEquals("[4, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[5, 3]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[5, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no"); //4,4
    assertEquals("[4, 4]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.NORTH, "no"); //2,2
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[3, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.EAST, "no");
    assertEquals("[3, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.WEST, "no");
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[4, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");
    assertEquals("[5, 0]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.NORTH, "no");
    dungeon.play(Direction.EAST, "no"); //4,1
    assertEquals("[4, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
    dungeon.play(Direction.SOUTH, "no");// 5,1 - END LOCATION
    assertEquals("[5, 1]", Arrays.toString(dungeon.getCurrPlayerPosition()));
  }

  /**
   * Test if the treasure is being stored in the way that is expected.
   */
  @Test
  public void testTreasurePercentage() {
    dungeon = new DungeonImpl(7, 5, true, 8, 20);
    int[] temp = treasurePercentageHelper(dungeon.dungeonDump());
    assertEquals(0.20, (float) temp[1] / temp[0], 0.2);
    assertNotEquals(0.19, (float) temp[1] / temp[0], 0.0);

    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    temp = treasurePercentageHelper(dungeon.dungeonDump());
    assertEquals(0.05, (float) temp[1] / temp[0], 0.01);
  }

  private int[] treasurePercentageHelper(Cells[][] dungeonCopy) {
    int caveCount = 0;
    int treasureCount = 0;
    for (Cells[] cellList: dungeonCopy) {
      for (Cells cell: cellList) {
        if (cell.getIsCave()) {
          caveCount += 1;
          if (cell.getTreasureList().size() > 0) {
            treasureCount += 1;
          }
        }
      }
    }
    return new int[]{caveCount, treasureCount};
  }

  /**
   * Test if the interconnectivity desired is achieved.
   */
  @Test
  public void testInterconnectivity() {
    dungeon = new DungeonImpl(7, 5, true, 8, 20);
    assertEquals(42, interconnectivityHelper(dungeon.dungeonDump()) / 2);

    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    assertEquals(39, interconnectivityHelper(dungeon.dungeonDump()) / 2);
  }

  private int interconnectivityHelper(Cells[][] dungeonCopy) {
    int count = 0;
    for (Cells[] cellList: dungeonCopy) {
      for (Cells cell: cellList) {
        count += cell.getEntrances();
      }
    }
    return count;
  }

  /**
   * Test to check if caves and tunnels are assigned correctly depending on number of entrances.
   * AND
   * Test to check if treasure is only assigned to caves and not tunnels.
   */
  @Test
  public void testCavesTunnels() {
    dungeon = new DungeonImpl(7, 5, true, 8, 20);
    Cells[][] dungeonCopy = dungeon.dungeonDump();
    assertTrue(cavesTunnelsHelper(dungeonCopy));
    assertTrue(treasureOnlyInCaves(dungeonCopy));

    dungeon = new DungeonImpl(6, 6, false, 4, 5);
    dungeonCopy = dungeon.dungeonDump();
    assertTrue(cavesTunnelsHelper(dungeonCopy));
    assertTrue(treasureOnlyInCaves(dungeonCopy));
  }

  private boolean cavesTunnelsHelper(Cells[][] dungeonCopy) {
    for (Cells[] cells : dungeonCopy) {
      for (int j = 0; j < dungeonCopy[0].length; j++) {
        if (cells[j].getEntrances() == 1 || cells[j].getEntrances() == 3
                || cells[j].getEntrances() == 4) {
          if (cells[j].getIsTunnel()) {
            return false;
          }
        } else if (cells[j].getEntrances() == 2) {
          if (cells[j].getIsCave()) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private boolean treasureOnlyInCaves(Cells[][] dungeonCopy) {
    for (Cells[] cells : dungeonCopy) {
      for (Cells cell: cells) {
        if (cell.getIsTunnel()) {
          if (cell.getTreasureList().size() > 0) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
