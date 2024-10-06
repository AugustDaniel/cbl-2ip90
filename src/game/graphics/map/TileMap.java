package game.graphics.map;

import game.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.Random;

public class TileMap implements Drawable {

    private TileSet tileSet;
    private final int MAP_HEIGHT = 32;
    private final int MAP_WIDTH = 32;
    private int[][] tileGrid;

    public TileMap(TileSet tileSet) {
        this.tileSet = tileSet;
        init();
    }

    private void init() {
        tileGrid = new int[MAP_HEIGHT][MAP_WIDTH];
        Random random = new Random();

        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                tileGrid[i][j] = random.nextInt(21);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                Optional<BufferedImage> imageOptional = tileSet.getTile(this.tileGrid[y][x]);

                if (imageOptional.isEmpty()) {
                    g.drawRect(x * tileSet.TILE_WIDTH, y * tileSet.TILE_HEIGHT, tileSet.TILE_WIDTH, tileSet.TILE_HEIGHT);
                } else {
                    g.drawImage(imageOptional.get(), x * tileSet.TILE_WIDTH, y * tileSet.TILE_HEIGHT, null);
                }
            }
        }
    }
}
