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

    public int[] getShipPlacement() 
    {
        int x = new Random().nextInt(board.getSize());
        int y = new Random().nextInt(board.getSize());
        return new int[]{x, y};
    }
    public char getShipDirection(){
        return new Random().nextBoolean()?'h':'v';
    }

    @Override 
    public boolean placeShips(int tab[], char direction, Ship ship)
    {
        int startX = tab[0];
        int startY = tab[1];
        boolean horizontal = (direction == 'h');
        boolean placed = board.placeShip(ship, startX, startY, horizontal);

        if (placed) 
        {
            return true;
        } 
        return false;
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
        int[] coordinates = new int[2];
        coordinates = strategy.calculateMove(PlayerBoard);
        return coordinates;
    }

    @Override
    public String toString() 
    {
        return super.toString();
    }
}
