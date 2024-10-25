package game;

import javax.swing.*;

/**
 * Frame for the game.
 */
public class GameFrame extends JFrame {

    private Game game = new Game();

    /**
     * Constructor GameFrame.
     */
    public GameFrame() {
        setTitle("Tower Defense");
        game.start();
        add(game);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
