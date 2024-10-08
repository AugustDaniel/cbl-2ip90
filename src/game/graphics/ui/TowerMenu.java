package game.graphics.ui;

import game.GameManager;
import game.npc.towers.Tower;
import game.util.DefaultMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class TowerMenu extends UIComponent implements DefaultMouseListener {

    private Tower tower;
    private GameManager gameManager;

    public TowerMenu(GameManager gameManager, Tower tower, int width, int height, Point2D position) {
        super(width, height, position);
        this.gameManager = gameManager;
        this.tower = tower;
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect((int) (position.getX() - width / 2.0), (int) (position.getY() - height - 15), width, height);
    }

    @Override
    public void update() {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
