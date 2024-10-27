package game.gamescreen;

import game.Game;
import game.graphics.map.TileMap;
import game.graphics.ui.GameButton;
import game.graphics.ui.menu.buymenu.BuyMenu;
import game.npc.mobs.Mob;
import game.npc.towers.Tower;
import game.util.DefaultMouseListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PlayingScreen used as a GameScreen.
 * This screen will display the gameplay of the game and also detect input from the player.
 */
public class PlayingScreen extends GameScreen implements DefaultMouseListener {

    private final TileMap map;
    private final BuyMenu buyMenu;
    private final int buyMenuWidth = 128;
    private Tower draggedTower;
    private final GameButton quitButton;

    /**
     * Constructor PlayingScreen.
     * @param game game object
     */
    public PlayingScreen(Game game, TileMap map) {
        super(game);
        this.map = map;
        this.buyMenu = new BuyMenu(
            game.getGameManager(), 
            new Point2D.Double(size.getWidth() - buyMenuWidth, 0), buyMenuWidth, size.height);
        this.draggedTower = null;
        this.quitButton = new GameButton(50, 20, new Point2D.Double(1, 1), "Quit", this::quit);
        addMouseMotionListener(this);
        addMouseListener(this);
        game.getGameManager().start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        map.draw(g2d);
        buyMenu.draw(g2d);


        List<Mob> moblist = new ArrayList<>(game.getGameManager().getMobList());
        moblist.forEach(t -> t.draw(g2d));
        game.getGameManager().getTowerList().forEach(t -> t.draw(g2d));
        if (draggedTower != null) {
            draggedTower.draw(g2d);
        }

        quitButton.draw(g2d);
    }

    /**
     * updates the buyMenu.
     */
    @Override
    public void update() {
        buyMenu.update();
    }

    /**
     * Called whenever a mouse is pressed.
     * Will either call method for the buyMenu, placing of the tower, or detect a tower click.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point2D pos = e.getPoint();

        if (buyMenu.contains(pos)) {
            buyMenuClicked(pos);
            return;
        }

        if (draggedTower != null) {
            placeDraggedTower();
            return;
        }

        checkTowers(pos);
    }

    /**
     * Called whenever the mouse is clicked
     * Used to detect whether the quit button is clicked.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (quitButton.contains(e.getPoint())) {
            quitButton.callAction();
        }
    }

    private void checkTowers(Point2D position) {
        for (Tower tower : game.getGameManager().getTowerList()) {
            if (tower.contains(position)) {
                if (tower.toggleClicked()) {
                    addMouseListener(tower.getTowerMenu());
                } else {
                    removeMouseListener(tower.getTowerMenu());
                }

                return;
            }
        }
    }

    private void placeDraggedTower() {
        if (map.isFree(draggedTower.getPosition())) {
            for (Tower t: game.getGameManager().getTowerList()) {
                if (t.contains(draggedTower.getPosition())) {
                    draggedTower = null;
                    return;
                }
            }

            game.getGameManager().addTower(draggedTower);
        }

        draggedTower = null;
    }

    private void buyMenuClicked(Point2D position) {
        if (draggedTower != null) {
            draggedTower = null;
        }

        Optional<Tower> towerOptional = buyMenu.getSelected(position);
        towerOptional.ifPresent(tower -> {
            draggedTower = tower.copyOf();
            draggedTower.setClicked(true);
        });
    }

    /**
     * Called whenever the mouse is moved.
     * Will make it so the dragged tower follows the mouse.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (draggedTower != null) {
            draggedTower.setPosition(e.getPoint());
        }
    }

    private void quit() {
        game.getGameManager().endGame();
    }
}
