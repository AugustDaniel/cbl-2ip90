package game.gamescreen;

import game.Game;
import game.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends GameScreen {

    private List<JButton> buttons;

    public MenuScreen(Game game) {
        super(game);
        this.buttons = new ArrayList<>();
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");
        JButton settingsButton = new JButton("Settings");
        buttons.add(playButton);
        buttons.add(quitButton);
        buttons.add(settingsButton);

        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentY(Component.CENTER_ALIGNMENT);
        }

        add(playButton);
        add(settingsButton);
        add(quitButton);



        playButton.addActionListener(e -> {
            game.setState(GameState.PLAYING);
        });

        quitButton.addActionListener(e -> {

        });

        settingsButton.addActionListener(e -> {

        });
    }

    @Override
    public void update() {

    }
}
