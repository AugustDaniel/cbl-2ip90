package game.gamescreen;

import game.Game;
import game.Updatable;

import javax.swing.*;
import java.awt.*;

public abstract class GameScreen extends JPanel implements Updatable {
    protected Dimension size;
    protected final Game game;

    public GameScreen(Game game) {
        this.game = game;
        this.size = new Dimension(640, 640);
        setPanelSize();
    }

    private void setPanelSize() {
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
