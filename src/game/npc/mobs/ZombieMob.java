package game.npc.mobs;

import game.GameManager;
import game.graphics.ui.GameButton;
import game.pathfinding.Pathfinding;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;
import java.nio.file.Path;

public class ZombieMob extends Mob{

    public ZombieMob(GameManager manager) {
        super(manager,"Zombie", 10, 5, 5, 2.5, Pathfinding.startPoint);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/pixel-tank.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
