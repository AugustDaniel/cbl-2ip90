package game;

import game.gamescreen.*;
import game.gamescreen.menuscreen.MenuScreen;
import game.gamescreen.menuscreen.ModeScreen;
import game.gamescreen.menuscreen.SettingsScreen;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class Game extends JPanel implements Runnable {
    private GameState state;
    public Thread thread;
    private final EnumMap<GameState, GameScreen> statePanel;
    private final CardLayout cardLayout;
    private final GameManager gameManager;

    public Game() {
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

    public void init() {
        setLayout(cardLayout);
        statePanel.put(GameState.MENU, new MenuScreen(this));
        statePanel.put(GameState.PLAYING, new PlayingScreen(this));
        statePanel.put(GameState.MODE_SELECTION, new ModeScreen(this));
        statePanel.put(GameState.SETTINGS, new SettingsScreen(this));

        for (GameState gameState : statePanel.keySet()) {
            add(statePanel.get(gameState), gameState.name());
        }

        cardLayout.show(this, this.state.name());
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double FPS_SET = 120.0;
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();

        long lastUpdate = System.nanoTime();
        double UPS_SET = 60.0;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        int updates = 0;
        int frames = 0;
        long lastTimeCheck = System.currentTimeMillis();

        long now;
        while (true) {
            now = System.nanoTime();

            if (now - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                frames++;
                this.statePanel.get(this.state).repaint();
            }

            if (now - lastUpdate >= timePerUpdate) {
                if (state == GameState.PLAYING) {
                    updateGame();
                }

                updates++;
                lastUpdate = System.nanoTime();
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS" + frames + " | UPS: " + updates);
                updates = 0;
                frames = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    private void updateGame() {
        this.gameManager.update();

        for (GameScreen screen : statePanel.values()) {
            screen.update();
        }
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public void setState(GameState state) {
        this.state = state;
        SwingUtilities.invokeLater(() -> {
            cardLayout.show(this, this.state.name());
        });
    }
}
