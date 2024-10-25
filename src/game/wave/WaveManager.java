package game.wave;

import game.GameManager;
import game.npc.mobs.Mob;
import game.npc.mobs.ZombieMob;
import game.util.Updatable;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * WaveManager object.
 * Manages the waves of the game and makes sures it starts
 * and end waves appropriately.
 */
public class WaveManager implements Updatable {

    private GameManager manager;
    private int waveCounter;
    private Wave currentWave;
    private long timer;
    private boolean breakStarted;

    /**
     * Constructor WaveManager
     * @param manager manager object
     */
    public WaveManager(GameManager manager) {
        this.manager = manager;
        this.waveCounter = 1;
        this.timer = System.currentTimeMillis();
        this.breakStarted = false;
        startNewWave();
    }

    /**
     * reset waves
     */
    public void reset() {
        this.waveCounter = 1;
    }

    /**
     * start waves
     */
    public void start() {
        startNewWave();
    }

    /**
     * updates the wave.
     * If wave is done it will start a 5-second break before starting the next wave.
     */
    @Override
    public void update() {
        if (currentWave.isDone()) {
            startBreak();

            long currentTime = System.currentTimeMillis();

            if (currentTime - this.timer >= 5000L) {
                this.waveCounter++;
                startNewWave();
                breakStarted = false;
            }

            return;
        }

        currentWave.update();
    }

    private void startBreak() {
        if (breakStarted) {
            return;
        }

        timer = System.currentTimeMillis();
        breakStarted = true;
    }

    /**
     * get wave counter
     * @return current wave counter
     */
    public int getWaveCounter() {
        return waveCounter;
    }

    private void startNewWave() {
        Queue<Mob> mobsToSpawn = new ArrayDeque<>();

        for (int i = 0; i < calculateAmountOfEnemies(); i++) {
            mobsToSpawn.offer(new ZombieMob(manager));
        }

        this.currentWave = new Wave(manager.getMobList(), mobsToSpawn, calculateSpawnRate());
    }

    private int calculateAmountOfEnemies() {
        return (int) (14.9 * Math.pow(waveCounter, 0.23));
    }

    private double calculateSpawnRate() {
        long baseInterval = 3000;
        long minInterval = 500;
        double scalingFactor = 0.9;

        return Math.max(minInterval, (long) (baseInterval * Math.pow(scalingFactor, waveCounter - 1)));
    }
}
