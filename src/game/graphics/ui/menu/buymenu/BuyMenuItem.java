package game.graphics.ui.menu.buymenu;

import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Item for a tower buy menu.
 */
public class BuyMenuItem extends UIComponent {

    private boolean isBuyAble;
    private final Tower item;
    private Point2D position;

    /**
     * Constructor BuyMenuItem.
     * @param tower tower
     * @param position position
     * @param width width
     * @param height height
     */
    public BuyMenuItem(Tower tower, Point2D position, int width, int height) {
        super(width, height, position);
        this.isBuyAble = false;
        this.item = tower;
        this.position = position;
        init();
    }

    private void init() {
        item.setPosition(
            new Point2D.Double((position.getX() + width / 2.0), position.getY() + height / 2.0));
    }

    /**
     * is buyable.
     * @return whether tower is buyable
     */
    public boolean isBuyAble() {
        return isBuyAble;
    }

    /**
     * set buyable.
     * @param buyAble buyable to set
     */
    public void setBuyAble(boolean buyAble) {
        isBuyAble = buyAble;
    }

    /**
     * get item.
     * @return item
     */
    public Tower getItem() {
        return item;
    }

    /**
     * draws the buy menu item
     * the buy text will be green when buyable, red when not.
     * @param g graphics object
     */
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

        g.drawString(
            String.valueOf(item.getPrice()), 
            (int) position.getX() + (width / 2) - 8, 
            (int) (position.getY()  + height - 2));

        g.setColor(Color.black);
    }

    /**
     * does nothing.
     */
    @Override
    public void update() {

    }
}
