import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Implements the key adapter
 */
public class TAdapter extends KeyAdapter {
    private Board board;

    /**
     * Default constructor
     * @param board Board to consider
     */
    public TAdapter(Board board) {
        this.board = board;
    }

    /**
     * Overrides the keyPressed function
     * @param e key event to consider
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT) && (!board.getRightDir())) {
            board.setLeftDir(true);
            board.setUpDir(false);
            board.setDownDir(false);
        }
        if ((key == KeyEvent.VK_RIGHT) && (!board.getLeftDir())) {
            board.setRightDir(true);
            board.setUpDir(false);
            board.setDownDir(false);
        }
        if ((key == KeyEvent.VK_UP) && (!board.getDownDir())) {
            board.setUpDir(true);
            board.setRightDir(false);
            board.setLeftDir(false);
        }
        if ((key == KeyEvent.VK_DOWN) && (!board.getUpDir())) {
            board.setDownDir(true);
            board.setRightDir(false);
            board.setLeftDir(false);
        }
        if ((key == KeyEvent.VK_SPACE) && (!board.getInGame())) {
            board.restart();
        }
        if (key == KeyEvent.VK_Q) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }
}

