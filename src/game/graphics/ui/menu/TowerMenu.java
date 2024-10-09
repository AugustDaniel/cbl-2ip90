package game.graphics.ui.menu;

import game.GameManager;
import game.graphics.ui.GameButton;
import game.graphics.ui.UIComponent;
import game.npc.towers.Tower;
import game.util.DefaultMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TowerMenu extends UIComponent implements DefaultMouseListener {

    private Tower tower;
    private GameManager gameManager;
    private List<GameButton> buttons;

    public TowerMenu(GameManager gameManager, Tower tower, int width, int height, Point2D position) {
        super(width, height, position);
        this.gameManager = gameManager;
        this.tower = tower;
        this.buttons = new ArrayList<>();

        this.buttons.add(new GameButton((int) (width * (2 / 3.0)), height / 2, new Point2D.Double(), "Upgrade", this::upgrade));
        this.buttons.add(new GameButton((int) (width * (2 / 3.0)), height / 2, new Point2D.Double(), "Sell", this::sell));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect((int) (position.getX() - width / 2.0), (int) (position.getY() - height - 15), width, height);
        g.setColor(Color.black);
        g.drawRect((int) (position.getX() - width / 2.0), (int) (position.getY() - height - 15), width, height);

        for (GameButton button : buttons) {
            button.draw(g);
        }

        g.drawString(String.valueOf(tower.getUpgradePrice()),(int)(position.getX() + width * (1/4.0)), (int)(position.getY()  - height));
        g.drawString(String.valueOf(tower.getPrice()),(int)(position.getX() + width * (1/4.0)), (int)(position.getY()  - height / 2));
    }

    @Override
    public void update() {
        buttons.get(0).setClickable(gameManager.getPlayerMoney() >= tower.getUpgradePrice());
     }

    private void upgrade() {
        gameManager.upgradeTower(tower);
    }

    private void sell() {
        gameManager.sellTower(tower);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GameButton button : buttons) {
            if (button.contains(e.getPoint())) {
                button.callAction();
            }
        }
    }

    @Override
    public void setPosition(Point2D position) {
        super.setPosition(position);

        int x = (int) (position.getX() - width / 2.0);
        buttons.get(0).setPosition(new Point2D.Double(x, (int) (position.getY() - height - 15)));
        buttons.get(1).setPosition(new Point2D.Double(x, (int) (position.getY() - height / 2.0 - 15)));
    }
}
