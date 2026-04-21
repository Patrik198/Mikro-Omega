import javax.swing.*;
import java.awt.*;

public class App1 {

        private JFrame jFrame;

    public App1() {
        jFrame = new JFrame();
        init();
    }

    private void init(){
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLayout(new BorderLayout());
        jFrame.setTitle("Game");
        jFrame.getContentPane().setBackground(Color.gray);

    }



}
