package game.npc.mobs;

import game.GameManager;
import game.graphics.ui.HealthBar;
import game.npc.Npc;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Mob extends Npc {

    protected String name;
    protected int price;
    protected int damage;
    protected int health;
    protected int maxHealth;
    protected HealthBar healthBar;
    protected Point2D targetPosition;
    protected double speed;

    public Mob(GameManager manager, Point2D position, Point2D targetPosition, String name, int price, int damage, int health, double speed) {
        super(position, manager);
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.targetPosition = targetPosition;
        this.speed = speed;
        this.healthBar = new HealthBar(1, 40, 5, new Point2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight()));
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) (this.position.getX() - image.getWidth() / 2), (int) (position.getY() - image.getHeight() / 2), null);
        healthBar.draw(g);
    }

    @Override
    public void update(List<? extends Npc> npcs) {
        this.healthBar.setHealthPercentage(getHealthPercentage());

        double newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());

        double xDif = speed * Math.cos(newAngle);
        double yDif = speed * Math.sin(newAngle);

        this.position = new Point2D.Double(
                this.position.getX() + xDif,
                this.position.getY() + yDif
        );
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
