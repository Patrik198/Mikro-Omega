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
    Score hc;

    private Frame frame;

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
    private final double[] rotationSpeeds = {0.03, 0.03, 0.03, 0.03};

    /**
     * Sets whether the game is currently running.
     *
     * @param bezi true = game is running, false = game is stopped
     */
    public void setBezi(boolean bezi) {
        this.bezi = bezi;
    }

    /**
     * Constructor. Loads all images, initialises game variables,
     * sets the window size, and registers the key listener.
     *
     * @param frame the main application window (JFrame) to which this panel is added
     */
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
        hc = new Score();
        currentItem = rnd.nextInt(4);
        zivoty = 3;
        bezi = true;
        this.frame = frame;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(kh);

        frame.add(this);
        frame.setResizable(false);
        frame.pack();

        this.requestFocus();

        frame.setIconImage(new ImageIcon(getClass().getResource("/Images/160px-Diamond_(inventory)_MCE.png")).getImage());
    }

    /**
     * Creates and starts the game thread that drives the main game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop running on its own thread.
     * Calls update() for logic and repaint() for rendering approximately every 16 ms (~60 FPS).
     */
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

    /**
     * Updates all game logic every frame:
     *
     *   Moves the cart left/right based on pressed keys
     *   Clamps the cart position to screen boundaries
     *   Updates the cart animation (moving / idle)
     *   Advances the falling item's position and rotation
     *   Resets the item when it falls below the screen
     *   Handles collision between the cart and the item:
     *
     *  Valuable item caught → increments score, increases speed every 10 points
     *  Bottle (item 2) caught → removes a life, ends the game at 0 lives
     */
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
                    gameThread = null;
                    frame.dispose();
                }
            } else {
                hc.counter();
                if (hc.getScorecounter() % 10 == 0){
                    diamondspeed++;
                }
            }

            currentItem = rnd.nextInt(4);
        }
    }

    /**
     * Renders the entire game scene each frame:
     *
     *Background stretched across the full screen
     *Cart sprite using the current animation frame
     *Currently falling item with rotation (diamond, iron ingot, bottle, or gold)
     *Debug hitboxes for the cart (red) and item (blue)
     *
     * @param g the graphics context provided by Swing
     */
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

        // Debug hitboxes
//        g2.setColor(new Color(255, 0, 0, 120));
//        g2.fillRect(cartX + 50, cartY + 170, tileSize * 8 - 100, tileSize * 4-60);
//        g2.setColor(Color.RED);
//        g2.drawRect(cartX + 50, cartY + 170, tileSize * 8 - 100, tileSize * 4-60);
//
//        g2.setColor(new Color(0, 0, 255, 120));
//        g2.fillRect(img2X+20, img2Y+292, 90, 50);
//        g2.setColor(Color.BLUE);
//        g2.drawRect(img2X+50, img2Y+292, 130, 50);
    }

    /**
     * Draws an image rotated by the given angle around its centre point.
     * The graphics context is temporarily transformed and then restored after drawing.
     *
     * @param g2    the graphics context
     * @param img   the image to draw
     * @param x     x coordinate of the image's top-left corner
     * @param y     y coordinate of the image's top-left corner
     * @param w     width of the image in pixels
     * @param h     height of the image in pixels
     * @param angle rotation angle in radians
     */
    private void drawRotatedImage(Graphics2D g2, Image img, int x, int y, int w, int h, double angle) {
        int cx = x + w / 2;
        int cy = y + h / 2;
        g2.rotate(angle, cx, cy);
        g2.drawImage(img, x, y, w, h, null);
        g2.rotate(-angle, cx, cy);
    }
}