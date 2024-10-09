package game.graphics.ui;

import game.util.ActionCallback;

import java.awt.*;
import java.awt.geom.Point2D;

public class GameButton extends UIComponent implements ActionCallback {

    private String text;
    private ActionCallback callback;
    private boolean isClickable;

    public GameButton(int width, int height, Point2D position, String text, ActionCallback callback) {
        super(width, height, position);
        this.text = text;
        this.callback = callback;
        this.isClickable = true;
    }

    @Override
    public void draw(Graphics2D g) {
        if (isClickable) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.gray);
        }

        g.fillRect((int) position.getX(), (int) position.getY(), width, height);
        g.setColor(Color.black);
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);
        g.setColor(Color.black);
        g.drawString(text, (int) position.getX() + 10, (int) position.getY() + height / 2);
    }

    @Override
    public void update() {

    }

    public void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    @Override
    public void callAction() {
        if (isClickable) {
            callback.callAction();
        }
    }
}
