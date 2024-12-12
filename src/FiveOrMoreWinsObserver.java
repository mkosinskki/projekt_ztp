public class FiveOrMoreWinsObserver implements IObserver {
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer gracz){
        if(gracz.getOsiagniecie(1))
            gracz.Unsubscribe(this);
        if(wins!=0 && wins%5==0){
            GameManagerPrototype.getInstance(null).interfejs.komunikatOsiagniecie(1);
            gracz.setAchievementList(1);
            gracz.Unsubscribe(this);}
    }
}
