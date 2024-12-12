public class OneWinObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer gracz){
        if(gracz.getOsiagniecie(0))
            gracz.Unsubscribe(this);
        if(wins==1){
            gracz.setAchievementList(0);
            GameManager.getInstance(null).interfejs.komunikatOsiagniecie(0);
            gracz.Unsubscribe(this);}
    }
}