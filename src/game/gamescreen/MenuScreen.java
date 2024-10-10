package game.gamescreen;

import game.Game;
import game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends GameScreen {

    protected List<JButton> buttons;
    protected GridBagConstraints constraints;

    public MenuScreen(Game game) {
        super(game);
        this.buttons = new ArrayList<>();
        constraints = new GridBagConstraints();
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
        clear();
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.insets = new Insets(2,0,2,0);

        JButton playButton = new JButton("Play");
        JButton settingsButton = new JButton("Settings");
        JButton quitButton = new JButton("Quit");
        buttons.add(playButton);
        buttons.add(settingsButton);
        buttons.add(quitButton);

        setUpButtons();

        playButton.addActionListener(e -> {
            game.setState(GameState.MODE_SELECTION);
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        settingsButton.addActionListener(e -> {
            game.setState(GameState.SETTINGS);
        });
    }

    @Override
    public void update() {

    }

    protected void setUpButtons() {
        Dimension buttonSize = new Dimension(150, 25);
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(buttonSize);
            add(button, constraints);
        }
    }

    protected void clear() {
        buttons.clear();
        removeAll();
    }
}
