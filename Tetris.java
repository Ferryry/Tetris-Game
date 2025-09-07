import java.util.Iterator;

public class Tetris {

  private boolean isRunning;
  private Board board;
  private Block block;

  /**
   * ANSI color table for the game.
   */
  private class ANSIColor {
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLACK = "\u001B[30m";
  }

  /**
   * The game's board.
   */
  private class Board implements Cloneable {
    private String[][] board;

    private void setSize(int width, int height) {
      board = new String[height][width];

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          board[i][j] = String.format("%1$s X %1$s", ANSIColor.BLACK);
        }
      }
    }

    public String[][] getBoard() {
      return board;
    }

    public Board(int width, int height) {
      setSize(width, height);
    }
  }

  /**
   * Tetris block class that contains color, block color, block shape, realized as a linked-list.
   */
  private class Block implements Iterable<Block>, Cloneable {

    private ANSIColor color;
    private String[][] block;
    private String name;
    private Block next;

    /**
     * Gets the block's shape.
     * @return The block's shape as an array.
     */
    public String[][] getBlock() {
      return block;
    }

    /**
     * Gets the block's internal name.
     * @return The name.
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the next block.
     * @return Next block.
     */
    public Block getNextBlock() {
      return next;
    }

    @Override
    public Iterator<Tetris.Block> iterator() {
      return new Iterator<Tetris.Block>() {
        private Block current = Block.this;

        @Override
        public boolean hasNext() {
          return current != null;
        }

        @Override
        public Block next() {
          Block ret = current;
          current = current.next;
          return ret;
        }
      };
    }
  }

  /**
   * Setup and starting method for the game
   */
  private void start() {

    board = new Board(6, 10);

    isRunning = true;

    updateGameOutputRender(isRunning);
  }

  /**
   * Here's the game logic such as game input, behaviour and board check ups.
   */
  public void update() {

  }

  /**
   * Returns the current state of the game.
   * @return The state as boolean.
   */
  public boolean isRunning() {
    return isRunning;
  }

  /**
   * Renders the entire game in the current state.
   * @param boardOnly If true only the board is being displayed, otherwise other information will be displayed too.
   */
  private void updateGameOutputRender(boolean boardOnly) {

    System.out.flush();

    for (int i = 0; i < board.getBoard().length; i++) {
      for (int j = 0; j < board.getBoard()[i].length; j++) {
        System.out.print(board.getBoard()[i][j]);
      }
      System.out.println();
    }

    if (boardOnly) {
      return;
    }

  }

  /**
   * Main game loop.
   */
  public void run() {

    start();

    // while (isRunning()) {
    // update();
    // }
    return;
  }
}