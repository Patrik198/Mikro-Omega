package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame {

    public TitleScreen() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("D:\\hrbek2\\Mikro-Omega_git1\\src\\Images\\160px-Diamond_(inventory)_MCE.png").getImage());

        JButton b1 = new JButton("Start");
        b1.setFont(new Font("Serif", Font.BOLD, 45));
        b1.setFocusPainted(false);
        add(b1, BorderLayout.NORTH);

        JButton b2 = new JButton("HighScore");
        b2.setFont(new Font("Serif", Font.BOLD, 45));
        b2.setFocusPainted(false);
        add(b2, BorderLayout.SOUTH);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Game");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setUndecorated(false);

                Game game = new Game(frame);

                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
                game.startGameThread();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScore();
            }
        });
    }
}