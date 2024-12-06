public class GameManager {
    private static GameManager instance;
    private Player player1;
    private Player player2;
    private Board board1;
    private Board board2;

    //MOZNA ZROBIC IMPLEMENTACJE GRAFICZNA DODAJAC KOLEJNY WZORZEC

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void setupGame() {
        board1 = new Board(10);
        board2 = new Board(10);

        AIStrategy difficultyLevel = new AIEasy();
        // DODAC TWORZENIE STATKOW I PRZEKAZYWANIE ICH GRACZOM DO USTAWIENIA
        // DODAC KLASE DO PRZECHOWYWANIA GRACZY (jakas baza danych jakby), kiedy gra sie konczy zapisywac danemu graczowi (nicknameowi) kolejne zwyciestwo
        // DODAC HISTORIE ROZGYRWEK kazda gra to nowy plik txt do kotego zapisujemy wszystkie System.out.println
        // DODAC GUI z personalizacja
        // DODAC SYSTEM OSIAGNIEC
        // DODAC ODBLOKOWYWANIE NOWEJ ZAWARTOSCI

        player1 = new HumanPlayer(board1);
        player2 = new ComputerPlayer(board2, difficultyLevel);

        System.out.println("Setting up Player 1:");
        player1.placeShips();

        System.out.println("Setting up Player 2:");
        player2.placeShips();
    }

    public void startGame() 
    {
        boolean gameOver = false;
        while (!gameOver) 
        {
            System.out.println("Player 1's turn:");
            int[] move1 = player1.makeMove();
            boolean hit1 = board2.markShot(move1[0], move1[1]);
            System.out.println(hit1 ? "Hit!" : "Miss!");

            if (board2.areAllShipsSunk()) {
                System.out.println("Player 1 wins!");
                gameOver = true;
                continue;
            }

            System.out.println("Player 2's turn:");
            int[] move2 = player2.makeMove();
            boolean hit2 = board1.markShot(move2[0], move2[1]);
            System.out.println(hit2 ? "Hit!" : "Miss!");

            if (board1.areAllShipsSunk()) {
                System.out.println("Player 2 wins!");
                gameOver = true;
            }
        }
    }
}
