package game.npc.mobs;

import game.GameManager;
import game.pathfinding.Pathfinding;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Zombie mob with set values for all the parameters.
 */
public class ZombieMob extends Mob{

    /**
     * Constructor ZombieMob
     * @param manager manager object
     */
    public ZombieMob(GameManager manager) {
        super(manager,"Zombie", 10, 5, 5, 2.5, Pathfinding.startPoint);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/Zombie_strip4.png")).getSubimage(0,0, 25, 32);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
