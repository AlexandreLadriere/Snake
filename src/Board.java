import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the board of the game
 */
public class Board extends JPanel implements ActionListener {

    private final int x[] = new int[Constants.MAX_DOTS]; // x coordinates of the snake body
    private final int y[] = new int[Constants.MAX_DOTS]; // coordinates of the snake body

    private int dots; // Number of dots in the snake
    private int apple_x; // x position of the apple on the board
    private int apple_y; // y position of the apple on the board

    private boolean leftDir = false;
    private boolean rightDir = true;
    private boolean upDir = false;
    private boolean downDir = false;
    private boolean inGame = true;

    private Timer timer;
    private Image square; // Snake body image
    private Image apple; // Apple image
    private Image head; // Snake head image

    /**
     * Default constructor
     */
    public Board() {
        initBoard();
    }

    /**
     * Initializes the board
     */
    private void initBoard() {
        addKeyListener(new TAdapter(this));
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.B_WIDTH, Constants.B_HEIGHT));
        loadImages();
        initGame();
    }

    /**
     * Overrides the paintComponent function
     * @param g Graphics you want to "draw"
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Draws the appropriates drawings
     * @param g Graphics to update
     */
    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < dots ; i++) {
                // draw the head
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                }
                else {
                    g.drawImage(square, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameOver(g);
        }
    }

    /**
     * Display the game over screen
     * @param g Graphics to update
     */
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Open Sans", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Constants.B_WIDTH - metr.stringWidth(msg)) / 2, Constants.B_HEIGHT / 2);
    }

    /**
     * Loads all images needed
     */
    private void loadImages() {
        ImageIcon iisquare = new ImageIcon("src/resources/square.png");
        square = iisquare.getImage();
        ImageIcon iiapple = new ImageIcon("src/resources/apple.png");
        apple = iiapple.getImage();
        ImageIcon iihead = new ImageIcon("src/resources/head.png");
        head = iihead.getImage();
    }

    /**
     * Initializes the game (creates the snake, places the apple and starts the timer)
     */
    private void initGame() {
        dots = 3; // Length of the snake when the game starts
        for (int i = 0; i < dots; i++) {
            x[i] = (Constants.B_WIDTH/6) - i * Constants.DOT_SIZE;
            y[i] = (Constants.B_WIDTH/6);
        }
        locateApple();
        timer = new Timer(Constants.DELAY, this);
        timer.start();
    }

    /**
     * Checks if the snake ate the apple
     */
    private void checkApple() {
        if(x[0] == apple_x && y[0] == apple_y) {
            dots++;
            locateApple();
        }
    }

    /**
     * Randomly places the apple on the board
     */
    private void locateApple() {
        int r = (int) (Math.random() * Constants.RAND_POS);
        apple_x = ((r * Constants.DOT_SIZE));
        r = (int) (Math.random() * Constants.RAND_POS);
        apple_y = ((r * Constants.DOT_SIZE));
    }

    /**
     * Moves the snake according to the direction
     */
    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[(i-1)];
            y[i] = y[(i-1)];
        }
        // x axis: left to right
        // y axis: top to down
        if (leftDir) {
            x[0] -= Constants.DOT_SIZE;
        }
        if(rightDir) {
            x[0] += Constants.DOT_SIZE;
        }
        if (upDir) {
            y[0] -= Constants.DOT_SIZE;
        }
        if (downDir) {
            y[0] += Constants.DOT_SIZE;
        }
    }

    /**
     * Checks if the snake ate itself or the border
     */
    private void checkCollision() {
        // Check if the snake ate itself
        for (int i = dots; i > 0; i--) {
            if ( (i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        // Check if the snake "ate" the border
        if (y[0] >= Constants.B_HEIGHT) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= Constants.B_WIDTH) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        // Stop the timer if game lost
        if (!inGame) {
            timer.stop();
        }
    }

    /**
     * Overrides actionPerformed function
     * @param actionEvent action event to consider
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    /**
     * Gets the "leftDir" parameter
     * @return boolean (true if leftDir, else false)
     */
    public boolean getLeftDir() {
        return leftDir;
    }

    /**
     * Sets the "leftDir" parameter
     * @param leftDir boolean (true if leftDir, else false)
     */
    public void setLeftDir(boolean leftDir) {
        this.leftDir = leftDir;
    }

    /**
     * Gets the "rightDir" parameter
     * @return boolean (true if rightDir, else false)
     */
    public boolean getRightDir() {
        return rightDir;
    }

    /**
     * Sets the "rightDir" parameter
     * @param rightDir boolean (true if rightDir, else false)
     */
    public void setRightDir(boolean rightDir) {
        this.rightDir = rightDir;
    }

    /**
     * Gets the "upDir" parameter
     * @return boolean (true if upDir, else false)
     */
    public boolean getUpDir() {
        return upDir;
    }

    /**
     * Sets the "upDir" parameter
     * @param upDir boolean (true if upDir, else false)
     */
    public void setUpDir(boolean upDir) {
        this.upDir = upDir;
    }

    /**
     * Gets the "downDir" parameter
     * @return boolean (true if downDir, else false)
     */
    public boolean getDownDir() {
        return downDir;
    }

    /**
     * Sets the "downDir" parameter
     * @param downDir boolean (true if downDir, else false)
     */
    public void setDownDir(boolean downDir) {
        this.downDir = downDir;
    }
}
