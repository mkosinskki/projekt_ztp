// import java.awt.*;
// import javax.swing.*;

// public class StartScreen extends JFrame {
//     public StartScreen() {
//         setTitle("Battleship - Start");
//         setSize(400, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         JLabel label = new JLabel("Choose Game Mode");
//         label.setAlignmentX(Component.CENTER_ALIGNMENT);

//         JButton playerVsPlayer = new JButton("Player vs Player");
//         JButton playerVsAI = new JButton("Player vs AI");
//         JButton aiVsAI = new JButton("AI vs AI");

//         playerVsPlayer.addActionListener(e -> startGame("Player vs Player"));
//         playerVsAI.addActionListener(e -> startGame("Player vs AI"));
//         aiVsAI.addActionListener(e -> startGame("AI vs AI"));

//         panel.add(label);
//         panel.add(playerVsPlayer);
//         panel.add(playerVsAI);
//         panel.add(aiVsAI);

//         add(panel);
//         setVisible(true);
//     }

//     private void startGame(String gameMode) {
//         int result = JOptionPane.showConfirmDialog(this, "Console Mode?", "Game Mode", JOptionPane.YES_NO_OPTION);
//         boolean isConsoleMode = (result == JOptionPane.YES_OPTION);

//         GameManager.getInstance().setupGame(isConsoleMode, gameMode);
//         GameManager.getInstance().startGame();

//         dispose();
//     }
// }