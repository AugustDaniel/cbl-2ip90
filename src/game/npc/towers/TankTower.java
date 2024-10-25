package game.npc.towers;


import game.GameManager;
import java.awt.geom.Point2D;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Tank tower with set values for all parameters.
 */
public class TankTower extends Tower {

    /**
     * Constructor RoundTower.
     * @param position position
     * @param manager manager object
     */
    public TankTower(Point2D position, GameManager manager) {
        super(manager, position, "Tank", 1, 75, 50, 1);
    }

    @Override
    protected void initImage() {
        try {
            this.image = ImageIO.read(new File("res/pixel-tank.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new tower object with the same values.
     * @return new object copy of this tower
     */
    @Override
    public Tower copyOf() {
        return new TankTower(position, this.manager);
    }
}
