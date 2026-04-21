import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JFrame {

    public TitleScreen(){
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        JButton b1 = new JButton("Start");
        add(b1, BorderLayout.PAGE_START);
        b1.setFont(new Font("Serif", Font.BOLD, 45));
        b1.setBounds(75, 150, 75, 150);
        b1.setFocusPainted(false);
    }
}
