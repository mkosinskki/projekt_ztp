public class EnemiesHitObserver implements IObserver{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer player){
        if(player.getMyAchievements(3))
            player.Unsubscribe(this);
        if(enemiesHit==20){
            player.setAchievementList(0);
            GameManager.getInstance(null).myInterface.achievementMessage(3);
            player.Unsubscribe(this);}
    }
}