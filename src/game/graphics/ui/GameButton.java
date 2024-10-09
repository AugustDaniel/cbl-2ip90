package game.graphics.ui;

import game.util.ActionCallback;

import java.awt.*;
import java.awt.geom.Point2D;

public class GameButton extends UIComponent implements ActionCallback {

    private String text;
    private ActionCallback callback;

    public GameButton(int width, int height, Point2D position, String text, ActionCallback callback) {
        super(width, height, position);
        this.text = text;
        this.callback = callback;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);
        g.drawString(text, (int) position.getX(), (int) position.getY());
    }

    @Override
    public void update() {

    }


    @Override
    public void callAction() {
        callback.callAction();
    }
}
