package game.gamescreen;

import game.Game;
import game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends GameScreen {

    private List<JButton> buttons;

    public MenuScreen(Game game) {
        super(game);
        this.buttons = new ArrayList<>();
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        LinearGradientPaint paint = new LinearGradientPaint(new Point2D.Double(), new Point2D.Double(size.getWidth(), size.getHeight()), new float[]{0.25f, 0.50f, 0.75f, 1f}, new Color[]{Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.WHITE});
        g2d.setPaint(paint);
        g2d.fillRect(0,0, size.width, size.height);
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(2,0,2,0);

        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");
        JButton settingsButton = new JButton("Settings");
        buttons.add(playButton);
        buttons.add(quitButton);
        buttons.add(settingsButton);

        Dimension buttonSize = new Dimension(100, 25);
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(buttonSize);
        }

        add(playButton, constraints);
        add(settingsButton, constraints);
        add(quitButton, constraints);

        playButton.addActionListener(e -> {
            game.setState(GameState.PLAYING);
        });

        quitButton.addActionListener(e -> {
            //TODO
        });

        settingsButton.addActionListener(e -> {
            //TODO
        });
    }

    @Override
    public void update() {

    }
}
