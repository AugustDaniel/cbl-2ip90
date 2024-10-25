package game.graphics.ui;

import game.util.Drawable;
import game.util.Updatable;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Abstract class UIComponent
 * Can be used to extend for all components in the ui that need a template.
 */
public abstract class UIComponent implements Drawable, Updatable {

    protected int width;
    protected int height;
    protected Point2D position;

    /**
     * Constructor UIComponent.
     * @param width width
     * @param height height
     * @param position position of upper left corner
     */
    public UIComponent(int width, int height, Point2D position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    /**
     * get width.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * set width.
     * @param width width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * get height.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * set height.
     * @param height height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * get position.
     * @return position upper left corner
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * set position.
     * @param position position to set
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Checks if point is within the UIComponent.
     * @param point point
     * @return true if point is inside false if not
     */
    public boolean contains(Point2D point) {
        return new Rectangle2D.Double(
            position.getX(), position.getY(), width, height).contains(point);
    }
}
