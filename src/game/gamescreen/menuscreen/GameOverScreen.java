package game.gamescreen.menuscreen;

import game.Game;
import game.GameState;
import java.awt.*;
import javax.swing.*;

/**
 * A game over screen with a wave counter and button to return to the main menu.
 */
public class GameOverScreen extends MenuScreen {

    private JLabel waveCounter;

    /**
     * Constructor GameScreen.
     *
     * @param game game object
     */
    public GameOverScreen(Game game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        clear();

        JLabel gameOver = new JLabel("GAME OVER");
        gameOver.setFont(new Font("Times New Roman", Font.BOLD, 30));
        gameOver.setForeground(Color.RED);

        add(gameOver, constraints);

        waveCounter = new JLabel("Waves survived: " + game.getGameManager().getWaveCounter());
        waveCounter.setFont(new Font("Times New Roman", Font.BOLD, 20));
        waveCounter.setForeground(Color.white);
        add(waveCounter, constraints);

        JButton back = new JButton("Return to menu");

        buttons.add(back);

        back.addActionListener(l -> {
            game.setState(GameState.MENU);
        });

        setUpButtons();
    }

    /**
     * sets wave counter to be displayed.
     * @param counter counter
     */
    public void setWaveCounter(int counter) {
        waveCounter.setText("Waves survived: " + counter);
    }
}
