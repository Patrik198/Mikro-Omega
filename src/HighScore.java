import javax.swing.*;
import java.awt.*;

public class HighScore {

    private JFrame jFrame;

    public HighScore() {
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
