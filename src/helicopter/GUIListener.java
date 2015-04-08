package helicopter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A listener for the GUI window.
 */
class GUIListener extends KeyAdapter {

    /**
     * A GUI for the maze game.
     */
    private final GUI window;

    /**
     * Creates a listener for the GUI window.
     *
     * @param window the GUI to listen to
     */
    public GUIListener(GUI window) {
        this.window = window;
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    /**
     * Set next move the input given by the players.
     *
     * @param event triggering key press
     */
    @Override
    public void keyTyped(KeyEvent event) {
        try {
            window.getGame().gas(true);
            window.update(true);
        } catch (HelicopterGame.CollisionException e) {
            System.out.println("Oh noes!");
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }
}