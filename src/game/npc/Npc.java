package game.npc;

import game.GameManager;
import game.util.Drawable;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Abstract NPC class
 * Template for all NPCs to use with image and position.
 */
public abstract class Npc implements Drawable {

    protected Point2D position;
    protected BufferedImage image;
    protected double scale;
    protected GameManager manager;

    /**
     * Constructor NPC
     * @param position position
     * @param gameManager manager object
     */
    public Npc(Point2D position, GameManager gameManager) {
        this.position = position;
        this.scale = 0;
        this.manager = gameManager;
        initImage();
    }

    protected abstract void initImage();

    /**
     * abstract update
     * @param npcs npc list
     */
    public abstract void update(List<? extends Npc> npcs);

    /**
     * Get position
     * @return position
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Set position
     * @param position position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Checks whether a point is inside the NPC
     * @param point point to check
     * @return true if point is inside the npc false when not
     */
    public boolean contains(Point2D point) {
        Rectangle2D rectangle = new Rectangle2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight() / 2.0, image.getWidth(), image.getHeight());
        return rectangle.contains(point);
    }
}
