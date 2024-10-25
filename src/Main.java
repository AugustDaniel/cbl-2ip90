import game.GameFrame;
import javax.swing.*;

/**
 * Main class of the program.
 */
public class Main {

    /**
     * Main method.
     * @param args args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
