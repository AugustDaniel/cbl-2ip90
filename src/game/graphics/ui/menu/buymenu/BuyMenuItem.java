package game.graphics.ui.menu.buymenu;

import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;

import java.awt.*;
import java.awt.geom.Point2D;

public class BuyMenuItem extends UIComponent {

    private boolean isBuyAble;
    private Tower item;
    private Point2D position;

    public BuyMenuItem(Tower tower, Point2D position, int width, int height) {
        super(width, height, position);
        this.isBuyAble = false;
        this.item = tower;
        this.position = position;
        init();
    }

    private void init() {
        item.setPosition(new Point2D.Double((position.getX() + width / 2.0), position.getY() + height / 2.0));
    }

    public boolean isBuyAble() {
        return isBuyAble;
    }

    public void setBuyAble(boolean buyAble) {
        isBuyAble = buyAble;
    }

    public Tower getItem() {
        return item;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect((int) position.getX(), (int) position.getY(), width, height);
        g.setColor(Color.black);
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);

        item.draw(g);

        if (isBuyAble) {
            g.setColor(Color.green);
        } else {
            g.setColor(Color.red);
        }

        g.drawString(String.valueOf(item.getPrice()), (int) position.getX() + (width / 2) - 8, (int) (position.getY()  + height));

        g.setColor(Color.black);
    }

    @Override
    public void update() {

    }
}
