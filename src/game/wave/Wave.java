package game.wave;

import game.npc.mobs.Mob;
import game.util.Updatable;

import java.util.List;
import java.util.Queue;

/**
 * Wave object.
 * Will spawn in mobs from a queue every given interval.
 */
public class Wave implements Updatable {

    private List<Mob> mobList;
    private Queue<Mob> mobsToSpawn;
    private double spawnRate;
    private long timer;

    /**
     * Constructor Wave
     * @param mobList list of mobs from game manager
     * @param mobsToSpawn queue with mobs to spawn
     * @param spawnRate spawn rate in nanoseconds
     */
    public Wave(List<Mob> mobList, Queue<Mob> mobsToSpawn, double spawnRate) {
        this.spawnRate = spawnRate;
        this.mobList = mobList;
        this.mobsToSpawn = mobsToSpawn;
        this.timer = System.currentTimeMillis();
    }

    /**
     * When all mobs have been spawned the wave is done.
     * @return whether the wave is done
     */
    public boolean isDone() {
        return mobsToSpawn.isEmpty() && mobList.isEmpty();
    }

    /**
     * Adds the mobs to the mob list on the interval of the spawn rate
     */
    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - this.timer >= spawnRate) {
            mobList.add(mobsToSpawn.poll());
            this.timer = System.currentTimeMillis();
        }
    }
}
