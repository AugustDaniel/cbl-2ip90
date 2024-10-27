package game.gamescreen.menuscreen;

import game.Game;
import game.GameDifficulty;
import game.GameState;
import javax.swing.*;

/**
 * ModeScreen used as a menu screen.
 * Add the buttons used in the menu to the buttons ArrayList to initialise them
 * and add them to the menu.
 * By default, the buttons are centered in the middle.
 */
public class ModeScreen extends MenuScreen {
    /**
     * Constructor for ModeScreen.
     * @param game game object
     */
    public ModeScreen(Game game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        clear();

        JButton singlePlayerButton = new JButton("Start");
        JButton backButton = new JButton("Back");

        JComboBox<GameDifficulty> difficultySelector = new JComboBox<>();
        difficultySelector.addItem(GameDifficulty.EASY);
        difficultySelector.addItem(GameDifficulty.NORMAL);
        difficultySelector.addItem(GameDifficulty.HARD);
        difficultySelector.setSelectedItem(GameDifficulty.NORMAL);

        add(difficultySelector, constraints);

        buttons.add(singlePlayerButton);
        buttons.add(backButton);

        singlePlayerButton.addActionListener(e -> {
            game.setState(GameState.PLAYING);
            game.getGameManager()
                .setDifficulty((GameDifficulty) difficultySelector.getSelectedItem());
        });

        backButton.addActionListener(e -> {
            game.setState(GameState.MENU);
        });

        setUpButtons();
    }
}
