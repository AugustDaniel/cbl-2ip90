package game.npc.mobs;

import game.GameManager;
import game.pathfinding.Pathfinding;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Goblin mob with set values for all the parameters.
 */
public class GoblinMob extends Mob {

    /**
     * Constructor GoblinMob.
     * @param manager manager object
     */
    public GoblinMob(GameManager manager) {
        super(manager, "Goblin", 15, 20, 10, 0.7, Pathfinding.startPoint);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/ArmourPsionicGoblin.png"))
                .getSubimage(0, 0, 32, 32);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
