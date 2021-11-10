import org.junit.Before;
import org.junit.Test;

import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Tic-Tac-Toe.
 */
public class TicTacToeTest {
  private TicTacToe ttt;

  @Before
  public void setUp() {
    ttt = new TicTacToeModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    // position out of board bounds.
    ttt.move(3, 3);

    //position already occupied.
    ttt.move(0,0); // game started here.
    ttt.move(0,0);

    // game resumes.
    ttt.move(0,2);
    ttt.move(1,1);
    ttt.move(2,2);
    ttt.move(1,0);
    ttt.move(1,2); // player O wins after this move

    // making a move after the game ended.
    ttt.move(2,1);
  }

  @Test
  public void testGetTurn() {
    assertEquals(Player.X, ttt.getTurn());
    ttt.move(0,0);
    assertEquals(Player.O, ttt.getTurn());
    ttt.move(1,1);
    assertEquals(Player.X, ttt.getTurn());
    ttt.move(2,2);
    assertNotEquals(Player.X, ttt.getTurn());
  }

  @Test
  public void testIsGameOver() {
    // Player O wins.
    assertFalse(ttt.isGameOver());
    ttt.move(0,0);
    ttt.move(0,2);
    ttt.move(1,1);
    ttt.move(2,2);
    ttt.move(1,0);
    ttt.move(1,2);
    assertTrue(ttt.isGameOver());
    assertEquals(Player.O, ttt.getWinner());

    // game ties.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(0,1);
    ttt.move(0,2);
    ttt.move(1,0);
    ttt.move(1,2);
    ttt.move(2,2);
    ttt.move(1,1);
    ttt.move(2,0);
    ttt.move(2,1);
    assertTrue(ttt.isGameOver());

    // vertical win.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(0,1);
    ttt.move(1,0);
    ttt.move(0,2);
    ttt.move(2,0);
    assertTrue(ttt.isGameOver());
    assertEquals(Player.X, ttt.getWinner());

    // horizontal win.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(1,0);
    ttt.move(0,1);
    ttt.move(1,1);
    ttt.move(0,2);
    assertTrue(ttt.isGameOver());
    assertEquals(Player.X, ttt.getWinner());

    // diagonal LR win.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(0,1);
    ttt.move(1,1);
    ttt.move(0,2);
    ttt.move(2,2);
    assertTrue(ttt.isGameOver());
    assertEquals(Player.X, ttt.getWinner());

    // diagonal RL win.
    ttt = new TicTacToeModel();
    ttt.move(0,2);
    ttt.move(0,0);
    assertFalse(ttt.isGameOver());
    ttt.move(1,1);
    ttt.move(0,1);
    ttt.move(2,0);
    assertTrue(ttt.isGameOver());
    assertEquals(Player.X, ttt.getWinner());
  }

  @Test
  public void testGetWinner() {
    // Player O win.
    ttt.move(0,0);
    ttt.move(0,2);
    ttt.move(1,1);
    ttt.move(2,2);
    ttt.move(1,0);
    ttt.move(1,2);
    assertEquals(Player.O, ttt.getWinner());

    // Player X win.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(0,2);
    ttt.move(1,1);
    ttt.move(1,2);
    ttt.move(2,2);
    assertEquals(Player.X, ttt.getWinner());

    // Game ties.
    ttt = new TicTacToeModel();
    ttt.move(0,0);
    ttt.move(0,1);
    ttt.move(0,2);
    ttt.move(1,0);
    ttt.move(1,2);
    ttt.move(2,2);
    ttt.move(1,1);
    ttt.move(2,0);
    ttt.move(2,1);
    assertNull(ttt.getWinner());
    assertTrue(ttt.isGameOver());
  }

  @Test
  public void testGetBoard() {
    ttt.move(0,0);
    ttt.move(0,2);
    ttt.move(1,1);
    ttt.move(2,2);
    ttt.move(1,0);
    ttt.move(1,2);
    Player[][] board = new Player[3][3];
    board[0][0] = Player.X;
    board[0][1] = null;
    board[0][2] = Player.O;
    board[1][0] = Player.X;
    board[1][1] = Player.X;
    board[1][2] = Player.O;
    board[2][0] = null;
    board[2][1] = null;
    board[2][2] = Player.O;
    assertEquals(board, ttt.getBoard());
  }

  @Test
  public void testGetMarkAt() {
    assertNull(ttt.getMarkAt(0, 0));
    ttt.move(0, 0);
    assertEquals(Player.X, ttt.getMarkAt(0,0));
    ttt.move(0,1);
    assertEquals(Player.O, ttt.getMarkAt(0,1));
    ttt.move(0,2);
    assertNotEquals(Player.O, ttt.getMarkAt(0, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAt() {
    ttt.getMarkAt(-30, 1);
    ttt.move(0,0);
    ttt.move(0,1);
    ttt.move(0,2);
    assertEquals(Player.O, ttt.getMarkAt(0,1));
  }
}
