package game.util;

import java.io.Serializable;

/**
 * For objects that need to be updatable.
 */
public interface Updatable extends Serializable {

    /**
     * update
     */
    void update();
}
