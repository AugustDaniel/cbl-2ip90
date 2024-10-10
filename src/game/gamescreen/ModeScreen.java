package game.gamescreen;

import game.Game;
import game.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModeScreen extends MenuScreen{

    public ModeScreen(Game game) {
        super(game);
        init();
    }

    private void init() {
        clear();

        JButton singlePlayerButton = new JButton("Single-player");
        JButton multiPlayerPlayerButton = new JButton("Multi-player");
        JButton backButton = new JButton("Back");
        buttons.add(singlePlayerButton);
        buttons.add(multiPlayerPlayerButton);
        buttons.add(backButton);

        singlePlayerButton.addActionListener(e -> {
            game.setState(GameState.PLAYING);
        });

        multiPlayerPlayerButton.addActionListener(e -> {
            game.setState(GameState.MULTIPLAYER_LOBBY);
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
