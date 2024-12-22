public class FiveOrMoreWinsObserver implements IObserver {
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp){
        if(hp.getMyAchievements(1))
            hp.Unsubscribe(this);
        if(wins!=0 && wins%5==0){
            GameManager.getInstance(null).myInterface.achievementMessage(1);
            hp.setAchievementList(1);
            hp.Unsubscribe(this);}
    }
}
