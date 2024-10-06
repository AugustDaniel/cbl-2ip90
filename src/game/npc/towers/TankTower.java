package game.npc.towers;

import game.npc.Npc;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;

public class TankTower extends Tower{

    public TankTower(Point2D position) {
        super(position, "Tank", 1, 10, 50);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/pixel-tank.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tower copyOf() {
        return new TankTower(position);
    }
}
