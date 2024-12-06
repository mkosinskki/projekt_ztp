import java.util.Random;

public class SimulationPlayer extends Player {
    private AIStrategy strategy;

    public SimulationPlayer(Board board, AIStrategy strategy) {
        super(board);
        this.strategy = strategy;
    }

    @Override
    public void placeShips() {
        System.out.println("Computer is placing ships...");

        Ship battleship = new Ship("Battleship", 4);

        boolean placed = false;
        while (!placed) 
        {
            int startX = new Random().nextInt(board.getSize());
            int startY = new Random().nextInt(board.getSize());
            boolean horizontal = new Random().nextBoolean();
            placed = board.placeShip(battleship, startX, startY, horizontal);
            if(horizontal && placed)
            {
                System.out.println("Ship placed: x start:" + startX + "x end:" + (startX + battleship.getSize() - 1) + "at y: " + startY);
            }
            else if(!horizontal && placed)
            {
                System.out.println("Ship placed: y start:" + startY + "y end:" + (startY + battleship.getSize() - 1) + "at x: " + startX);
            }
        }

        System.out.println("Computer placed Battleship.");
    }

    @Override
    public int[] makeMove() {
        return strategy.calculateMove(board);
    }
}