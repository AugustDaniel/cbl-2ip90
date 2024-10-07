package game.graphics.ui;

import java.awt.*;
import java.awt.geom.Point2D;

public class HealthBar extends UIComponent{

    private float healthPercentage;

    public HealthBar(float healthPercentage, int width, int height, Point2D position) {
        super(width, height, position);
        this.healthPercentage = healthPercentage;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);

        g.setColor(Color.red);
        g.fillRect((int) position.getX(), (int) position.getY(), (int) (width * healthPercentage), height);

        g.setColor(Color.black);
    }

    public void setHealthPercentage(float healthPercentage) {
        this.healthPercentage = healthPercentage;
    }

    @Override
    public void update() {

    }
}
