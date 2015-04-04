package helicopter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** A listener for the GUI window. */
public class GUIListener extends KeyAdapter {
  
    /** A GUI for the maze game. */
    private GUI window;

    /**
     * Creates a listener for the GUI window.
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
     * @param event triggering key press
     */
    @Override
    public void keyTyped(KeyEvent event) {
        try {
            this.window.getGame().applyingGas(true);
        } catch (HelicopterGame.CollisionException e) {
            System.out.println("Oh noes!");
        }
        this.window.update();
    }

    @Override
    public void keyReleased(KeyEvent event) {
    } 
}