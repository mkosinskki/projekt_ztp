import java.util.Random;

public class ComputerPlayer extends Player {
    private AIStrategy strategy;
    private Board PlayerBoard;

    public ComputerPlayer( String nickname, AIStrategy strategy) {
        super(nickname);
        this.strategy = strategy;
        super.winCount = 0;
    }

    @Override
    public void setBoard(Board board) {
        super.board = board;
    }

    public void setPlayerBoard(Board playerBoard) {
        PlayerBoard = playerBoard;
    }

    @Override
    public boolean placeShips(Ship statek) 
    {
        boolean placed = false;
        while (!placed) 
        {
            int startX = new Random().nextInt(board.getSize());
            int startY = new Random().nextInt(board.getSize());
            boolean horizontal = new Random().nextBoolean();

            placed = board.placeShip(statek, startX, startY, horizontal);
        }
        return true;
    }

    @Override 
    public boolean placeShips(int tab[], char direction, Ship statek)
    {
        return false; //zwrot bledu
    }

    @Override
    public boolean makeMove(int koordynaty[]) 
    {
        boolean hit = board.markShot(koordynaty[0], koordynaty[1]);
        if(hit)
        {
            return true; //trafiono w statek
        }
        return false; //nie trafiono w statek
    }

    public int[] attackEnemy()
    {
        int[] koordynaty = new int[2];
        koordynaty = strategy.calculateMove(PlayerBoard);
        // PlayerBoard.markShot(koordynaty[0], koordynaty[1]);
        return koordynaty;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
