package game.gamescreen;

import game.Game;
import game.graphics.map.Map;
import game.graphics.ui.buymenu.BuyMenu;
import game.npc.towers.Tower;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Optional;

public class PlayingScreen extends GameScreen implements MouseListener, MouseMotionListener {

    private Map map;
    private BuyMenu buyMenu;
    private final int BUY_MENU_WIDTH = 128;
    private Tower draggedTower;

    public PlayingScreen(Game game) {
        super(game);
        this.map = new Map();
        this.buyMenu = new BuyMenu(game.getGameManager(), new Point2D.Double(size.getWidth() - BUY_MENU_WIDTH, 0), BUY_MENU_WIDTH, size.height);
        this.draggedTower = null;
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        map.draw(g2d);
        buyMenu.draw(g2d);
    }

    @Override
    public void update() {
        buyMenu.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (buyMenu.contains(e.getPoint())) {
            Optional<Tower> towerOptional = buyMenu.getSelected(e.getPoint());

            towerOptional.ifPresent(tower -> draggedTower = tower.copyOf());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (draggedTower != null) {
            draggedTower.setPosition(e.getPoint());
        }
    }
}
