package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Model class for Tic-Tac-Toe.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private final Player[][] board;
  private Player turn;
  private Player winner;
  private boolean gameOver;
  private int moves;

  /**
   * Constructor for Tic Tac Toe Model class.
   */
  public TicTacToeModel() {
    board  = new Player[3][3];
    turn  = Player.X;
    winner  = null;
    gameOver = false;
    moves = 3 * 3;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("GAME OVER. Additional moves are not allowed.");
    } else if (r < 0 || r > 2 || c < 0 || c > 2 ) {
      throw new IllegalArgumentException("Move Not Allowed. Invalid position.");
    } else if (board[r][c] != null) {
      throw new IllegalArgumentException("Board position already filled.");
    } else {
      board[r][c] = turn;
      updateGame(r, c);
    }
  }

  private void updateGame(int r, int c) {
    int row = 0;
    int column = 0;
    int diagonal_LR = 0; // diagonal from left top to right bottom.
    int diagonal_RL = 0; // diagonal from right top to left bottom.
    moves -= 1;

    for (int i = 0; i < 3; i++) {
      if (board[r][i] == turn) {
        row += 1;
      }
      if (board[i][c] == turn) {
        column += 1;
      }
      if (board[i][i] == turn) {
        diagonal_LR += 1;
      }
      if (board[i][2 - i] == turn)  {
        diagonal_RL += 1;
      }
    }

    if (row == 3 || column == 3 || diagonal_LR == 3 || diagonal_RL == 3) {
      winner = turn;
    }

    if (winner != null || moves == 0) {
      gameOver = true;
    }

    if (turn == Player.X) {
      turn = Player.O;
    } else {
      turn = Player.X;
    }
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override
  public Player getTurn() {
    return turn;
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or
   * one player has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not
   * over, returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {
    return this.winner;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override
  public Player[][] getBoard() {
    Player[][] copy = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, 3);
    }
    return copy;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return this.board[r][c];
  }
}
