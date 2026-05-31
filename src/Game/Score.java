package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the score window and tracks the player's current and high score.
 * Displays a small window showing the current score, which updates in real time during gameplay.
 */
public class Score extends JFrame {

    /** The score accumulated in the current game session. */
    private int currentscore;

    /** The highest score reached so far (displayed in the window). */
    private int scorecounter;

    /** Label displaying the current score value. */
    private JLabel score;

    /**
     * Constructs and displays the score window.
     * Initialises the layout with a title label and a score counter label.
     */
    public Score() {
        super("Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("/Images/160px-Diamond_(inventory)_MCE.png").getImage());

        JLabel title = new JLabel("score:", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        score = new JLabel(String.valueOf(scorecounter), SwingConstants.CENTER);
        score.setFont(new Font("Serif", Font.PLAIN, 60));
        add(score, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Increments the current score by one.
     * and refreshes the displayed label.
     */
    public void counter() {
        currentscore++;

        if (currentscore > scorecounter) {
            scorecounter = currentscore;
            score.setText(String.valueOf(scorecounter));
        }
    }

    /**
     * Returns the current score value.
     *
     * @return the highest score reached so far
     */
    public int getScorecounter() {
        return scorecounter;
    }
}