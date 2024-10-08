package game;

import game.npc.mobs.Mob;
import game.npc.mobs.ZombieMob;
import game.npc.towers.TankTower;
import game.npc.towers.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class GameManager implements Updatable {

    private int playerMoney;
    private int playerHealth;
    private final List<Tower> towerList;
    private final TreeSet<Tower> buyableTowers;
    private final List<Mob> mobs;

    public GameManager() {
        this.towerList = new ArrayList<>();
        this.playerMoney = 100;
        this.playerHealth = 100;
        this.buyableTowers = new TreeSet<>();
        this.mobs = new ArrayList<>();
        init();
    }

    private void init() {
        this.buyableTowers.add(new TankTower(new Point2D.Double()));
        this.mobs.add(new ZombieMob(new Point2D.Double(100, 100)));
    }

    public void addTower(Tower tower) {
        playerMoney -= tower.getPrice();
        this.towerList.add(tower);
    }

    public TreeSet<Tower> getBuyableTowers() {
        return buyableTowers;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public List<Mob> getMobList() {
        return this.mobs;
    }

    public void sellTower(Tower tower) {
        if (this.towerList.contains(tower)) {
            this.towerList.remove(tower);
            this.playerMoney -= tower.getPrice();
        }
    }

    @Override
    public void update() {
        for (Tower tower : towerList) {
            tower.update(mobs);
        }

        Iterator<Mob> iterator = mobs.iterator();
        while (iterator.hasNext()) {
            Mob mob = iterator.next();

            mob.update(towerList);
            if (mob.isDead()) {
                iterator.remove();
                this.playerMoney += mob.getPrice();
            }
        }
    }
}
