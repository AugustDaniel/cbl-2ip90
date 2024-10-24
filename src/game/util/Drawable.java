package game.util;

import java.awt.*;
import java.io.Serializable;

/**
 * For objects that need a draw method with a graphics object
 */
public interface Drawable extends Serializable {

    /**
     * draw
     * @param g graphics object
     */
    void draw(Graphics2D g);
}
