import java.util.Random;

public class ComputerPlayer extends Player {
    private AIStrategy strategy;

    public ComputerPlayer(Board board, AIStrategy strategy) {
        super(board);
        this.strategy = strategy;
    }

    @Override
    public void placeShips() {
        System.out.println("Computer is placing ships...");

        Ship battleship = new Ship("Battleship", 4);

        boolean placed = false;
        while (!placed) {
            int startX = new Random().nextInt(board.getSize());
            int startY = new Random().nextInt(board.getSize());
            boolean horizontal = new Random().nextBoolean();

            placed = board.placeShip(battleship, startX, startY, horizontal);
        }

        System.out.println("Computer placed Battleship.");
    }

    @Override
    public int[] makeMove() {
        return strategy.calculateMove(board);
    }
}
