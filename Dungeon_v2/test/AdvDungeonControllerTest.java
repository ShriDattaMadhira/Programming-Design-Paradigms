import org.junit.Test;

import java.io.StringReader;

import dungeon.AdvDungeonConsoleController;
import dungeon.AdvDungeonController;
import dungeon.AdvancedDungeon;
import dungeon.Dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Here we conduct various tests on controller to see that the combination of controller and model
 * are working as expected for any given input.
 */
public class AdvDungeonControllerTest {
  private AdvDungeonController controller;

  /**
   * Test to check if the exception cases are handled properly.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments() {
    StringReader input = new StringReader("m south m south");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    // width is invalid (less than or equal to zero).
    controller.playGame(new AdvancedDungeon(0, 4, true, 3,
            24, 4));
    // height is invalid (less than or equal to zero).
    controller.playGame(new AdvancedDungeon(6, 0, false, 5,
            9, 4));
    // width and height are too small.
    controller.playGame(new AdvancedDungeon(4, 4, false, 1,
            10, 4));
    // interconnectivity is invalid (less than zero).
    controller.playGame(new AdvancedDungeon(5, 5, true, -1,
            100, 4));
    // interconnectivity is greater than the number of edges that can be present.
    controller.playGame(new AdvancedDungeon(5, 5, false, 26,
            98, 4));
    // treasure percentage is invalid (less than zero).
    controller.playGame(new AdvancedDungeon(10, 9, true, 31,
            -20, 4));
    // too less otyughs
    controller.playGame(new AdvancedDungeon(10, 9, true, 31,
            -20, 0));
    // too many otyughs
    controller.playGame(new AdvancedDungeon(10, 9, true, 31,
            -20, 100));
    // all good dungeon - wrapping.
    controller.playGame(new AdvancedDungeon(7, 5, true, 8,
            5, 4));
    // all good dungeon - non-wrapping.
    controller.playGame(new AdvancedDungeon(6, 6, false, 4,
            5, 5));
  }

  /**
   * Test if the game is moving the player correctly as per the inputs given.
   */
  @Test
  public void testMove() {
    // wrapping dungeon
    StringReader input = new StringReader("m east m north m south m west");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(7, 5, true, 8,
            25, 4));
    String expected = "\nCurrent location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? east\n" + "\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go NORTH, WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? north\n" + "\n" + "Current location: [4, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, EAST, SOUTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go NORTH, WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n" + "\n" + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    // This tests the following for a wrapping dungeon,
    //  1. Player is starting at the expected starting position - [0,2]
    //  2. Move East - [0,3]
    //  3. Move North - [4,3]
    //  4. Move South - [0,3]
    //  5. Move West - [0,2]
    assertEquals(expected, gameLog.toString());

    // non-wrapping dungeon
    input = new StringReader("m west m south m north m east");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(6, 6, false, 4,
            21, 5));
    expected = "\nCurrent location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n" + "\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [1, 3]. This is a Tunnel.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go NORTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? north\n" + "\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? east\n" + "\n" + "Current location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    // This tests the following for a non-wrapping dungeon,
    //  1. Player is starting at the expected starting position - [0,4]
    //  2. Move West - [0,3]
    //  3. Move South - [1,3]
    //  4. Move North - [0,3]
    //  5. Move East - [0,4]
    assertEquals(expected, gameLog.toString());
  }

  /**
   * Test if the game is shooting the monster correctly as per the inputs given by the user.
   */
  @Test
  public void testShoot() {
    // wrapping
    StringReader input = new StringReader("s north 2 s north 1 s north 1");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(7, 5, true, 8,
            25, 4));
    String expected = "\n" + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? north\n" + "How far do you want the shoot the arrow? 2\n" + "\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? north\n" + "How far do you want the shoot the arrow? 1\n" + "\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 1\n"
            + "More pungent Smell. Otyugh in very close proximity. Otyugh injured.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? north\n" + "How far do you want the shoot the arrow? 1\n" + "\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 0\n"
            + "Otyugh in very close proximity is dead.No smell. No Otyugh nearby.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    // This test asserts that, for a wrapping dungeon,
    //  1. After shooting, player's arrow count decreases by 1.
    //  2. When shot with wrong distance but correct direction, nothing will happen.
    //  3. When shot in the correct direction and with correct distance.
    //        a. First shot, Otyugh gets injured.
    //        b. Second shot, Otyugh dies.
    assertEquals(expected, gameLog.toString());

    // non-wrapping
    input = new StringReader("s south 1 s south 1");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(6, 6, false, 4,
            21, 5));
    expected = "\nCurrent location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n" + "\n"
            + "Current location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n"
            + "More pungent Smell. Otyugh in very close proximity. Otyugh injured.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n" + "\n"
            + "Current location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 1\n"
            + "Otyugh in very close proximity is dead.No smell. No Otyugh nearby.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    // This test asserts that, for a non-wrapping dungeon,
    //  1. After shooting, player's arrow count decreases by 1.
    //  2. When shot with wrong distance but correct direction, nothing will happen.
    //  3. When shot in the correct direction and with correct distance,
    //        a. First shot, Otyugh gets injured.
    //        b. Second shot, Otyugh dies.
    assertEquals(expected, gameLog.toString());
  }

  /**
   * Test if the game is allowing the player to pickup treasure and arrows and is updating the
   * information as expected.
   */
  @Test
  public void testPick() {
    // wrapping
    StringReader input = new StringReader("p 0 1 m south m south p 1 1");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(7, 5, true, 8,
            25, 4));
    String expected = "\nCurrent location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] false\n"
            + "Do you want to pick up arrows?[1/0] true\n" + "\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [2, 2]. This is a Cave.\n"
            + "There is treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] true\n"
            + "Do you want to pick up arrows?[1/0] true\n" + "\n"
            + "Current location: [2, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 13\n"
            + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());

    // non-wrapping
    input = new StringReader("p 0 1 m west m west m west p 1 1");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(new AdvancedDungeon(6, 6, false, 4,
            21, 5));
    expected = "\nCurrent location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] false\n"
            + "Do you want to pick up arrows?[1/0] true\n" + "\n"
            + "Current location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n" + "\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n" + "\n" + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, EAST, SOUTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n" + "\n" + "Current location: [0, 1]. This is a Cave.\n"
            + "There is treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 8\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go SOUTH, EAST, WEST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] true\n"
            + "Do you want to pick up arrows?[1/0] true\n" + "\n"
            + "Current location: [0, 1]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 8\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go SOUTH, EAST, WEST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
  }

  /**
   * Test if the game is ending when the end/goal location is reached or when the player is eaten
   * by the monster(Otyugh).
   */
  @Test
  public void testGameEndScenarios() {
    //wrapping dungeon
    Dungeon wrap = new AdvancedDungeon(7, 5, true, 8,
            25, 4);
    // Player reaches the end location successfully without being killed
    StringReader input = new StringReader("p 0 1 m east m north m east s east 1 s east 1 "
            + "m east");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    assertFalse(wrap.isGameOver());
    controller.playGame(wrap);
    assertTrue(wrap.isGameOver());

    // Player gets eaten by Otyugh at end location. Confirms presence of otyugh at end location.
    input = new StringReader("p 0 1 m east m north m east m east");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    assertFalse(wrap.isGameOver());
    assertFalse(wrap.getEaten());
    controller.playGame(wrap);
    assertTrue(wrap.getEaten());
    assertTrue(wrap.isGameOver());

    // Player gets eaten by Otyugh and game ends.
    wrap = new AdvancedDungeon(7, 5, true, 8,
            25, 4);
    input = new StringReader("m north");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    assertFalse(wrap.isGameOver());
    assertFalse(wrap.getEaten());
    controller.playGame(wrap);
    assertTrue(wrap.getEaten());
    assertTrue(wrap.isGameOver());

    //non-wrapping dungeon
    Dungeon nonWrap = new AdvancedDungeon(6, 6, false, 4,
            21, 5);
    // Player reaches the end location successfully without being killed
    input = new StringReader("p 0 1 m west m west m west m west m south m south s south 1"
            + "s south 1 m south m south m east s south 1 s south 1 m east");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    assertFalse(nonWrap.isGameOver());
    controller.playGame(nonWrap);
    assertTrue(nonWrap.isGameOver());

    // Player gets eaten by Otyugh and game ends.
    nonWrap = new AdvancedDungeon(6, 6, false, 4,
            21, 5);
    input = new StringReader("m south");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    assertFalse(nonWrap.isGameOver());
    assertFalse(nonWrap.getEaten());
    controller.playGame(nonWrap);
    assertTrue(nonWrap.getEaten());
    assertTrue(nonWrap.isGameOver());
  }

  /**
   * Test if player is getting correct hints in terms of smell when monsters are nearby.
   */
  @Test
  public void testSmell() {
    String expected = "";
    // wrapping
    Dungeon wrap = new AdvancedDungeon(7, 5, true, 8,
            25, 4);
    //  More pungent smell because of one monster in neighboring cells.
    StringReader input = new StringReader("");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\nCurrent location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {}\nCurrent Arrow Count: 3\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
    // Less pungent smell because of one monster at +2 distance from current cell.
    input = new StringReader("m south");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\nCurrent location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {}\nCurrent Arrow Count: 3\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n"
            + "\nWhat action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\nCurrent location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n"
            + "Current bag: {}\nCurrent Arrow Count: 3\n"
            + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
    // More pungent smell because of multiple monsters at +2 distance from current cell.
    input = new StringReader("m south p 1 1 m south s south 1 s south 1 m south m east m east");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\nCurrent location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\n" + "Current location: [2, 2]. This is a Cave.\n"
            + "There is treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] true\n"
            + "Do you want to pick up arrows?[1/0] true\n\n"
            + "Current location: [2, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 8\n"
            + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\n" + "Current location: [3, 2]. This is a Tunnel.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 8\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go NORTH, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n\n"
            + "Current location: [3, 2]. This is a Tunnel.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 7\n"
            + "More pungent Smell. Otyugh in very close proximity. Otyugh injured.\n"
            + "You can go NORTH, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n\n"
            + "Current location: [3, 2]. This is a Tunnel.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 6\n"
            + "Otyugh in very close proximity is dead.No smell. No Otyugh nearby.\n"
            + "You can go NORTH, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\n" + "Current location: [4, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 10 arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 6\n"
            + "Otyugh in this cave is dead. No smell. No Otyugh nearby.\n"
            + "You can go NORTH, EAST, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? east\n\n" + "Current location: [4, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 6\n"
            + "Otyugh in very close proximity is dead.Less pungent smell. Otyugh in close proximity"
            + ".\nYou can go WEST, EAST, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? east\n\n" + "Current location: [4, 4]. This is a Cave.\n"
            + "There is treasure here. There are no arrows here.\n"
            + "Current bag: {DIAMONDS=2, SAPPHIRE=1, RUBIES=3}\n" + "Current Arrow Count: 6\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, EAST, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());

    // non-wrapping
    Dungeon nonWrap = new AdvancedDungeon(6, 6, false, 4,
            21, 5);
    //  More pungent smell because of one monster in neighboring cells.
    input = new StringReader("");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(nonWrap);
    expected = "\nCurrent location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n"
            + "Current bag: {}\nCurrent Arrow Count: 3\n"
            + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
    // Less pungent smell because of one monster at +2 distance from current cell.
    input = new StringReader("m west m west m west m west m south m south s south 1 s south 1");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(nonWrap);
    expected = "\nCurrent location: [0, 4]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n\n" + "Current location: [0, 3]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n\n" + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go WEST, EAST, SOUTH from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n\n" + "Current location: [0, 1]. This is a Cave.\n"
            + "There is treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go SOUTH, EAST, WEST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? west\n\n" + "Current location: [0, 0]. This is a Tunnel.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "No smell. No Otyugh nearby.\n"
            + "You can go SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\n" + "Current location: [1, 0]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go NORTH, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n\n" + "Current location: [2, 0]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go NORTH, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n\n"
            + "Current location: [2, 0]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n"
            + "More pungent Smell. Otyugh in very close proximity. Otyugh injured.\n"
            + "You can go NORTH, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 1\n\n"
            + "Current location: [2, 0]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 1\n"
            + "Otyugh in very close proximity is dead.Less pungent smell. Otyugh in close proximity"
            + ".\nYou can go NORTH, SOUTH, EAST from here.\n\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
  }

  /**
   * Test how the controller reacts to invalid inputs for action(move, shoot, pickup),
   * direction(north, south, east, west), and distance(only > 0).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputs() {
    String expected = "";

    // passing a null for dungeon.
    StringReader input = new StringReader("n south [ 1 1 d south 1 m south p 1 1 s south 2");
    StringBuilder gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(null);

    // wrapping
    Dungeon wrap = new AdvancedDungeon(7, 5, true, 8,
            25, 4);
    //  Invalid action.
    input = new StringReader("n south [ 1 1 d south 1 m south p 1 1 s south 2");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\n" + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - n\n"
            + "---Invalid choice of action - n. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - south\n"
            + "---Invalid choice of action - south. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - [\n"
            + "---Invalid choice of action - [. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - 1\n"
            + "---Invalid choice of action - 1. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - 1\n"
            + "---Invalid choice of action - 1. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - d\n"
            + "---Invalid choice of action - d. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - south\n"
            + "---Invalid choice of action - south. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - 1\n"
            + "---Invalid choice of action - 1. Choose Again.---\n"
            + "Current location: [0, 2]. This is a Cave.\n"
            + "There is no treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "More pungent Smell. Otyugh in very close proximity.\n"
            + "You can go WEST, NORTH, SOUTH, EAST from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - p\n"
            + "Do you want to pick up treasure?[1/0] true\n"
            + "Do you want to pick up arrows?[1/0] true\n" + "\n"
            + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 3\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? 2\n" + "\n"
            + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());

    // invalid direction
    input = new StringReader("m pdp");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\nCurrent location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? pdp\n"
            + "------Direction can only be North/South/East/West.------\n" + "\n"
            + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());

    // invalid distance.
    input = new StringReader("m south s south one s south -2");
    gameLog = new StringBuilder();
    controller = new AdvDungeonConsoleController(input, gameLog);
    controller.playGame(wrap);
    expected = "\n" + "Current location: [1, 2]. This is a Cave.\n"
            + "There is no treasure here. There are no arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - m\n"
            + "Which direction? south\n" + "\n" + "Current location: [2, 2]. This is a Cave.\n"
            + "There is treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n"
            + "How far do you want the shoot the arrow? ---Give numerical digits---\n" + "\n"
            + "Current location: [2, 2]. This is a Cave.\n"
            + "There is treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - s\n"
            + "Which direction? south\n" + "How far do you want the shoot the arrow? -2\n"
            + "---Can't shoot. Distance too low.---\n" + "\n"
            + "Current location: [2, 2]. This is a Cave.\n"
            + "There is treasure here. There are 5 arrows here.\n" + "Current bag: {}\n"
            + "Current Arrow Count: 2\n" + "Less pungent smell. Otyugh in close proximity.\n"
            + "You can go SOUTH, WEST, EAST, NORTH from here.\n" + "\n"
            + "What action you want to choose? Move(M), Shoot an arrow(S), Pick items(P) - \n"
            + "Player is not eaten. Player did not reach end location. But input ended.\n";
    assertEquals(expected, gameLog.toString());
  }
  // end of class
}
