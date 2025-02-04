public abstract class Interface 
{
    public abstract int menu();

    public abstract int chooseGameMode();

    public abstract String getNickname();
    
    public abstract int[] getCoordinates();

    public abstract char getDirection();

    public abstract int chooseStatistics();

    public abstract int[] getShipCount();

    public abstract void shotResultMessage(int[] coordinates, boolean hit);

    public abstract int chooseAIdifficulty();

    public abstract void delay(long time);

    public abstract void pauseLine();

    public abstract void clear();

    public abstract void showBoard(Player attacker, Player attacked);

    public abstract void showBoard(Player player);

    public abstract void messagesRegardingShip(int option, int ShipSize);

    public abstract void showPlayer(Player player);

    public abstract void showAllPlayersStatistics(PlayerList playerList);

    public abstract void winnerMessage(Player Winner);

    public abstract void achievementMessage(int i);

    public abstract int chooseSetup();

    public abstract int getBoardSize();

    public abstract void customisationMenu(String nickname);

    public abstract void customisationErrorMessage(String nick);

    public abstract void loggedInMessage(String nick);

    public abstract void errorMessages(int errorNumber);

    public abstract void settingShipsMessage(String nickname);

    public abstract void showHistoryMenu(GameHistory gameHistory);

    public abstract int choosePlayerToCheck();

    public abstract void attackingStageMessage();

    public abstract ICustomization getCustomization(String nick);

    public abstract void printInfo(String info);
}
