public class App {
    public static void main(String[] args) throws Exception 
    {
        GameManager.getInstance(new GameConsole());
        GameManager.getInstance(null).startGame();
    }
}
