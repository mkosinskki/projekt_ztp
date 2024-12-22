import java.util.HashMap;

public class HumanPlayer extends Player {

    public HashMap<Integer,String> achievements;
    private CustomisationConsole customisationConsole;
    public HumanPlayer(String nickname) {
        super(nickname);
        super.winCount = 0;
        achievements = new HashMap<>();
    }

    public HashMap<Integer, String> getAchievements() {
        return achievements;
    }


    public void setWins(int wins)
    {
        super.winCount = wins;
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
    public void setBoard(Board board) {
        super.board = board;
    }

    @Override
    public boolean placeShips(Ship ship)
    {
        return false; //zwrot bledu 
    }

    @Override
    public boolean makeMove(int[] arr)
    {
        boolean hit = board.markShot(arr[0], arr[1]);
        if(hit)
        {
            return true; //trafiono w statek
        }
        return false; //nie trafiono w statek
    }

    public void setAchievementList(HashMap<Integer, String> achievements) {
        this.achievements = achievements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Osiagniecia: ");
        achievements.forEach((key, value) -> sb.append(key + " " + "\"" + value + "\"" +","));
        return sb.toString();
    } //trzeba zmienic i dodac jeszcze wypisanie osiagniec
}