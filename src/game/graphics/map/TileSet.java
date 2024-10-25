package game.graphics.map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores the image for the tile set and the sub images for the tiles.
 * Tiles are stored in an arraylist and the index is used as the id.
 */
public class TileSet {

    private final List<BufferedImage> tiles;
    public int tileHeight;
    public int tileWidth;
    private final BufferedImage image;

    /**
     * constructor TileSet.
     * @param image complete tileSet image
     * @param width width of single tile
     * @param height height of single tile
     */
    public TileSet(BufferedImage image, int width, int height) {
        this.image = image;
        this.tiles = new ArrayList<>();
        this.tileHeight = height;
        this.tileWidth = width;
        init();
    }

    private void init() {
        try {
            for (int y = 0; y < image.getHeight() / tileHeight; y++) {
                for (int x = 0; x < image.getWidth() / tileWidth; x++) {
                    tiles.add(image.getSubimage(x * 32, y * 32, tileWidth, tileHeight));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an optional with the tile corresponding to the id.
     * @param id id of the tile
     * @return optional with the sub image of the tile
     */
    public Optional<BufferedImage> getTile(int id) {
        if (id > tiles.size()) {
            return Optional.empty();
        }

        return Optional.of(this.tiles.get(id));
    }
}
