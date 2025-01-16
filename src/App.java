
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

    public static void clearConsole()
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
