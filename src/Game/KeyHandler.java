package Game;

import java.awt.event.KeyEvent;

/**
 * Handles keyboard input for the game.
 * Tracks the pressed state of the A, D movement keys.
 */
public class KeyHandler {

    /** True while the A key (move left) is held down. */
    public boolean LeftPressed;

    /** True while the D key (move right) is held down. */
    public boolean RightPressed;


    /**
     * Sets the corresponding direction flag to true when a movement key is pressed.
     *
     * @param e the key event containing the key code
     */

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            LeftPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            RightPressed = true;
        }
    }

    /**
     * Sets the corresponding direction flag to false when a movement key is released.
     *
     * @param e the key event containing the key code
     */

    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();


        if (code == KeyEvent.VK_A) {
            LeftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            RightPressed = false;
        }
    }
}