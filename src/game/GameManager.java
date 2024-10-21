package game;

import game.npc.mobs.GoblinMob;
import game.npc.mobs.Mob;
import game.npc.mobs.ZombieMob;
import game.npc.towers.RoundTower;
import game.npc.towers.TankTower;
import game.npc.towers.Tower;
import game.pathfinding.Pathfinding;
import game.pathfinding.Vertex;
import game.util.Updatable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class GameManager implements Updatable {

    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_MONEY = 100;
    private int playerMoney;
    private int playerHealth;
    private final List<Tower> towerList;
    private final TreeSet<Tower> buyableTowers;
    private final List<Mob> mobs;

    public GameManager() {
        this.towerList = new ArrayList<>();
        this.playerMoney = DEFAULT_MONEY;
        this.playerHealth = DEFAULT_HEALTH;
        this.buyableTowers = new TreeSet<>();
        this.mobs = new ArrayList<>();
        init();
    }

    private void init () {
        this.buyableTowers.add(new TankTower(new Point2D.Double(), this));
        this.buyableTowers.add(new RoundTower(new Point2D.Double(), this));
    }

    public void start() {
        this.mobs.add(new GoblinMob(this));
    }

    public void addTower(Tower tower) {
        playerMoney -= tower.getPrice();
        this.towerList.add(tower);
        tower.setPlaced(true);
        tower.setClicked(false);
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
            this.playerMoney += tower.getPrice();
        }
    }

    public void upgradeTower(Tower tower) {
        if (tower.getUpgradePrice() > playerMoney) {
            return;
        }

        playerMoney -= tower.getUpgradePrice();
        tower.upgrade();
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
                return;
            }

            if (mob.isAtTargetPosition()) {
                if (mob.getCurrentNode() == Pathfinding.endPoint) {
                    this.playerHealth -= mob.getDamage();
                    iterator.remove();
                }

                Vertex next = Pathfinding.path.get(mob.getCurrentNode());

                if (next != null) {
                    mob.setCurrentNode(Pathfinding.path.get(mob.getCurrentNode()));
                }
            }
        }
    }

    public void endGame() {
        clear();
    }

    private void clear() {
        this.towerList.clear();
        this.mobs.clear();
        playerMoney = DEFAULT_MONEY;
        playerHealth = DEFAULT_HEALTH;
    }

    public void setDifficulty(GameDifficulty selectedItem) {
        switch (selectedItem) {
            case EASY -> this.playerHealth = DEFAULT_HEALTH * 2;
            case NORMAL -> this.playerHealth = this.DEFAULT_HEALTH;
            case HARD -> this.playerHealth = DEFAULT_HEALTH / 2;
        }
    }
}
