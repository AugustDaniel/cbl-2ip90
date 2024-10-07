package game.graphics.map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TileSet {

    private List<BufferedImage> tiles;
    public int tileHeight;
    public int tileWidth;
    private BufferedImage image;

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

    public Optional<BufferedImage> getTile(int id) {
        if (id > tiles.size()) {
            return Optional.empty();
        }

        return Optional.of(this.tiles.get(id));
    }
}
