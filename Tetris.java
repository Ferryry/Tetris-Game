import java.util.Iterator;

public class Tetris {

  private boolean isRunning;
  private Board board;
  private Block block;

  private class ANSIIColor {
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLACK = "\u001B[30m";
  }

  private class Board implements Cloneable {
    private String[][] board;

    private void setSize(int width, int height) {
      board = new String[height][width];

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          board[i][j] = String.format("%1$s X %1$s", ANSIIColor.BLACK);
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

  private class Block implements Iterable<Block>, Cloneable {

    private ANSIIColor color;
    private String[][] block;
    private String name;
    private Block next;

    public String[][] getBlock() {
      return block;
    }

    public String getName() {
      return name;
    }

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

  public void start() {

    board = new Board(6, 10);

    isRunning = true;

    updateGameOutputRender(isRunning);
  }

  public void update() {

  }

  public boolean isRunning() {
    return isRunning;
  }

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

  public void run() {

    start();

    // while (isRunning()) {
    // update();
    // }
    return;
  }
}