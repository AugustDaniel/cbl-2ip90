package game.npc.mobs;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;

public class ZombieMob extends Mob{

    public ZombieMob(Point2D position) {
        super(position, "Zombie", 10, 5, 5);
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
