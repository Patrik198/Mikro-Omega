package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu screen of the game.
 * Displays a "Start" button to launch a new game and a "Score" button to view the scoreboard.
 */
public class TitleScreen extends JFrame {

    /**
     * Constructs the title screen window.
     * Sets up the layout, styling, buttons, and their action listeners.
     */
    public TitleScreen() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/Images/160px-Diamond_(inventory)_MCE.png")).getImage());

        JButton b1 = new JButton("Start");
        b1.setFont(new Font("Serif", Font.BOLD, 45));
        b1.setFocusPainted(false);
        add(b1, BorderLayout.NORTH);

        JButton b2 = new JButton("High Score");
        b2.setFont(new Font("Serif", Font.BOLD, 45));
        b2.setFocusPainted(false);
        add(b2, BorderLayout.SOUTH);

        /**
         * Opens a new maximised game window and starts the game loop
         * when the "Start" button is clicked.
         */
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

        /**
         * Opens the score screen when the "Score" button is clicked.
         */
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScore();
            }
        });
    }
}