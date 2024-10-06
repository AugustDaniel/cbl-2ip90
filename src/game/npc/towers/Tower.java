package game.npc.towers;

import game.npc.Npc;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Tower extends Npc implements Comparable<Tower> {

    private String name;
    private int damage;
    private int range;
    private int price;

    public Tower(Point2D position, String name, int damage, int range, int price) {
        super(position);
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform transform = g.getTransform();
        transform.translate(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight() / 2.0);
        g.drawImage(this.image, transform, null);
    }

    @Override
    public void update(List<? extends Npc> npcs) {

    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Tower o) {
        return Integer.compare(this.price, o.price);
    }

    public abstract Tower copyOf();
}
