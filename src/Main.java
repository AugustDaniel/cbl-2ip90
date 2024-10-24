import game.GameFrame;

import javax.swing.*;

public class Main {

    /**
     * Main method
     * @param args args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
