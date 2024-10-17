package game.gamescreen;

import game.Game;
import game.GameState;
import game.graphics.map.TileMap;
import game.graphics.ui.GameButton;
import game.graphics.ui.menu.TowerMenu;
import game.graphics.ui.menu.buymenu.BuyMenu;
import game.npc.towers.Tower;
import game.util.DefaultMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Optional;

public class PlayingScreen extends GameScreen implements DefaultMouseListener {

    private TileMap map;
    private BuyMenu buyMenu;
    private final int BUY_MENU_WIDTH = 128;
    private Tower draggedTower;
    private GameButton quitButton;

    public PlayingScreen(Game game) {
        super(game);
        this.map = new TileMap();
        this.buyMenu = new BuyMenu(game.getGameManager(), new Point2D.Double(size.getWidth() - BUY_MENU_WIDTH, 0), BUY_MENU_WIDTH, size.height);
        this.draggedTower = null;
        this.quitButton = new GameButton(50, 20, new Point2D.Double(1,1), "Quit", this::quit);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        map.draw(g2d);
        buyMenu.draw(g2d);


        game.getGameManager().getMobList().forEach(t -> t.draw(g2d)); // TODO maybe combine with towers
        game.getGameManager().getTowerList().forEach(t -> t.draw(g2d)); //TODO maybe make better
        if (draggedTower != null) {
            draggedTower.draw(g2d);
        }

        quitButton.draw(g2d);
    }

    @Override
    public void update() {
        buyMenu.update();
    }

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

    @Override
    public void mouseMoved(MouseEvent e) {
        if (draggedTower != null) {
            draggedTower.setPosition(e.getPoint());
        }
    }

    private void quit() {
        game.setState(GameState.MENU);
        game.getGameManager().endGame();
    }
}
