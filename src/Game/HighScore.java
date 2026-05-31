package Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Represents the score window and tracks the player's current and all-time high score.
 * The high score is persisted to a local file so it survives between game sessions.
 */
public class HighScore extends JFrame {

    /** Path to the file where the high score is saved. */
    private static final String SAVE_FILE = "highscore.txt";

    /** The score accumulated in the current game session. */
    private int currentscore;

    /** The highest score ever reached, loaded from file on startup. */
    private int scorecounter;

    /** Label displaying the current score value. */
    private JLabel score;

    /**
     * Constructs and displays the score window.
     * Loads the saved high score from disk and initialises the layout.
     */
    public HighScore() {
        super("High Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("/Images/160px-Diamond_(inventory)_MCE.png").getImage());

        scorecounter = loadHighScore();

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
     * If the new value exceeds the saved high score, updates and saves it to disk.
     */
    public void counter() {
        currentscore++;

        if (currentscore > scorecounter) {
            scorecounter = currentscore;
            score.setText(String.valueOf(scorecounter));
            saveHighScore(scorecounter);
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

    /**
     * Loads the high score
     * Returns 0 if the file does not exist or cannot be read.
     *
     * @return the saved high score, or 0 if unavailable
     */
    private int loadHighScore() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Saves the given score
     *
     * @param highScore the score to save
     */
    private void saveHighScore(int highScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}