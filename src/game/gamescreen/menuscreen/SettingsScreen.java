package game.gamescreen.menuscreen;

import game.Game;
import game.GameState;

import javax.swing.*;

/**
 * SettingScreen used as a menu screen.
 * Add the buttons used in the menu to the buttons ArrayList to initialise them
 * and add them to the menu.
 * By default, the buttons are centered in the middle.
 */
public class SettingsScreen extends MenuScreen {

    /**
     * Constructor SettingsScreen
     * @param game game object
     */
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

    /**
     * Update does nothing
     */
    @Override
    public void update() {

    }
}
