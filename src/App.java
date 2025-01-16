
public class App {
    public static void main(String[] args) throws Exception 
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> 
        {
            GameManager.getInstance(null).updateAllPlayers();
        }));

        InterfaceConsole console = new InterfaceConsole();
        GameManager.getInstance(console).userMenu();
    }
    
    public static void Delay(long time)
    {
        long millis = System.currentTimeMillis() % 10000000;
        while(millis+time>System.currentTimeMillis() % 10000000);
    }
}
