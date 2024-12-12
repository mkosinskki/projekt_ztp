public class ShipsPlacedObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer gracz){
        if(gracz.getOsiagniecie(2))
            gracz.Unsubscribe(this);
        if(shipsPlaced==30){
            gracz.setAchievementList(2);
            GameManager.getInstance(null).interfejs.komunikatOsiagniecie(2);
            gracz.Unsubscribe(this);}
    }
}