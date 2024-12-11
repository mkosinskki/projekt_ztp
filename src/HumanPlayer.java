import java.util.HashMap;
import java.util.List;

public class HumanPlayer extends Player {

    public HashMap<Integer,String> listaOsiagniec;
    private CustomisationConsole customisationConsole;
    public HumanPlayer(String nickname) {
        super(nickname);
        super.winCount = 0;
        listaOsiagniec = new HashMap<>();
    }

    public HashMap<Integer, String> getListaOsiagniec() {
        return listaOsiagniec;
    }

    /*public void addAchievement(Achievement a) {
        this.achievementList.add(a);
    }*/

    public void setWins(int wins)
    {
        super.winCount = wins;
    }
    //DODAC OPCJE USTAWIANIA RODZAJOW STATKOW I ROZMIARU PLANSZY

    // @Override
    // public void placeShips() 
    // {
    //     System.out.println("Place your ships on the board:");
    //     System.out.println(board);

    //     // Przykład rozmieszczania jednego statku (statyczne dane dla uproszczenia)
    //     //DOdac opcje roznych statkow i rozmiarow
    //     Ship battleship = new Ship("Battleship", 4);
    //     System.out.println("Placing Battleship (size 4). Enter starting coordinates (x y) and direction (h for horizontal, v for vertical): ");
    //     int startX = scanner.nextInt();
    //     int startY = scanner.nextInt();
    //     char direction = scanner.next().charAt(0);

    //     boolean horizontal = (direction == 'h');
    //     boolean placed = board.placeShip(battleship, startX, startY, horizontal);

    //     if (placed) 
    //     {
    //         System.out.println("Battleship placed successfully.");
    //         System.out.println(board);
    //     } 
    //     else 
    //     {
    //         System.out.println("Failed to place Battleship. Try again.");
    //         this.placeShips();
    //     }
    // }

    // @Override
    // public int[] makeMove() 
    // {
    //     System.out.println("Enter coordinates to attack (x y): ");
    //     int x = scanner.nextInt();
    //     int y = scanner.nextInt();
    //     return new int[]{x, y};
    // }

    @Override
    public boolean placeShips(int tab[], char direction, Ship statek)
    {
        int startX = tab[0];
        int startY = tab[1];
        boolean horizontal = (direction == 'h');
        boolean placed = board.placeShip(statek, startX, startY, horizontal);

        if (placed) 
        {
            return true;
        } 
        return false;
    }

    @Override
    public void setBoard(Board board) {
        super.board = board;
    }

    @Override
    public boolean placeShips(Ship statek)
    {
        return false; //zwrot bledu 
    }

    @Override
    public boolean makeMove(int tab[]) 
    {
        boolean hit = board.markShot(tab[0], tab[1]);
        if(hit)
        {
            return true; //trafiono w statek
        }
        return false; //nie trafiono w statek
    }

    public void setAchievementList(HashMap<Integer, String> listaOsiagniec) {
        this.listaOsiagniec = listaOsiagniec;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Osiagniecia: ");
        listaOsiagniec.forEach((key, value) -> sb.append(key + " " + "\"" + value + "\"" +","));
        return sb.toString();
    } //trzeba zmienic i dodac jeszcze wypisanie osiagniec
}