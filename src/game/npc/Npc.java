package game.npc;

import game.GameManager;
import game.util.Drawable;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class Npc implements Drawable {

    protected Point2D position;
    protected BufferedImage image;
    protected double scale;
    protected GameManager manager;

    public Npc(Point2D position, GameManager gameManager) {
        this.position = position;
        this.scale = 0;
        this.manager = gameManager;
        initImage();
    }

    protected abstract void initImage();

    public abstract void update(List<? extends Npc> npcs);

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getImageSize() {
        return this.image.getWidth(); //TODO scale
    }

    public boolean contains(Point2D point) {
        Rectangle2D rectangle = new Rectangle2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight() / 2.0, image.getWidth(), image.getHeight());
        return rectangle.contains(point);
    }
}
