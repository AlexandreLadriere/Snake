/**
 * Implements all constants
 */
public final class Constants {
    public static final int B_WIDTH = 300; // Board width
    public static final int B_HEIGHT = 300; // Board height
    public static final int DOT_SIZE = 10; // Dot size
    public static final int MAX_DOTS = (B_WIDTH * B_HEIGHT) / (DOT_SIZE * DOT_SIZE); // Maximum number of possible dots on the board
    public static final int RAND_POS = 29; // Used to compute the random position of an "apple"
    public static final int DELAY = 100; // Speed of the game
}
