public abstract class Interface {
    public abstract int menu();

    public abstract int chooseGameMode();

    public abstract String getNickname();
    
    public abstract int[] getCoordinates();

    public abstract char getDirection();

    public abstract int chooseStatistics();

    public abstract int[] getShipCount();

    public abstract void shotResultMessage(int[] coordinates, boolean hit);

    public abstract int chooseAIdifficulty();

    public abstract void showBoard(Player player);

    public abstract void MessagesRegardingShip(int option, int ShipSize);

    public abstract void showPlayer(Player player);

    public abstract void showAllPlayersStatistics(PlayerList playerList);

    public abstract void winnerMessage(Player Winner);

    public abstract void achievementMessage(Player achiever);

    public abstract int chooseSetup();

    public abstract int getBoardSize();

    public abstract void customisationMenu();

    public abstract void loggedInMessage(String nick);
}
