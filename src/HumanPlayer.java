import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    private final static IObserver[] usableObservers = {new OneWinObserver(),
                                                        new FiveOrMoreWinsObserver(),
                                                        new ShipsPlacedObserver(),
                                                        new EnemiesHitObserver()};
    private final String[] AvailableAchievements = GameManager.achievements; //zle uzycie singletona bo mamy globalny dostep
    private boolean[] myAchievements = new boolean[AvailableAchievements.length];
    private int shipsPlaced=0, enemiesHit=0;
    List<IObserver> observers;
    public HumanPlayer(String nickname) {
        super(nickname);
        super.winCount = 0;
        observers=new ArrayList<IObserver>();
        for(int i=0; i<usableObservers.length; i++)
            Subscribe(usableObservers[i]);
    }

    public boolean getMyAchievements(int i) {
        return myAchievements[i];
    }
    public void setRecords(int a, int b){
        shipsPlaced=b;
        enemiesHit=a;
    }
    public void updateHits(boolean bool){
        if(bool)
            enemiesHit++;
        Notify();
    }
    private void Subscribe(IObserver observer)
    {
        observers.add(observer);
    }

    public void Unsubscribe(IObserver observer){
        observers.remove(observer);
    }
    private void Notify(){
        for (IObserver observer : observers) {
            observer.Notify(winCount, shipsPlaced, enemiesHit, this);
        }
    }
    @Override
    public void addWinCount()
    {
        Notify();
        winCount++;
    }
    /*public void addAchievement(Achievement a) {
        this.achievementList.add(a);
    }*/

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
            shipsPlaced++;
            Notify();
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

    public void setAchievementList(int i) {
        myAchievements[i]=true;
    }
    public void setAchievementList(boolean[] lista) {
        myAchievements =lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Trafione pola: "+enemiesHit);
        sb.append(", Postawione statki: "+shipsPlaced);
        sb.append(", Osiagniecia: ");
        for(int i = 0; i< myAchievements.length; i++)
            if(myAchievements[i])
                sb.append(i + " " + "\"" + AvailableAchievements[i] + "\"" +",");
        return sb.toString();
    } //trzeba zmienic i dodac jeszcze wypisanie osiagniec
}