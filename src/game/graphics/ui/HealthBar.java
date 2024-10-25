package game.graphics.ui;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Health bar ui for mobs.
 */
public class HealthBar extends UIComponent {

    private float healthPercentage;

    /**
     * Constructor HealthBar.
     * @param healthPercentage percentage
     * @param width width
     * @param height height
     * @param position position
     */
    public HealthBar(float healthPercentage, int width, int height, Point2D position) {
        super(width, height, position);
        this.healthPercentage = healthPercentage;
    }

    /**
     * draws the health bar in the position.
     * @param g graphics object
     */
    @Override
    public void draw(Graphics2D g) {
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);

        g.setColor(Color.red);
        g.fillRect((int) position.getX(), 
            (int) position.getY(), (int) (width * healthPercentage), height);

        g.setColor(Color.black);
    }

    /**
     * set health percentage.
     * @param healthPercentage percentage to set
     */
    public void setHealthPercentage(float healthPercentage) {
        this.healthPercentage = healthPercentage;
    }

    /**
     * does nothing.
     */
    @Override
    public void update() {

    }
}
