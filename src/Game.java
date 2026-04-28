import javax.swing.*;
import java.awt.*;

public class Game implements Runnable{

        private JFrame jFrame;
        private Thread gameThread;
        KeyHandler kh = new KeyHandler();

    public Game() {
        jFrame = new JFrame();
        jFrame.addKeyListener(kh);
        jFrame.setFocusable(true);
        init();
    }

    int cartx = 100;
    int carty = 100;
    int cartspeed = 4;


    private void init(){
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLayout(new BorderLayout());
        jFrame.setTitle("Game");
        jFrame.getContentPane().setBackground(Color.gray);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){

        while (gameThread != null){

            update();

//            repaint();
        }
    }

    public void update(){

        if (kh.LeftPressed == true){
            cartx -= cartspeed;
        } else if (kh.RightPressed) {
            cartx += cartspeed;
        }
    }

    public void paintobject(Graphics g){
        paintobject(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.cyan);
        g2.fillRect(cartx,carty,100,100);
        g2.dispose();
    }



}
