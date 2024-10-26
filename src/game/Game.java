package game;

import game.gamescreen.GameScreen;
import game.gamescreen.PlayingScreen;
import game.gamescreen.menuscreen.GameOverScreen;
import game.gamescreen.menuscreen.MenuScreen;
import game.gamescreen.menuscreen.ModeScreen;
import game.gamescreen.menuscreen.SettingsScreen;
import game.graphics.map.TileMap;
import java.awt.*;
import java.util.EnumMap;
import javax.swing.*;

/**
 * Game object.
 * Houses the game thread and will display the appropriate GameScreen.
 * Will initialise the gameManager.
 */
public class Game extends JPanel implements Runnable {
    private GameState state;
    public Thread thread;
    private final EnumMap<GameState, GameScreen> statePanel;
    private final CardLayout cardLayout;
    private final GameManager gameManager;
    private final TileMap map;

    /**
     * Constructor Game.
     */
    public Game() {
        this.map = new TileMap();
        this.state = GameState.MENU;
        this.gameManager = new GameManager(this);
        this.statePanel = new EnumMap<>(GameState.class);
        this.cardLayout = new CardLayout();
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void init() {
        setLayout(cardLayout);
        statePanel.put(GameState.MENU, new MenuScreen(this));
        statePanel.put(GameState.PLAYING, new PlayingScreen(this, map));
        statePanel.put(GameState.MODE_SELECTION, new ModeScreen(this));
        statePanel.put(GameState.SETTINGS, new SettingsScreen(this));
        statePanel.put(GameState.GAME_OVER, new GameOverScreen(this));

        for (GameState gameState : statePanel.keySet()) {
            add(statePanel.get(gameState), gameState.name());
        }

        cardLayout.show(this, this.state.name());
    }

    /**
     * Starts the game thread.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Will update and paint the game a certain amount of times per second.
     * Repainting is done 120 times per second.
     * updating is done 60 times per second.
     */
    @Override
    public void run() {
        double fpsSet = 120.0;
        double timePerFrame = 1000000000.0 / fpsSet;
        long lastFrame = System.nanoTime();

        long lastUpdate = System.nanoTime();
        double upsSet = 60.0;
        double timePerUpdate = 1000000000.0 / upsSet;

        long now;
        while (true) {
            now = System.nanoTime();

            if (now - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                this.statePanel.get(this.state).repaint();
            }

            if (now - lastUpdate >= timePerUpdate) {
                if (state == GameState.PLAYING) {
                    updateGame();
                }

                lastUpdate = System.nanoTime();
            }
        }
    }

    private void updateGame() {
        this.gameManager.update();

        for (GameScreen screen : statePanel.values()) {
            screen.update();
        }
    }

    /**
     * Get game manager.
     * @return gameManager
     */
    public GameManager getGameManager() {
        return this.gameManager;
    }

    /**
     * Sets the state of the game.
     * Will show the appropriate GameScreen.
     * @param state GameState
     */
    public void setState(GameState state) {
        this.state = state;

        if (state == GameState.GAME_OVER) {
            System.out.println(gameManager.getWaveCounter());
            ((GameOverScreen) (statePanel.get(state))).setWaveCounter(gameManager.getWaveCounter());
        }

        SwingUtilities.invokeLater(() -> {
            cardLayout.show(this, this.state.name());
        });
    }
}
