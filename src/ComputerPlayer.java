import java.util.Random;

public class ComputerPlayer extends Player {
    private AIStrategy strategy;
    private Board PlayerBoard;

    public ComputerPlayer(Board Ownboard, Board PlayerBoard, AIStrategy strategy) {
        super(Ownboard);
        this.strategy = strategy;
        this.PlayerBoard = PlayerBoard;
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
        return strategy.calculateMove(PlayerBoard);
    }
}
