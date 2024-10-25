package game.wave;

import game.npc.mobs.Mob;
import game.util.Updatable;

import java.util.List;
import java.util.Queue;

public class Wave implements Updatable {

    private List<Mob> mobList;
    private Queue<Mob> mobsToSpawn;
    private double spawnRate;
    private long timer;

    public Wave(List<Mob> mobList, Queue<Mob> mobsToSpawn, double spawnRate) {
        this.spawnRate = spawnRate;
        this.mobList = mobList;
        this.mobsToSpawn = mobsToSpawn;
        this.timer = System.currentTimeMillis();
    }

    public boolean isDone() {
        return mobsToSpawn.isEmpty();
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - this.timer >= spawnRate) {
            mobList.add(mobsToSpawn.poll());
            this.timer = System.currentTimeMillis();
        }
    }
}
