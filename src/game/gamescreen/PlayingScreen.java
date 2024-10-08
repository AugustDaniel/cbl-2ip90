package game.gamescreen;

import game.Game;
import game.graphics.map.TileMap;
import game.graphics.ui.buymenu.BuyMenu;
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

    public PlayingScreen(Game game) {
        super(game);
        this.map = new TileMap();
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


        game.getGameManager().getTowerList().forEach(t -> t.draw(g2d)); //TODO maybe make better
        game.getGameManager().getMobList().forEach(t -> t.draw(g2d)); // TODO maybe combine with towers
        if (draggedTower != null) {
            draggedTower.draw(g2d);
        }
    }

    @Override
    public void update() {
        buyMenu.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (buyMenu.contains(e.getPoint())) {
            if (draggedTower != null) {
                draggedTower = null;
            }

            Optional<Tower> towerOptional = buyMenu.getSelected(e.getPoint());
            towerOptional.ifPresent(tower -> {
                draggedTower = tower.copyOf();
                draggedTower.setClicked(true);
            });
            return;
        }

        if (draggedTower != null) {
            if (map.isFree(draggedTower.getPosition())) {
                game.getGameManager().addTower(draggedTower);
            }

            draggedTower = null;
            return;
        }

        for (Tower tower : game.getGameManager().getTowerList()) {
            if (tower.contains(e.getPoint())) {
                if (tower.toggleClicked()) {
                    addMouseListener(tower.getTowerMenu());
                } else {
                    removeMouseListener(tower.getTowerMenu());
                }

                return;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (draggedTower != null) {
            draggedTower.setPosition(e.getPoint());
        }
    }
}
