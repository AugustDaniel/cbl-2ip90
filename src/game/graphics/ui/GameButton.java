package game.graphics.ui;

import game.util.ActionCallback;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * UIComponent that can be used as a button with an action callback.
 */
public class GameButton extends UIComponent implements ActionCallback {

    private String text;
    private ActionCallback callback;
    private boolean isClickable;

    /**
     * Constructor GameButton.
     * @param width width
     * @param height height
     * @param position position
     * @param text text
     * @param callback callback method
     */
    public GameButton(int width, 
            int height, 
            Point2D position, 
            String text, 
            ActionCallback callback) {
        super(width, height, position);
        this.text = text;
        this.callback = callback;
        this.isClickable = true;
    }

    /**
     * draws the button.
     * button will be white when clickable and gray when not.
     * @param g graphics object
     */
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
        g.drawString(text, (int) position.getX() + 10, (int) position.getY() + height / 2 + 5);
    }

    /**
     * does nothing.
     */
    @Override
    public void update() {

    }

    /**
     * set clickable.
     * @param isClickable clickable to set
     */
    public void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    /**
     * Calls the action callback if clickable.
     */
    @Override
    public void callAction() {
        if (isClickable) {
            callback.callAction();
        }
    }
}
