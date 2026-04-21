import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton b2 = new JButton("HighScore");
        add(b2, BorderLayout.PAGE_END);
        b2.setFont(new Font("Serif", Font.BOLD, 45));
        b2.setBounds(75, 150, 75, 150);
        b2.setFocusPainted(false);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new App1();
                dispose();

            }
        });
    }
}
