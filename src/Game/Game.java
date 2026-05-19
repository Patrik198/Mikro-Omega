package Game;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    private Thread gameThread;
    KeyHandler kh = new KeyHandler();
    Image img;
    Image background;

    final int screenWidth;
    final int screenHeight;

    int cartX = 610;
    int cartY = 505;
    int cartSpeed = 12;

    public Game(JFrame frame) {

        img = new ImageIcon(getClass().getResource("/Images/pixil-frame-0.png")).getImage();
        background = new ImageIcon(getClass().getResource("/Images/background upravene.png")).getImage();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screensize.getWidth();
        screenHeight = (int) screensize.getHeight();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setFocusable(true);

        this.addKeyListener(kh);

        frame.add(this);
        frame.setResizable(false);
        frame.pack();

        this.requestFocus();

        frame.setIconImage(new ImageIcon(getClass().getResource("/Images/160px-Diamond_(inventory)_MCE.png")).getImage());
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
        }

        if (kh.RightPressed) {
            cartX += cartSpeed;
        }

        // hranice obrazovky
        if (cartX < -60) {
            cartX = -60;
        }

        if (cartX > screenWidth - tileSize * 12) {
            cartX = screenWidth - tileSize * 12;
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