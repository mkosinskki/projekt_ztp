public class EnemiesHitObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer gracz){
        if(gracz.getOsiagniecie(3))
            gracz.Unsubscribe(this);
        if(enemiesHit==20){
            gracz.setAchievementList(0);
            GameManagerPrototype.getInstance(null).interfejs.komunikatOsiagniecie(3);
            gracz.Unsubscribe(this);}
    }
}