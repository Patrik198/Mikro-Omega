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
    Image img;
    Image img2;
    Image img3;
    Image img4;
    Image background;
    HighScore hc;

    private final int screenWidth;
    private final int screenHeight;
    private Random rnd;
    private int currentItem;

    private int cartX = 610;
    private int cartY = 463;
    private int cartSpeed = 12;
    private int diamondspeed = 5;
    private int img2X;
    private int img2Y;
    private int zivoty;
    private boolean bezi;

    private double itemRotation = 0;
    private final double[] rotationSpeeds = {0.03, 0.06, 0.04, 0.08};

    public void setBezi(boolean bezi) {
        this.bezi = bezi;
    }

    public Game(JFrame frame) {

        cart.cartFrames[0] = new ImageIcon(getClass().getResource("/Images/minecart1.png")).getImage();
        cart.cartFrames[1] = new ImageIcon(getClass().getResource("/Images/defaultcart.png")).getImage();
        img2 = new ImageIcon(getClass().getResource("/Images/diamond.png")).getImage();
        img = new ImageIcon(getClass().getResource("/Images/ironingot.png")).getImage();
        img3 = new ImageIcon(getClass().getResource("/Images/pet lahev.png")).getImage();
        img4 = new ImageIcon(getClass().getResource("/Images/zlato.png")).getImage();
        background = new ImageIcon(getClass().getResource("/Images/background upravene.png")).getImage();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screensize.getWidth();
        screenHeight = (int) screensize.getHeight();
        rnd = new Random();
        img2X = rnd.nextInt(screenWidth);
        img2Y = -350;
        hc = new HighScore();
        currentItem = rnd.nextInt(4);
        zivoty = 3;
        bezi = true;

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
            repaint();

            try {
                Thread.sleep(16);
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

        if (cartX < -65) {
            cartX = -65;
        }

        if (cartX > screenWidth - tileSize * 12 + 240) {
            cartX = screenWidth - tileSize * 12 + 240;
        }

        boolean isMoving = kh.LeftPressed || kh.RightPressed;
        cart.updateAnimation(isMoving);

        img2Y += diamondspeed;
        itemRotation += rotationSpeeds[currentItem];

        if (img2Y > screenHeight) {
            img2Y = -350;
            img2X = rnd.nextInt(screenWidth - 350);
            currentItem = rnd.nextInt(4);
            itemRotation = 0;
        }

        Rectangle itemRect;
        Rectangle cartRect = new Rectangle(cartX + 50, cartY + 170, tileSize * 8 - 100, tileSize * 4 - 60);

        if (currentItem == 0) {
            itemRect = new Rectangle(img2X + 20, img2Y + 292, 90, 50);
        } else {
            itemRect = new Rectangle(img2X + 50, img2Y + 142, 130, 50);
        }

        if (cartRect.intersects(itemRect)) {
            img2Y = -350;
            img2X = rnd.nextInt(screenWidth - 350);
            itemRotation = 0;

            if (currentItem == 2) {
                zivoty--;
                if (zivoty <= 0) {
                    setBezi(false);
                    gameThread = null; // zastaví smyčku
                }
            } else {
                hc.counter();
            }

            currentItem = rnd.nextInt(4);
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.drawImage(cart.cartFrames[cart.currentFrame], cartX, cartY, tileSize * 10, tileSize * 10, null);

        switch (currentItem) {
            case 0:
                drawRotatedImage(g2, img2, img2X, img2Y, 350, 350, itemRotation);
                break;
            case 1:
                drawRotatedImage(g2, img, img2X, img2Y, 300, 300, itemRotation);
                break;
            case 2:
                drawRotatedImage(g2, img3, img2X, img2Y, 200, 200, itemRotation);
                break;
            default:
                drawRotatedImage(g2, img4, img2X, img2Y, 250, 250, itemRotation);
                break;
        }

//        Hitboxy na testy a pro ukazku
        g2.setColor(new Color(255, 0, 0, 120));
        g2.fillRect(cartX + 50, cartY + 170, tileSize * 8 - 100, tileSize * 4-60);
        g2.setColor(Color.RED);
        g2.drawRect(cartX + 50, cartY + 170, tileSize * 8 - 100, tileSize * 4-60);

        g2.setColor(new Color(0, 0, 255, 120));
        g2.fillRect(img2X+20, img2Y+292, 90, 50);
        g2.setColor(Color.BLUE);
        g2.drawRect(img2X+50, img2Y+292, 130, 50);
    }

    private void drawRotatedImage(Graphics2D g2, Image img, int x, int y, int w, int h, double angle) {
        int cx = x + w / 2;
        int cy = y + h / 2;
        g2.rotate(angle, cx, cy);
        g2.drawImage(img, x, y, w, h, null);
        g2.rotate(-angle, cx, cy);
    }
}