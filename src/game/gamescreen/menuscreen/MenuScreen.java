package game.gamescreen.menuscreen;

import game.Game;
import game.GameState;
import game.gamescreen.GameScreen;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * GamesScreen used as a menu screen.
 * Add the buttons used in the menu to the buttons ArrayList to initialise them
 * and add them to the menu.
 * By default, the buttons are centered in the middle.
 */
public class MenuScreen extends GameScreen {

    protected List<JButton> buttons;
    protected GridBagConstraints constraints;

    /**
     * MenuScreen constructor.
     * @param game game object
     */
    public MenuScreen(Game game) {
        super(game);
        this.buttons = new ArrayList<>();
        constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.insets = new Insets(2, 0, 2, 0);
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        LinearGradientPaint paint = new LinearGradientPaint(
            new Point2D.Double(), 
            new Point2D.Double(size.getWidth(), size.getHeight()), 
            new float[]{0.25f, 0.50f, 0.75f, 1f}, 
            new Color[]{Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.WHITE});
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, size.width, size.height);
    }

    /**
     * Initialize the menu.
     * Make sure to call clear() in the beginning of the method.
     * Add buttons to the buttons list and use the setupButtons to initialize them.
     */
    protected void init() {
        clear();
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

    /**
     * Update.
     * Can be overridden by subclasses
     */
    @Override
    public void update() {

    }

    /**
     * Sets up the buttons in the buttons list.
     * Adds them to the menuscreen using the appropriate dimensions.
     */
    protected void setUpButtons() {
        Dimension buttonSize = new Dimension(150, 25);
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(buttonSize);
            add(button, constraints);
        }
    }

    /**
     * Clears all the buttons in the buttonlist.
     */
    protected void clear() {
        buttons.clear();
        removeAll();
    }
}
