package game.npc.mobs;

import game.npc.Npc;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Mob extends Npc {

    private String name;
    private int price;
    private int damage;
    private int health;

    public Mob(Point2D position, String name, int price, int damage, int health) {
        super(position);
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.health = health;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform transform = g.getTransform();
        transform.translate(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight() / 2.0);
        g.drawImage(this.image, transform, null);
    }

    public boolean damage(int damage) {
        this.health -= damage;

        return health <= 0;
    }
}
