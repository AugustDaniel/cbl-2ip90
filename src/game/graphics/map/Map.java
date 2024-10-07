package game.graphics.map;

import game.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;

public class Map implements Drawable {

    private TileSet tileSet;
    private TileMap tileMap;

    public Map() {
        try {
            this.tileSet = new TileSet(ImageIO.read(new File("res/spriteatlas.png")));
            this.tileMap = new TileMap(this.tileSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        this.tileMap.draw(g);
    }

    public boolean isFree(Point2D point2D) {
        return tileMap.isFree(point2D);
    }
}
