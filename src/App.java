import java.util.Timer;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            GameManagerPrototype.getInstance(null).updateAllPlayers();
        }));
        // GameManager.getInstance(new GameConsole());
        // GameManager.getInstance(null).startGame();

        InterfaceConsole konsola = new InterfaceConsole();
        //GameManagerPrototype.getInstance(konsola).setupGame();
        GameManagerPrototype.getInstance(konsola).przedGra();
    }
    
    public static void Delay(long czas){
        long millis = System.currentTimeMillis() % 10000;
        while(millis+czas>System.currentTimeMillis() % 10000)
        ;
    }
}
