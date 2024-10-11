package game.npc.towers;

import game.GameManager;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;

public class RoundTower extends Tower{


    public RoundTower(Point2D position, GameManager gameManager) {
        super(gameManager, position, "Round", 2, 50, 75, 1);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/tankbase.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tower copyOf() {
        return new RoundTower(position, manager);
    }
}
