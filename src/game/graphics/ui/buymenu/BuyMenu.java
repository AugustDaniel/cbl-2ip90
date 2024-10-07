package game.graphics.ui.buymenu;

import game.GameManager;
import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuyMenu extends UIComponent {

    private final int ITEM_WIDTH = 64;
    private final int ITEM_HEIGHT = 64;
    private final int ITEM_SPACING = 10;
    private final GameManager gameManager;
    private List<BuyMenuItem> menuItems;

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
        int y = ITEM_SPACING;
        for (Tower tower : gameManager.getBuyableTowers()) {
            this.menuItems.add(new BuyMenuItem(tower, new Point2D.Double(position.getX() + (width / 2.0) - (ITEM_WIDTH / 2.0), position.getY() + y), ITEM_WIDTH, ITEM_HEIGHT));
            y += ITEM_HEIGHT + ITEM_SPACING;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.darkGray);
        Rectangle2D background = new Rectangle2D.Double(position.getX(), position.getY(), width, height);
        g.fill(background);

        g.setColor(Color.BLACK);
        for (BuyMenuItem item : menuItems) {
            item.draw(g);
        }
    }

    @Override
    public void update() {
        for (BuyMenuItem item : menuItems) {
            item.setBuyAble(item.getItem().getPrice() <= gameManager.getPlayerMoney());
        }
    }

    public Optional<Tower> getSelected(Point2D point) {
        for (BuyMenuItem item : menuItems) {
            if (item.contains(point) && item.isBuyAble()) {
                return Optional.of(item.getItem());
            }
        }

        return Optional.empty();
    }
}
