package game.npc.towers;

import game.GameManager;
import game.graphics.ui.menu.TowerMenu;
import game.npc.Npc;
import game.npc.mobs.Mob;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

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

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return this.price;
    }

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

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Tower o) {
        return Integer.compare(this.price, o.price);
    }

    public abstract Tower copyOf();

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

    public void doDamage() {
        if (targetMob != null) {
            if (!isInRange(targetMob.getPosition())) {
                targetMob = null;
                return;
            }

            targetMob.damage(getDamage());
        }
    }

    public boolean isInRange(Point2D position) {
        return this.position.distance(position) <= this.range;
    }

    public boolean toggleClicked() {
        this.isClicked = !isClicked;
        return this.isClicked;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    public void setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    public TowerMenu getTowerMenu() {
        return this.towerMenu;
    }

    public void upgrade() {
        this.upgradePrice += price;
        this.damage += damage / 2;
        this.range += range / 2;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    @Override
    public void setPosition(Point2D position) {
        super.setPosition(position);

        if (this.towerMenu != null) {
        this.towerMenu.setPosition(position);
        }
    }
}
