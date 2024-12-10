/*
import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {
    private Player player1;
    private Player player2;
    private Board board1;
    private Board board2;

    public GameScreen(Player player1, Player player2, Board board1, Board board2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board1 = board1;
        this.board2 = board2;

        setTitle("Battleship - Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 2));

        // Plansze graczy
        panel.add(createBoardPanel(board1, "Player 1"));
        panel.add(createBoardPanel(board2, "Player 2"));

        add(panel);
        setVisible(true);
    }

    private JPanel createBoardPanel(Board board, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(board.getSize(), board.getSize()));
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                JButton cellButton = new JButton();
                cellButton.setPreferredSize(new Dimension(30, 30));
                grid.add(cellButton);
            }
        }
        panel.add(grid, BorderLayout.CENTER);

        return panel;
    }
}*/
