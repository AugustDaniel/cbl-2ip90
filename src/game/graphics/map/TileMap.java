package game.graphics.map;

import game.Drawable;


import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class TileMap implements Drawable {

    private TileSet tileSet;
    private int mapHeight = 32;
    private int mapWidt = 32;
    private int tileHeight;
    private int tileWidth;
    private int[][] tileGrid;
    private Element root;

    public TileMap() {
        init();
    }

    private void init() {
        try {
            this.root = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().parse(new File("res/map.tmx")).getDocumentElement();
            this.tileHeight = Integer.parseInt(this.root.getAttribute("tileheight"));
            this.tileWidth = Integer.parseInt(this.root.getAttribute("tilewidth"));
            this.tileSet = new TileSet(ImageIO.read(new File("res/spriteatlas.png")), this.tileWidth, this.tileHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mapHeight = Integer.parseInt(this.root.getAttribute("height"));
        this.mapWidt = Integer.parseInt(this.root.getAttribute("width"));
        tileGrid = new int[mapHeight][mapWidt];

        String[] layerData = ((Element) this.root.getElementsByTagName("layer").item(0))
                .getElementsByTagName("data")
                .item(0)
                .getTextContent()
                .trim()
                .split(",");

        int counter = 0;
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidt; j++) {
                this.tileGrid[i][j] = Integer.parseInt(layerData[counter].trim()) - 1;
                counter++;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidt; x++) {
                Optional<BufferedImage> imageOptional = tileSet.getTile(this.tileGrid[y][x]);

                if (imageOptional.isEmpty()) {
                    g.drawRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                } else {
                    g.drawImage(imageOptional.get(), x * tileWidth, y * tileHeight, null);
                }
            }
        }
    }

    public boolean isFree(Point2D point) {
        int x = (int) (point.getX() / tileWidth);
        int y = (int) (point.getY() / tileHeight);

        System.out.println(x);
        System.out.println(y);

        if (x >= mapWidt || y >= mapHeight) {
            return false;
        }

        //TODO
        return true;
    }
}
