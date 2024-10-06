package game.graphics.map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TileSet {

    private List<BufferedImage> tiles;
    public final int TILE_HEIGHT = 32;
    public final int TILE_WIDTH = 32;
    private BufferedImage image;

    public TileSet(BufferedImage image) {
        this.image = image;
        this.tiles = new ArrayList<>();
        init();
    }

    private void init() {
        try {
            for (int y = 0; y < image.getHeight() / TILE_HEIGHT; y++) {
                for (int x = 0; x < image.getWidth() / TILE_WIDTH; x++) {
                    tiles.add(image.getSubimage(x * 32, y * 32, TILE_WIDTH, TILE_HEIGHT));
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
