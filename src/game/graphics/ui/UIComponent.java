package game.graphics.ui;

import game.Drawable;
import game.Updatable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class UIComponent implements Drawable, Updatable {

    protected int width;
    protected int height;
    protected Point2D position;

    public UIComponent(int width, int height, Point2D position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
