package game.npc.towers;

import game.GameManager;
import game.graphics.ui.menu.TowerMenu;
import game.npc.Npc;
import game.npc.mobs.Mob;
import game.util.SoundPlayer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;

/**
 * Abstract Tower class
 * Contains a tower menu to upgrade or sell tower.
 */
public abstract class Tower extends Npc implements Comparable<Tower> {

    protected String name;
    protected int damage;
    protected int range;
    protected int price;
    protected Mob targetMob;
    protected int fireRate;
    protected long timer;
    protected boolean isClicked;
    protected boolean isPlaced;
    protected TowerMenu towerMenu;
    protected int upgradePrice;

    /**
     * Constructor Tower
     * @param gameManager manager object
     * @param position position
     * @param name name
     * @param damage damage
     * @param range range
     * @param price price
     * @param fireRate fireRate
     */
    public Tower(GameManager gameManager, Point2D position, String name, int damage, int range, int price, int fireRate) {
        super(position, gameManager);
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.price = price;
        this.targetMob = null;
        this.fireRate = fireRate;
        this.isClicked = false;
        this.isPlaced = false;
        this.towerMenu = new TowerMenu(gameManager, this, 100, 50, position);
        this.timer = System.currentTimeMillis();
        this.upgradePrice = price;
    }

    /**
     * Get damage
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get price
     * @return price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Draws the tower.
     * if a tower is clicked it will also display the range and tower menu
     * @param g graphics object
     */
    @Override
    public void draw(Graphics2D g) {
        if (isClicked) {
            g.setColor(new Color(0, 0, 0, 0.3f));
            g.fillOval((int) (position.getX() - range), (int) (position.getY() - range), range * 2, range * 2);

            if (isPlaced) {
                towerMenu.draw(g);
            }
        }

        g.drawImage(image, (int) (this.position.getX() - image.getWidth() / 2), (int) (position.getY() - image.getHeight() / 2), null);
        g.setColor(Color.black);
    }

    /**
     * Name as string
     * @return the name of the tower
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Compares the price of the towers
     * @param o the object to be compared.
     * @return the comparison of the price
     */
    @Override
    public int compareTo(Tower o) {
        return Integer.compare(this.price, o.price);
    }

    /**
     * abstract method of copy of tower
     * @return copy of the tower
     */
    public abstract Tower copyOf();

    /**
     * Checks if mob is in range and targets it.
     * updates tower menu.
     * Does damage according to fire rate to targeted mob
     * @param npcs npc list
     */
    @Override
    public void update(List<? extends Npc> npcs) {
        for (Npc npc : npcs) {
            if (isInRange(npc.getPosition()) && npc instanceof Mob) {
                targetMob = (Mob) npc;
                break;
            }
        }

        towerMenu.update();

        long currentTime = System.currentTimeMillis();
        if (currentTime - this.timer >= this.fireRate * 1000L) {
            doDamage();
            this.timer = System.currentTimeMillis();
        }
    }

    /**
     * Checks whether targeted mob is still in range and does damage to it.
     * If mob is not in range will reset the targeted mob.
     * When damaging a mob it will also play a sound.
     */
    public void doDamage() {
        if (targetMob != null) {
            if (!isInRange(targetMob.getPosition())) {
                targetMob = null;
                return;
            }

            playShootSound();
            targetMob.damage(getDamage());
        }
    }

    private void playShootSound() {
        if (manager.isSoundOn()) {
            SoundPlayer.playSound(new File("res/Gun+1.wav"));
        }
    }

    /**
     * Checks whether a position is in range of the tower
     * @param position position to check
     * @return true if in range false when not
     */
    public boolean isInRange(Point2D position) {
        return this.position.distance(position) <= this.range;
    }

    /**
     * toggles the isClicked variable of the tower
     * @return the value of isClicked after the toggle
     */
    public boolean toggleClicked() {
        this.isClicked = !isClicked;
        return this.isClicked;
    }

    /**
     * Set clicked
     * @param isClicked value to set
     */
    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    /**
     * Set placed
     * @param isPlaced value to set
     */
    public void setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    /**
     * Get TowerMenu
     * @return TowerMenu attached to the tower
     */
    public TowerMenu getTowerMenu() {
        return this.towerMenu;
    }

    /**
     * Upgrades the tower.
     * Increases the next upgrade price.
     * Increases the damage by half of the current damage.
     * Increases the range by half of the current range.
     */
    public void upgrade() {
        this.upgradePrice += price;
        this.damage += damage / 2;
        this.range += range / 2;
    }

    /**
     * Get upgrade price
     * @return upgrade price
     */
    public int getUpgradePrice() {
        return upgradePrice;
    }

    /**
     * Set position of the tower and also the tower menu
     * @param position position
     */
    @Override
    public void setPosition(Point2D position) {
        super.setPosition(position);

        if (this.towerMenu != null) {
            this.towerMenu.setPosition(position);
        }
    }
}
