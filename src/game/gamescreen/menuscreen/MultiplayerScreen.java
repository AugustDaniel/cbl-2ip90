package game.gamescreen.menuscreen;

import game.Game;
import game.GameState;

import javax.swing.*;

public class MultiplayerScreen extends MenuScreen {

    private DefaultListModel<String> listModel;

    public MultiplayerScreen(Game game) {
        super(game);
        listModel = new DefaultListModel<>();
    }

    @Override
    protected void init() {
        JButton hostButton = new JButton("Host new game");
        JButton joinButton = new JButton("Join");
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");
        JList<String> lobbyList = new JList<>(listModel);

        hostButton.addActionListener(e -> {

        });

        joinButton.addActionListener(e -> {

        });

        refreshButton.addActionListener(e -> {
            refreshLobbies();
        });

        backButton.addActionListener(e -> {
            game.setState(GameState.MODE_SELECTION);
        });

        add(hostButton);
        add(joinButton);
        add(refreshButton);
        add(backButton);
        add(lobbyList);
    }

    private void refreshLobbies() {
        //TODO
    }

    @Override
    public void update() {

    }
}
