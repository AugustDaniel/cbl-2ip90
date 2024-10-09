package game.npc.towers;


import game.GameManager;
import game.graphics.ui.menu.TowerMenu;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;

public class TankTower extends Tower{


    public TankTower(Point2D position, GameManager manager) {
        super(manager, position, "Tank", 1, 100, 50, 1);
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
        return new TankTower(position, this.manager);
    }
}
