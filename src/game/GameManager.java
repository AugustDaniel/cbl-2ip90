package game;

import game.npc.mobs.Mob;
import game.npc.towers.RoundTower;
import game.npc.towers.TankTower;
import game.npc.towers.Tower;
import game.pathfinding.Pathfinding;
import game.pathfinding.Vertex;
import game.util.Updatable;
import game.wave.WaveManager;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Game manager object.
 * Has all information about the current game and manages it.
 * Provides the logic to the game.
 */
public class GameManager implements Updatable {

    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_MONEY = 100000;
    private int playerMoney;
    private int playerHealth;
    private final List<Tower> towerList;
    private final TreeSet<Tower> buyableTowers;
    private final List<Mob> mobs;
    private boolean soundOn;
    private Game game;
    private WaveManager waveManager;

    /**
     * Constructor GameManager
     * @param game game object
     */
    public GameManager(Game game) {
        this.towerList = new ArrayList<>();
        this.playerMoney = DEFAULT_MONEY;
        this.playerHealth = DEFAULT_HEALTH;
        this.buyableTowers = new TreeSet<>();
        this.mobs = new ArrayList<>();
        this.soundOn = true;
        this.game = game;
        this.waveManager = new WaveManager(this);
        init();
    }

    private void init () {
        this.buyableTowers.add(new TankTower(new Point2D.Double(), this));
        this.buyableTowers.add(new RoundTower(new Point2D.Double(), this));
    }

    /**
     * Start the game
     */
    public void start() {
        waveManager.start();
    }

    /**
     * Add tower to the game and initializes it.
     * Also subtracts money from the player.
     * @param tower tower to add
     */
    public void addTower(Tower tower) {
        playerMoney -= tower.getPrice();
        this.towerList.add(tower);
        tower.setPlaced(true);
        tower.setClicked(false);
    }

    /**
     * Get BuyAble Towers
     * @return ordered set of all buyAble towers
     */
    public TreeSet<Tower> getBuyableTowers() {
        return buyableTowers;
    }

    /**
     * Get player money
     * @return player money
     */
    public int getPlayerMoney() {
        return playerMoney;
    }

    /**
     * Get player health
     * @return player health
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Get tower list
     * @return tower list
     */
    public List<Tower> getTowerList() {
        return towerList;
    }

    /**
     * Get mob list
     * @return mob list
     */
    public List<Mob> getMobList() {
        return this.mobs;
    }

    /**
     * Sells a tower and removes it from the game.
     * Will increment the playerMoney with the price of the tower.
     * @param tower tower to sell
     */
    public void sellTower(Tower tower) {
        if (this.towerList.contains(tower)) {
            this.towerList.remove(tower);
            this.playerMoney += tower.getPrice();
        }
    }

    /**
     * Upgrades tower if there is enough playerMoney.
     * Will subtract playMoney when upgrading
     * @param tower tower to upgrade
     */
    public void upgradeTower(Tower tower) {
        if (tower.getUpgradePrice() > playerMoney) {
            return;
        }

        playerMoney -= tower.getUpgradePrice();
        tower.upgrade();
    }


    /**
     * Updates all towers and mobs in the game.
     * Also updates the wave manager.
     */
    @Override
    public void update() {
        for (Tower tower : towerList) {
            tower.update(mobs);
        }

        updateMobs();
        waveManager.update();
    }

    private void updateMobs() {
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
                if (mob.getCurrentVertex() == Pathfinding.endPoint) {
                    this.playerHealth -= mob.getDamage();
                    iterator.remove();
                    checkGameOver();
                }

                Vertex next = Pathfinding.path.get(mob.getCurrentVertex());

                if (next != null) {
                    mob.setCurrentVertex(Pathfinding.path.get(mob.getCurrentVertex()));
                }
            }
        }
    }

    private void checkGameOver() {
        if (this.playerHealth <= 0) {
            game.setState(GameState.GAME_OVER);
            endGame();
        }
    }

    /**
     * End game.
     * Resets everything and changes state to game over.
     */
    public void endGame() {
        clear();
    }

    /**
     * Clear all list except buyAble towers and sets health and money back to default
     */
    private void clear() {
        this.towerList.clear();
        this.mobs.clear();
        playerMoney = DEFAULT_MONEY;
        playerHealth = DEFAULT_HEALTH;
        this.waveManager.reset();
    }

    /**
     * Sets the difficulty of the game.
     *
     * @param selectedItem selected difficulty
     */
    public void setDifficulty(GameDifficulty selectedItem) {
        switch (selectedItem) {
            case EASY -> this.playerHealth = DEFAULT_HEALTH * 2;
            case NORMAL -> this.playerHealth = this.DEFAULT_HEALTH;
            case HARD -> this.playerHealth = DEFAULT_HEALTH / 2;
        }
    }

    /**
     * toggles the soundOn variable
     */
    public void toggleSoundOn() {
        this.soundOn = !this.soundOn;
    }

    /**
     * isSoundOn
     * @return isSoundOn
     */
    public boolean isSoundOn() {
        return soundOn;
    }

    /**
     * get wave counter
     * @return the current wave counter
     */
    public int getWaveCounter() {
        return waveManager.getWaveCounter();
    }
}
