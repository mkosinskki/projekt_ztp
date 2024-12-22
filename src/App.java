public class App {
    public static void main(String[] args) throws Exception 
    {
        // GameManager.getInstance(new GameConsole());
        // GameManager.getInstance(null).startGame();

        InterfaceConsole konsola = new InterfaceConsole();
        //GameManagerPrototype.getInstance(konsola).setupGame();
        GameManagerPrototype.getInstance(konsola).userMenu();
    }
}
