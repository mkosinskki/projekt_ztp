public class ShipsPlacedObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp){
        if(hp.getMyAchievements(2))
            hp.Unsubscribe(this);
        if(shipsPlaced==30){
            hp.setAchievementList(2);
            GameManager.getInstance(null).myInterface.achievementMessage(2);
            hp.Unsubscribe(this);}
    }
}