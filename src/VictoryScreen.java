import java.awt.*;
import javax.swing.*;

public class VictoryScreen extends JFrame 
{
    public VictoryScreen(String winner) {
        setTitle("Victory!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel(winner + " Wins!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);

        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.addActionListener(e -> {
            new StartScreen();
            dispose();
        });

        add(backToMenu, BorderLayout.SOUTH);
        setVisible(true);
    }
}
