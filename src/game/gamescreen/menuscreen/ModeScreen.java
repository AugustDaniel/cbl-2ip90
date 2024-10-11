package game.gamescreen.menuscreen;

import game.Game;
import game.GameDifficulty;
import game.GameState;

import javax.swing.*;

public class ModeScreen extends MenuScreen{

    public ModeScreen(Game game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        clear();

        JButton singlePlayerButton = new JButton("Single-player");
        JButton multiPlayerPlayerButton = new JButton("Multi-player");
        JButton backButton = new JButton("Back");

        JComboBox<GameDifficulty> difficultySelector = new JComboBox<>();
        difficultySelector.addItem(GameDifficulty.EASY);
        difficultySelector.addItem(GameDifficulty.NORMAL);
        difficultySelector.addItem(GameDifficulty.HARD);
        difficultySelector.setSelectedItem(GameDifficulty.NORMAL);

        add(difficultySelector, constraints);

        buttons.add(singlePlayerButton);
        buttons.add(multiPlayerPlayerButton);
        buttons.add(backButton);

        singlePlayerButton.addActionListener(e -> {
            game.setState(GameState.PLAYING);
            game.getGameManager().setDifficulty((GameDifficulty) difficultySelector.getSelectedItem());
        });

        multiPlayerPlayerButton.addActionListener(e -> {
            game.setState(GameState.MULTIPLAYER_LOBBY);
            game.getGameManager().setDifficulty((GameDifficulty) difficultySelector.getSelectedItem());
        });

        backButton.addActionListener(e -> {
            game.setState(GameState.MENU);
        });

        setUpButtons();
    }

    @Override
    public void update() {

    }
}
