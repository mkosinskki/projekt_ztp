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
    public void showBoard(Player attacker, Player attacked){}

    @Override
    public void showBoard(Player player){}


    @Override
    public void messagesRegardingShip(int option, int ShipSize){}

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
    public void delay(long time)
    {
        long millis = System.currentTimeMillis() % 10000000;
        while(millis+time>System.currentTimeMillis() % 10000000);
    }

    @Override
    public void pauseLine(){

    }

    @Override
    public void clear()
    {
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
    public void customisationErrorMessage(String nick){
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

    @Override
    public void printInfo(String info){}
}
