package game.npc.mobs;

import game.graphics.ui.HealthBar;
import game.npc.Npc;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Mob extends Npc {

    protected String name;
    protected int price;
    protected int damage;
    protected int health;
    protected int maxHealth;
    protected HealthBar healthBar;

    public Mob(Point2D position, String name, int price, int damage, int health) {
        super(position);
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.healthBar = new HealthBar(1, 40,5, new Point2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight()));
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) (this.position.getX() - image.getWidth() / 2), (int) (position.getY()  - image.getHeight() /2), null);
        healthBar.draw(g);
    }

    @Override
    public void update(List<? extends Npc> npcs) {
        this.healthBar.setHealthPercentage(getHealthPercentage());
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    public int getPrice() {
        return price;
    }

    public float getHealthPercentage() {
        return ((float) health / maxHealth);
    }

    public boolean isDead() {
        return health <= 0;
    }
}
