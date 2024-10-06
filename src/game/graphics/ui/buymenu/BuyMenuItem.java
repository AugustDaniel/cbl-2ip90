package game.graphics.ui.buymenu;

import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;

import java.awt.*;
import java.awt.geom.Point2D;

public class BuyMenuItem extends UIComponent {

    private boolean isBuyAble;
    private Tower tower;
    private Point2D position;

    public BuyMenuItem(Tower tower, Point2D position, int width, int height) {
        super(width, height, position);
        this.isBuyAble = false;
        this.tower = tower;
        this.position = position;
        init();
    }

    private void init() {
        tower.setPosition(new Point2D.Double((position.getX() + width / 2.0), position.getY() + height / 2.0));
    }

    public boolean isBuyAble() {
        return isBuyAble;
    }

    public void setBuyAble(boolean buyAble) {
        isBuyAble = buyAble;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect((int) position.getX(), (int) position.getY(), width, height);
        g.setColor(Color.black);
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);

        tower.draw(g);

        if (isBuyAble) {
            g.setColor(Color.green);
        } else {
            g.setColor(Color.red);
        }

        g.drawString(String.valueOf(tower.getPrice()), (int) position.getX() + (width / 2) - 8, (int) (position.getY()  + height));

        g.setColor(Color.black);
    }

    @Override
    public void update() {

    }
}
