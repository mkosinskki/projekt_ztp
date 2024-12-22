public class OneWinObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp){
        if(hp.getMyAchievements(0))
            hp.Unsubscribe(this);
        if(wins==1){
            hp.setAchievementList(0);
            GameManager.getInstance(null).myInterface.achievementMessage(0);
            hp.Unsubscribe(this);}
    }
}