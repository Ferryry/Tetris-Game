/**
 * A simple functional interface to generate a new block.
 */
@FunctionalInterface
public interface BlockGenerator<T> {
  public T GenerateBlock();
}