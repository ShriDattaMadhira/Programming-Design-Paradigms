import static org.junit.Assert.assertEquals;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;
import java.io.StringReader;
import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {
  TicTacToeController c;
  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model
  
  // TODO: Implement your own tests cases for the controller

  /**
   * Test to check a variety of invalid inputs and other tests.
   * Tests checked in this method,
   * 1. Invalid row & column - Row and Column are not given as numbers.
   * 2. Out of bounds row & column - Row and Column are not given within the bounds of the board.
   * 3. Signal to quit the game ("q") - given as a row input and column input.
   * 4. Asserts that the game runs even after we give an invalid move.
   * 5. Asserts that the game behaves as expected when given a move to a filled space.
   */
  @Test
  public void testInvalidInput() {
    // invalid row, column.
    // "q" given as a column.
    StringReader input = new StringReader("two 2 3 one 1 q");
    StringBuilder gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    String temp = "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for X:\n" +
                  "Not a valid number: two\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   | X\n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for O:\n" +
                  "Not a valid number: one\n" +
                  "Game quit! Ending game state:\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   | X\n" +
                  "-----------\n" +
                  "   |   |  \n";
    assertEquals(temp, gameLog.toString());

    // out of bounds row, column.
    // "q" given as a row.
    // 1,1 repeated twice - check if the program works if a move to a filled space is given.
    // Checking game runs after invalid move.
    input = new StringReader("5 1 2 5 1 1 1 1 q 2");
    gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    temp = "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           "Not a valid move: 5, 1\n" +
           "Not a valid move: 2, 5\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           "Not a valid move: 1, 1\n" +
           "Game quit! Ending game state:\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n";
    assertEquals(temp, gameLog.toString());

    // valid move
    input = new StringReader("1 1 q");
    gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    temp = "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           "Game quit! Ending game state:\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n";
    assertEquals(temp, gameLog.toString());
  }

  /**
   * Test to check how the controller handles the "null" for the model as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("1 1 2 2 1 0 2 1");
    StringBuilder gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(null);
  }

  /**
   * Test to check if the winners are declared as expected when every move is correctly given.
   */
  @Test
  public void testWinners() {
    // player O wins
    StringReader input = new StringReader("1 1 1 3 2 2 3 3 2 1 2 3 q");
    StringBuilder gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    String temp = "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for X:\n" +
                  " X |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for O:\n" +
                  " X |   | O\n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for X:\n" +
                  " X |   | O\n" +
                  "-----------\n" +
                  "   | X |  \n" +
                  "-----------\n" +
                  "   |   |  \n" +
                  "Enter a move for O:\n" +
                  " X |   | O\n" +
                  "-----------\n" +
                  "   | X |  \n" +
                  "-----------\n" +
                  "   |   | O\n" +
                  "Enter a move for X:\n" +
                  " X |   | O\n" +
                  "-----------\n" +
                  " X | X |  \n" +
                  "-----------\n" +
                  "   |   | O\n" +
                  "Enter a move for O:\n" +
                  " X |   | O\n" +
                  "-----------\n" +
                  " X | X | O\n" +
                  "-----------\n" +
                  "   |   | O\n" +
                  "Game is over! O wins.\n";
    assertEquals(temp, gameLog.toString());

    // player X wins
    input = new StringReader("1 1 1 3 2 2 2 3 3 3");
    gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    temp = "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           " X |   | O\n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X |   | O\n" +
           "-----------\n" +
           "   | X |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           " X |   | O\n" +
           "-----------\n" +
           "   | X | O\n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X |   | O\n" +
           "-----------\n" +
           "   | X | O\n" +
           "-----------\n" +
           "   |   | X\n" +
           "Game is over! X wins.\n";
    assertEquals(temp, gameLog.toString());

    // game ties.
    input = new StringReader("1 1 1 2 1 3 2 1 2 3 3 3 2 2 3 1 3 2");
    gameLog = new StringBuilder();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(new TicTacToeModel());
    temp = "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           " X | O |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X | O | X\n" +
           "-----------\n" +
           "   |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O |   |  \n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for X:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O |   | X\n" +
           "-----------\n" +
           "   |   |  \n" +
           "Enter a move for O:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O |   | X\n" +
           "-----------\n" +
           "   |   | O\n" +
           "Enter a move for X:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O | X | X\n" +
           "-----------\n" +
           "   |   | O\n" +
           "Enter a move for O:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O | X | X\n" +
           "-----------\n" +
           " O |   | O\n" +
           "Enter a move for X:\n" +
           " X | O | X\n" +
           "-----------\n" +
           " O | X | X\n" +
           "-----------\n" +
           " O | X | O\n" +
           "Game is over! Tie game.\n";
    assertEquals(temp, gameLog.toString());
  }

  /**
   * Test to check if the Appendable is working as expected.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }
}
