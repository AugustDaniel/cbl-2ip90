package game;

import javax.swing.*;

public class GameFrame extends JFrame {

    private Game game = new Game();

    public GameFrame() {
        setTitle("Tower Defense");
        game.start();
        add(game);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
