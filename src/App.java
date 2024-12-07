public class App {
    public static void main(String[] args) throws Exception 
    {
        GameManager.getInstance().setupGame();
        GameManager.getInstance().startGame();
    }
}
