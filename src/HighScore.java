import javax.swing.*;
import java.awt.*;

public class HighScore extends JFrame {

    public HighScore() {
        super("High Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // zavře jen toto okno
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Best score:", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        JLabel score = new JLabel("0", SwingConstants.CENTER); // zatím placeholder
        score.setFont(new Font("Serif", Font.PLAIN, 60));
        add(score, BorderLayout.CENTER);

        setVisible(true);
    }
}