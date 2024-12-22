public class InterfaceGUI extends Interface {
    @Override
    public int menu(){
        return 0;
    }
    @Override
    public int chooseGameMode(){
        return 0;
    }
    @Override
    public String getNickname(){
        return "";
    }
    @Override
    public int[] getCoordinates(){
        return new int[0];
    }
    @Override
    public char getDirection(){
        return 0;
    }

    @Override
    public void showAllPlayersStatistics(PlayerList playerList) {

    }

    @Override
    public int chooseStatistics() {
        return 0;
    }

    @Override
    public void showPlayer(Player player)
    {
    }
    @Override
    public int[] getShipCount(){
        return new int[0];
    }

    @Override
    public void shotResultMessage(int[] coordinates, boolean hit){}

    @Override
    public int chooseAIdifficulty(){
        return 0;
    }

    @Override
    public void showBoard(Player player){}

    @Override
    public void MessagesRegardingShip(int option, int ShipSize){}

    @Override
    public void winnerMessage(Player Winner) {

    }

    @Override
    public void achievementMessage(Player achiever) {
    }

    @Override
    public int chooseSetup()
    {
        return 0;
    }

    @Override
    public int getBoardSize()
    {
        return 0;
    }
    
    @Override
    public void customisationMenu()
    {
        
    }

    @Override
    public void loggedInMessage(String nick)
    {
        
    }
}
