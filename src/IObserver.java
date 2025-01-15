public interface IObserver 
{
    void Notify(int wins, int ships, int enemies, HumanPlayer hp);
}

class OneWinObserver implements IObserver
{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp)
    {
        if(hp.getMyAchievements(0))
            hp.Unsubscribe(this);
        else if(wins==1)
        {
            hp.setAchievementList(0);
            GameManager.getInstance(null).myInterface.achievementMessage(0);
            hp.Unsubscribe(this);
        }
    }
}

class FiveOrMoreWinsObserver implements IObserver 
{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp)
    {
        if(hp.getMyAchievements(1))
            hp.Unsubscribe(this);
        else if(wins==5)
        {
            GameManager.getInstance(null).myInterface.achievementMessage(1);
            hp.setAchievementList(1);
            hp.Unsubscribe(this);
        }
    }
}
class ShipsPlacedObserver implements IObserver
{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer hp)
    {
        if(hp.getMyAchievements(2))
            hp.Unsubscribe(this);
        else if(shipsPlaced==30)
        {
            hp.setAchievementList(2);
            GameManager.getInstance(null).myInterface.achievementMessage(2);
            hp.Unsubscribe(this);
        }
    }
}

class EnemiesHitObserver implements IObserver
{
    public void Notify(int wins, int shipsPlaced, int enemiesHit, HumanPlayer player){
        if(player.getMyAchievements(3))
            player.Unsubscribe(this);
        else if(enemiesHit==20)
        {
            player.setAchievementList(3);
            GameManager.getInstance(null).myInterface.achievementMessage(3);
            player.Unsubscribe(this);
        }
    }
}

