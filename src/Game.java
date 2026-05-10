import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    private Thread gameThread;
    KeyHandler kh = new KeyHandler();
    Image img;
    Image background;

    int cartX = 100;
    int cartY = 522;
    int cartSpeed = 4;

    public Game(JFrame frame) {
        img = new ImageIcon("pixil-frame-0.png").getImage();
        background = new ImageIcon("background upravene.png").getImage();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.add(this);
        frame.addKeyListener(kh);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();  // ← zavolá paintComponent automaticky

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (kh.LeftPressed) {
            cartX -= cartSpeed;
        } else if (kh.RightPressed) {
            cartX += cartSpeed;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // getWidth()/getHeight() funguje správně až po setVisible
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.drawImage(img, cartX, cartY, tileSize * 10, tileSize * 10, null);
    }
}