package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;

    private Thread gameThread;
    KeyHandler kh = new KeyHandler();
    Entity cart = new Entity();
    Image img2;
    Image background;

    private final int screenWidth;
    private final int screenHeight;
    private Random rnd;

    private int cartX = 610;
    private int cartY = 463;
    private int cartSpeed = 12;
    private int img2X;


    public Game(JFrame frame) {

        cart.cartFrames[0] = new ImageIcon(getClass().getResource("/Images/pixil-frame-0.png")).getImage();
        cart.cartFrames[1] = new ImageIcon(getClass().getResource("/Images/defaultcart.png")).getImage();
        img2 = new ImageIcon(getClass().getResource("/Images/pixil-frame-0 (6).png")).getImage();
        background = new ImageIcon(getClass().getResource("/Images/background upravene.png")).getImage();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screensize.getWidth();
        screenHeight = (int) screensize.getHeight();
        rnd = new Random();
        img2X = rnd.nextInt(screenWidth);

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
        if (cartX < -65) {
            cartX = -65;
        }

        if (cartX > screenWidth - tileSize * 12 + 240) {
            cartX = screenWidth - tileSize * 12 + 240;
        }

        boolean isMoving = kh.LeftPressed || kh.RightPressed;
        cart.updateAnimation(isMoving);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // getWidth()/getHeight() funguje správně až po setVisible
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.drawImage(cart.cartFrames[cart.currentFrame], cartX, cartY, tileSize * 10, tileSize * 10, null);
        g2.drawImage(img2, img2X, cartY-400, 350, 350, null);
    }
}