import java.util.Iterator;
import java.util.Random;

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
   * Setup and starting method for the game.
   */
  private void start() {

    board = new Board(6, 10);
    block = generateNewBlock();

    isRunning = true;

    updateGameOutputRender(isRunning);
  }

  /**
   * Generates a new random block.
   */
  private Block generateNewBlock() {
    Block tetrisBlock = new Block();

    BlockGenerator<Block> blockGenerator = () -> {
      int random = new Random().nextInt(2);
      switch (random) {
        case 0:
          String[][] quadrat = new String[2][2];
          quadrat[0][0] = "X";
          quadrat[0][1] = "X";
          quadrat[1][0] = "X";
          quadrat[1][1] = "X";
          tetrisBlock.block = quadrat;
      
        default:
          String[][] rectangle = new String[2][4];
          rectangle[0][0] = "X";
          rectangle[0][1] = "X";
          rectangle[0][2] = "X";
          rectangle[0][3] = "X";
          rectangle[1][0] = "X";
          rectangle[1][1] = "X";
          rectangle[1][2] = "X";
          rectangle[1][3] = "X";
          tetrisBlock.block = rectangle;
      }
      return tetrisBlock;
    };

    return blockGenerator.GenerateBlock();
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