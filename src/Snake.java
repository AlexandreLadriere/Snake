import javax.swing.*;
import java.awt.*;

/**
 * Main class
 */
public class Snake extends JFrame {
    /**
     * Default constructor
     */
    public Snake() {
        initUI();
    }

    /**
     * Initializes the UI
     */
    private void initUI() {
        add(new Board());
        setResizable(false);
        pack();
        setTitle("Snake - Alexandre Ladrière");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * main
     * @param args arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame w = new Snake();
            w.setVisible(true);
        });
    }
}
