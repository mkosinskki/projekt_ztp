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
    public void showBoard(Player player, boolean displayShips){}

    @Override
    public void MessagesRegardingShip(int option, int ShipSize){}

    @Override
    public void winnerMessage(Player Winner) {

    }

    @Override
    public void achievementMessage(int i) {
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
    public void customisationMenu(String nickname)
    {
        
    }

    @Override
    public void CustomisationErrorMessage(String nick){
        System.out.println("Gracz "+nick+" nie wygral jeszcze ani jednej gry aby odblokowac customizacje\n");
    }

    @Override
    public void loggedInMessage(String nick)
    {
        
    }
    @Override
    public void showHistoryMenu(GameHistory gameHistory)
    {
        ;
    }

    @Override
    public void errorMessages(int errorNumber){}

    @Override
    public void settingShipsMessage(String nickname){}

    @Override
    public int choosePlayerToCheck(){return 0;}

    @Override
    public void attackingStageMessage() {

    }

    @Override
    public ICustomization getCustomization(String nick){
        return null;}
}
