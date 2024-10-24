package game.npc.towers;

import game.GameManager;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;

/**
 * Round tower with set values for all parameters.
 */
public class RoundTower extends Tower{

    /**
     * Constructor RoundTower
     * @param position position
     * @param gameManager manager object
     */
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

    /**
     * Creates a new tower object with the same values
     * @return new object copy of this tower
     */
    @Override
    public Tower copyOf() {
        return new RoundTower(position, manager);
    }
}
