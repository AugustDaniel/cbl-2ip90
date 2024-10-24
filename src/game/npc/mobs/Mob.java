package game.npc.mobs;

import game.GameManager;
import game.graphics.ui.HealthBar;
import game.npc.Npc;
import game.pathfinding.Vertex;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Abstract mob clas.
 * Has a current Vertex for pathfinding and other values needed to play the game.
 * A mob also has a health bar.
 */
public abstract class Mob extends Npc {

    protected String name;
    protected int price;
    protected int damage;
    protected int health;
    protected int maxHealth;
    protected HealthBar healthBar;
    protected Point2D targetPosition;
    protected double speed;
    protected Vertex currentVertex;

    /**
     * Constructor Mob
     * @param manager manager object
     * @param name name
     * @param price price
     * @param damage damage
     * @param maxHealth health
     * @param speed speed
     * @param currentVertex current vertex for pathfinding
     */
    public Mob(GameManager manager, String name, int price, int damage, int maxHealth, double speed, Vertex currentVertex) {
        super(currentVertex.getPosition(), manager);
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.targetPosition = currentVertex.getPosition();
        this.speed = speed;
        this.position = currentVertex.getPosition();
        this.currentVertex = currentVertex;
        this.healthBar = new HealthBar(1, 40, 5, new Point2D.Double(position.getX() - image.getWidth() / 2.0, position.getY() - image.getHeight()));
    }

    /**
     * draws the mob and its health bar
     * @param g graphics object
     */
    public void draw(Graphics2D g) {
        g.drawImage(image, (int) (this.position.getX() - image.getWidth() / 2), (int) (position.getY() - image.getHeight() / 2), null);
        healthBar.draw(g);
    }

    /**
     * Checks the position and adjusts it depending on the speed moving towards the target position.
     * Also changes the health bar position and percentage.
     * @param npcs npcs
     */
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

    /**
     * checks whether the mob is close enough to its target position.
     * @return true when it is close to target position, false when not.
     */
    public boolean isAtTargetPosition() {
        return position.distance(targetPosition) <= 7 * speed / 8;
    }

    /**
     * Set target position
     * @param targetPosition position to set as target position
     */
    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     * damage this mob with a certain amount.
     * @param damage amount to damage mob with
     */
    public void damage(int damage) {
        this.health -= damage;
    }

    /**
     * Get price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * calculates the percentage of the current health in relation to the max health
     * @return percentage of current health
     */
    public float getHealthPercentage() {
        return ((float) health / maxHealth);
    }

    /**
     * checks whether mob is dead.
     * @return true when health is lower or equal to 0 false when not
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Get current vertex
     * @return current vertex
     */
    public Vertex getCurrentVertex() {
        return currentVertex;
    }

    /**
     * Set current vertex
     * @param vertex vertex to set as current
     */
    public void setCurrentVertex(Vertex vertex) {
        this.currentVertex = vertex;
        setTargetPosition(vertex.getPosition());
    }

    /**
     * Get damage
     * @return damage
     */
    public int getDamage() {
        return damage;
    }
}
