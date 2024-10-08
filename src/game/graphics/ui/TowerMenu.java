package game.graphics.ui;

import game.GameManager;
import game.npc.towers.Tower;

import java.awt.*;
import java.awt.geom.Point2D;

public class TowerMenu extends UIComponent {

    private Tower tower;
    private GameManager gameManager;

    public TowerMenu(GameManager gameManager, Tower tower, int width, int height, Point2D position) {
        super(width, height, position);
        this.gameManager = gameManager;
        this.tower = tower;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }


}
