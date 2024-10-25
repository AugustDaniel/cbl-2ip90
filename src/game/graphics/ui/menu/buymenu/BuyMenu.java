package game.graphics.ui.menu.buymenu;

import game.GameManager;
import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * BuyMenu used as a UIComponent.
 * Contains the buyAble towers as menu items and also displays
 * the health and money of the player.
 */
public class BuyMenu extends UIComponent {

    private final int itemWidth = 64;
    private final int itemHeigth = 64;
    private final int itemSpacing = 10;
    private final GameManager gameManager;
    private final List<BuyMenuItem> menuItems;

    /**
     * Constructor BuyMenu.
     * @param gameManager gameManager object
     * @param position position of the upper left corner
     * @param width width of the menu
     * @param height height of the menu
     */
    public BuyMenu(GameManager gameManager, Point2D position, int width, int height) {
        super(width, height, position);
        this.gameManager = gameManager;
        this.position = position;
        this.menuItems = new ArrayList<>();
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {
        int y = itemSpacing;
        for (Tower tower : gameManager.getBuyableTowers()) {
            this.menuItems.add(
                new BuyMenuItem(tower, 
                    new Point2D.Double(
                        position.getX() + (width / 2.0) - (itemWidth / 2.0), 
                        position.getY() + y), itemWidth, itemHeigth));
            y += itemHeigth + itemSpacing;
        }
    }

    /**
     * draws the menu and all the menu items along with the health and money of the player.
     * @param g graphics object
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.darkGray);
        Rectangle2D background = new Rectangle2D.Double(
            position.getX(), 
            position.getY(), 
            width, 
            height);
        g.fill(background);

        g.setColor(Color.BLACK);
        for (BuyMenuItem item : menuItems) {
            item.draw(g);
        }

        g.setColor(Color.white);
        g.drawString("Health :" + gameManager.getPlayerHealth(), 
            (int) position.getX() + itemSpacing, 
            (int) (position.getY() + height - itemSpacing));
        g.drawString("Money " + gameManager.getPlayerMoney(), 
            (int) position.getX() + itemSpacing, 
            (int) (position.getY() + height - 2 * itemSpacing));
        g.drawString("Wave " + gameManager.getWaveCounter(), 
            (int) position.getX() + itemSpacing, 
            (int) (position.getY() + height - 3 * itemSpacing));
        g.setColor(Color.black);
    }

    /**
     * checks whether the money of the player is enough to but a tower.
     * And changed the color to red if not buyAble and green when it is.
     */
    @Override
    public void update() {
        for (BuyMenuItem item : menuItems) {
            item.setBuyAble(item.getItem().getPrice() <= gameManager.getPlayerMoney());
        }
    }

    /**
     * returns optional with tower that was clicked in the buyMenu.
     * @param point point where clicked
     * @return Optional with clicked tower
     */
    public Optional<Tower> getSelected(Point2D point) {
        for (BuyMenuItem item : menuItems) {
            if (item.contains(point) && item.isBuyAble()) {
                return Optional.of(item.getItem());
            }
        }

        return Optional.empty();
    }
}
