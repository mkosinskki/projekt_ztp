public class GameManager {
    private static GameManager instance;
    private IGame gra;
    private Player p1, p2;
    //MOZNA ZROBIC IMPLEMENTACJE GRAFICZNA DODAJAC KOLEJNY WZORZEC

    private GameManager(IGame gra) {
        this.gra=gra;
    }

    public static GameManager getInstance(IGame game) {
        if (instance == null) {
            instance = new GameManager(game);
        }
        return instance;
    }
    
    public void startGame(){
        gra.setupGame();
        gra.startGame();
        instance=null;
    }
}

// public class GameManager {
//     private static GameManager instance;
//     private Player player1;
//     private Player player2;
//     private Board board1;
//     private Board board2;
//     private boolean isConsoleMode;

//     private GameManager() {}

//     public static GameManager getInstance() {
//         if (instance == null) {
//             instance = new GameManager();
//         }
//         return instance;
//     }

//     public void setupGame(boolean isConsoleMode, String gameMode) {
//         this.isConsoleMode = isConsoleMode;
//         board1 = new Board(10);
//         board2 = new Board(10);

//         switch (gameMode) 
//         {
//             case "Player vs Player":
//                 player1 = new HumanPlayer(board1);
//                 player2 = new HumanPlayer(board2);
//                 break;
//             case "Player vs AI":
//                 player1 = new HumanPlayer(board1);
//                 player2 = new ComputerPlayer(board2, new AIEasy());
//                 break;
//             case "AI vs AI":
//                 player1 = new ComputerPlayer(board1, new AIEasy());
//                 player2 = new ComputerPlayer(board2, new AIHard());
//                 break;
//         }
//     }

//     public void startGame() {
//         if (isConsoleMode) {
//             startConsoleGame();
//         } else {
//             startGUIGame();
//         }
//     }

//     private void startConsoleGame() 
//     {
//         System.out.println("Console game started!");
//         boolean gameOver = false;
//         while (!gameOver) {
//             System.out.println("Player 1's turn:");
//             int[] move1 = player1.makeMove();
//             boolean hit1 = board2.markShot(move1[0], move1[1]);
//             System.out.println(hit1 ? "Hit!" : "Miss!");

//             if (board2.areAllShipsSunk()) {
//                 System.out.println("Player 1 wins!");
//                 gameOver = true;
//                 continue;
//             }

//             System.out.println("Player 2's turn:");
//             int[] move2 = player2.makeMove();
//             boolean hit2 = board1.markShot(move2[0], move2[1]);
//             System.out.println(hit2 ? "Hit!" : "Miss!");

//             if (board1.areAllShipsSunk()) {
//                 System.out.println("Player 2 wins!");
//                 gameOver = true;
//             }
//         }
//     }

//     private void startGUIGame() 
//     {
//         new GameScreen(player1, player2, board1, board2);
//     }
// }

