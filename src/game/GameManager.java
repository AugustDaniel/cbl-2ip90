package game;

import game.npc.towers.TankTower;
import game.npc.towers.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GameManager implements Updatable {

    private int playerMoney;
    private int playerHealth;
    private final List<Tower> towerList;
    private final TreeSet<Tower> buyableTowers;

    public GameManager() {
        this.towerList = new ArrayList<>();
        this.playerMoney = 100;
        this.playerHealth = 100;
        this.buyableTowers = new TreeSet<>();
        init();
    }

    private void init() {
        this.buyableTowers.add(new TankTower(new Point2D.Double()));
    }

    public void addTower(Tower tower) {
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

    @Override
    public void update() {

    }
}
