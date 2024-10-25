package game.gamescreen;

import game.Game;
import game.util.Updatable;
import java.awt.*;
import javax.swing.*;

/**
 * Abstract class that extends JPanel.
 * Can be used to create a screen in the game with the right dimensions.
 */
public abstract class GameScreen extends JPanel implements Updatable {
    protected Dimension size;
    protected final Game game;

    /**
     * Constructor GameScreen.
     * @param game game object
     */
    public GameScreen(Game game) {
        this.game = game;
        this.size = new Dimension(640, 512);
        setPanelSize();
    }

    private void setPanelSize() {
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
