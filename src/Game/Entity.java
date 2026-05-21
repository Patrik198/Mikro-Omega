package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int x, y;
    private int speed;

    private BufferedImage left1, right1;
    private String direction;

    // --- animace ---
    public Image[] cartFrames = new Image[2];
    public int currentFrame = 0;
    private int frameCounter = 0;
    private static final int FRAME_SWAP = 5; // každých N snímků přepni sprite

    public void updateAnimation(boolean isMoving) {
        if (isMoving) {
            frameCounter++;
            if (frameCounter >= FRAME_SWAP) {
                frameCounter = 0;
                currentFrame = 1 - currentFrame; // 0 ↔ 1
            }
        } else {
            currentFrame = 0; // stojí → klidový snímek
            frameCounter = 0;
        }
    }
}