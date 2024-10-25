package game.graphics.map;

import game.pathfinding.Pathfinding;
import game.util.Drawable;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;

/**
 * This object will initialize the tile map from an XML file.
 * It first creates a tile set and then fills the tile map with these tiles.
 * Will also initialize the pathfinding in the map.
 */
public class TileMap implements Drawable {

    private TileSet tileSet;
    private int mapHeight = 32;
    private int mapWidth = 32;
    private int tileHeight;
    private int tileWidth;
    private int[][] tileGrid;
    private Element root;

    /**
     * Constructor TileMap.
     */
    public TileMap() {
        init();
    }

    private void init() {
        try {
            this.root = DocumentBuilderFactory
                .newDefaultInstance()
                .newDocumentBuilder().parse(new File("res/map.tmx")).getDocumentElement();
            this.tileHeight = Integer.parseInt(this.root.getAttribute("tileheight"));
            this.tileWidth = Integer.parseInt(this.root.getAttribute("tilewidth"));
            this.tileSet = new TileSet(
                ImageIO.read(new File("res/towerDefense_tilesheet.png")), 
                this.tileWidth, 
                this.tileHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mapHeight = Integer.parseInt(this.root.getAttribute("height"));
        this.mapWidth = Integer.parseInt(this.root.getAttribute("width"));
        tileGrid = new int[mapHeight][mapWidth];

        String[] layerData = ((Element) this.root.getElementsByTagName("layer").item(0))
                .getElementsByTagName("data")
                .item(0)
                .getTextContent()
                .trim()
                .split(",");

        Pathfinding.initPathfinding(
            tileHeight, 
            tileWidth, 
            mapHeight, 
            mapWidth, 
            this.root.getElementsByTagName("objectgroup"));

        int counter = 0;
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                this.tileGrid[i][j] = Integer.parseInt(layerData[counter].trim()) - 1;
                counter++;
            }
        }
    }

    /**
     * Draws all the tiles in the tileMap.
     * @param g graphics object
     */
    @Override
    public void draw(Graphics2D g) {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                Optional<BufferedImage> imageOptional = tileSet.getTile(this.tileGrid[y][x]);

                if (imageOptional.isEmpty()) {
                    g.drawRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                } else {
                    g.drawImage(imageOptional.get(), x * tileWidth, y * tileHeight, null);
                }
            }
        }
    }

    /**
     * checks whether a point is inside the map and is a valid location.
     * It is a valid location if it is not inside the mob lane.
     * @param point point to check
     * @return true when free false when not
     */
    public boolean isFree(Point2D point) {
        int x = (int) (point.getX() / tileWidth);
        int y = (int) (point.getY() / tileHeight);

        return x < mapWidth && y < mapHeight && !Pathfinding.isLane(x, y);
    }
}
