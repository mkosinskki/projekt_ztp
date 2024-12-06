import java.util.Random;

public class AIMedium implements AIStrategy {
    private Random random;

    public AIMedium() {
        this.random = new Random();
    }

    @Override
    public int[] calculateMove(Board board) {
        // Prosty przykład: losowy wybór z sąsiednich komórek po trafieniu
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) 
            {
                Cell cell = board.getCell(i, j);
                if (cell.isHit() && cell.containsShip()) {
                    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                    for (int[] dir : directions) 
                    {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if (board.isWithinBounds(newX, newY) && !board.getCell(newX, newY).isHit()) 
                        {
                            return new int[]{newX, newY};
                        }
                    }
                }
            }
        }
        // Jeśli brak sąsiednich celów, wybiera losowe pole
        return new AIEasy().calculateMove(board);
    }
}
