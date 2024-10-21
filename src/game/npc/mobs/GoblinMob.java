package game.npc.mobs;

import game.GameManager;
import game.pathfinding.Pathfinding;
import game.pathfinding.Vertex;

import javax.imageio.ImageIO;
import java.io.File;

public class GoblinMob extends Mob{
    public GoblinMob(GameManager manager) {
        super(manager, "Goblin", 15, 10, 10, 1, Pathfinding.startPoint);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/ArmourPsionicGoblin.png")).getSubimage(0,0, 32, 32);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
