package game.npc.mobs;

import game.GameManager;
import game.graphics.ui.HealthBar;
import game.npc.Npc;
import game.pathfinding.Vertex;

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
    protected Vertex currentNode;

    public Mob(GameManager manager, Point2D position, Point2D targetPosition, String name, int price, int damage, int health, double speed, Vertex currentNode) {
        super(position, manager);
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.targetPosition = targetPosition;
        this.speed = speed;
        this.currentNode = currentNode;
        this.healthBar = new HealthBar(1, 40, 5, new Point2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight()));
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) (this.position.getX() - image.getWidth() / 2), (int) (position.getY() - image.getHeight() / 2), null);
        healthBar.draw(g);
    }

    @Override
    public void update(List<? extends Npc> npcs) {
        this.healthBar.setHealthPercentage(getHealthPercentage());

        if (isAtTargetPosition() || targetPosition == null) {
            return;
        }
        
        double newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());

        this.position = new Point2D.Double(
                this.position.getX() + speed * Math.cos(newAngle),
                this.position.getY() + speed * Math.sin(newAngle)
        );

        healthBar.setPosition(new Point2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight()));
    }

    public boolean isAtTargetPosition() {
        return position.distance(targetPosition) <= 7 * speed / 8;
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
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
