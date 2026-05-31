package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a game entity with position, speed, and sprite animation.
 * Currently used as the player-controlled minecart.
 */
public class Entity {

    private int x, y;
    private int speed;

    private BufferedImage left1, right1;
    private String direction;

    /** Array holding the two animation frames of the cart (idle and moving). */
    public Image[] cartFrames = new Image[2];

    /** Index of the currently displayed animation frame (0 or 1). */
    public int currentFrame = 0;

    /** Counter tracking how many frames have passed since the last sprite swap. */
    private int frameCounter = 0;

    /** Number of game frames to wait before switching to the next animation sprite. */
    private static final int FRAME_SWAP = 5;

    /**
     * Updates the cart's animation state each game frame.
     * While the cart is moving, alternates between frame 0 and frame 1
     *
     * @param isMoving true if the cart is currently moving, false if standing still
     */
    public void updateAnimation(boolean isMoving) {
        if (isMoving) {
            frameCounter++;
            if (frameCounter >= FRAME_SWAP) {
                frameCounter = 0;
                currentFrame = 1 - currentFrame; // toggle between 0 and 1
            }
        } else {
            currentFrame = 0; // idle → reset to default frame
            frameCounter = 0;
        }
    }
}