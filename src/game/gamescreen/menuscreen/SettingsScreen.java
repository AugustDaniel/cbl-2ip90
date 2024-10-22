package game.gamescreen.menuscreen;

import game.Game;
import game.GameState;
import game.gamescreen.GameScreen;

import javax.swing.*;

public class SettingsScreen extends MenuScreen {

    public SettingsScreen(Game game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        clear();
        String soundOn = game.getGameManager().isSoundOn() ? "On" : "Off";
        JButton soundToggle = new JButton("Sound: " + soundOn);
        JButton back = new JButton("Back");

        buttons.add(soundToggle);
        buttons.add(back);

        back.addActionListener(e -> {
            game.setState(GameState.MENU);
        });

        soundToggle.addActionListener(e -> {
            game.getGameManager().toggleSoundOn();
            String text = game.getGameManager().isSoundOn() ? "On" : "Off";
            buttons.get(0).setText("Sound: " + text);
        });

        setUpButtons();
    }

    @Override
    public void update() {

    }
}
