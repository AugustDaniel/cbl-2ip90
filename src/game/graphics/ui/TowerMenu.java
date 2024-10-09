package game.graphics.ui;

import game.GameManager;
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

        this.buttons.add(new GameButton((int) (width * (2 / 3.0)), height / 2, new Point2D.Double(), "Upgrade", () -> System.out.println("cool")));
        this.buttons.add(new GameButton((int) (width * (2 / 3.0)), height / 2, new Point2D.Double(), "Sell", () -> System.out.println("cool")));
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect((int) (position.getX() - width / 2.0), (int) (position.getY() - height - 15), width, height);

        g.setColor(Color.white);
        g.fillRect((int) (position.getX() - width / 2.0), (int) (position.getY() - height - 15), width, height);

        for (GameButton button : buttons) {
            button.draw(g);
        }
    }

    @Override
    public void update() {

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
