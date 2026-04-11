import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JFrame {

    public TitleScreen(){
        super("Title screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
    }
}
