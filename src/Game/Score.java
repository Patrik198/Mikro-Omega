package Game;

import javax.swing.*;
import java.awt.*;

public class Score extends JFrame {

    private int currentscore;
    private int scorecounter;
    private JLabel score;

    public Score() {
        super("High Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // zavře jen toto okno
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("/Images/160px-Diamond_(inventory)_MCE.png").getImage());

        JLabel title = new JLabel("Best score:", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        score = new JLabel(String.valueOf(scorecounter), SwingConstants.CENTER);
        score.setFont(new Font("Serif", Font.PLAIN, 60));
        add(score, BorderLayout.CENTER);

        setVisible(true);
    }

    public void counter() {
        currentscore++;

        if (currentscore > scorecounter) {
            scorecounter = currentscore;
            score.setText(String.valueOf(scorecounter));
        }
    }

    public int getScorecounter() {
        return scorecounter;
    }
}