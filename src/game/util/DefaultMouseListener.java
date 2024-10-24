package game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * DefaultMouseListener for objects that only need certain methods from the listener.
 * All methods have a default implementation so that the object implementing this only
 * needs to implement the needed methods.
 * By default, all methods do nothing.
 */
public interface DefaultMouseListener extends MouseListener, MouseMotionListener {

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseClicked(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mousePressed(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseReleased(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseEntered(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseExited(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseDragged(MouseEvent e) {

    }

    /**
     * Does nothing
     * @param e the event to be processed
     */
    @Override
    default void mouseMoved(MouseEvent e) {

    }

}
