import java.util.Scanner;

public class GameConsole implements IGame {
    private Player player1;
    private Player player2;
    private Board board1;
    private Board board2;

    public void setupGame() {
        Scanner scnaner = new Scanner(System.in);
        System.out.println("Wybierz rozmiar tablicy AxA, napisz A:\n");
        board1 = new Board(scnaner.nextInt());
        board2 = new Board(board1.getSize());

        System.out.println("\nWybierz tryb gry:\n\t0: Gracz vs Bot\n\t1: Gracz vs Gracz\n\t2: Bot vs Bot\n");
        switch(scnaner.nextInt()){
            case 0:
                player1=new HumanPlayer(board1);
                System.out.println("Wybierz poziom trudnosci bota:\n\t0: Latwy\n\t1: Sredni\n\t2: Trudny\n");
                switch(scnaner.nextInt()){
                case 0:player2=new ComputerPlayer(board2,board2,new AIEasy()); break;
                //case 1:player2=new ComputerPlayer(board2,board,new AIMedium()); break;
                case 2:player2=new ComputerPlayer(board2,board1,new AIHard()); break;
                default: player2=new ComputerPlayer(board2,board1,new AIHard());
                }
                break;
            case 1:
                player1=new HumanPlayer(board1);
                player2=new HumanPlayer(board2);
                break;
            case 2:
                System.out.println("Wybierz poziom trudnosci bota pierwszego:\n\t0: Latwy\n\t1: Sredni\n\t2: Trudny\n");
                switch(scnaner.nextInt()){
                    case 0:player1=new SimulationPlayer(board1,new AIEasy()); break;
                    //case 1:player1=new SimulationPlayer(board1,new AIMedium()); break;
                    case 2:player1=new SimulationPlayer(board1,new AIHard()); break;
                    default: player1=new SimulationPlayer(board1,new AIHard());
                }
                System.out.println("Wybierz poziom trudnosci bota drugiego:\n\t0: Latwy\n\t1: Sredni\n\t2: Trudny\n");
                switch(scnaner.nextInt()){
                    case 0:player2=new SimulationPlayer(board2,new AIEasy()); break;
                    //case 1:player2=new SimulationPlayer(board2,new AIMedium()); break;
                    case 2:player2=new SimulationPlayer(board2,new AIHard()); break;
                    default: player2=new SimulationPlayer(board2,new AIHard());
                }
            }

        // DODAC TWORZENIE STATKOW I PRZEKAZYWANIE ICH GRACZOM DO USTAWIENIA
        // DODAC KLASE DO PRZECHOWYWANIA GRACZY (jakas baza danych jakby), kiedy gra sie konczy zapisywac danemu graczowi (nicknameowi) kolejne zwyciestwo
        // DODAC HISTORIE ROZGYRWEK kazda gra to nowy plik txt do kotego zapisujemy wszystkie System.out.println
        // DODAC GUI z personalizacja
        // DODAC SYSTEM OSIAGNIEC
        // DODAC ODBLOKOWYWANIE NOWEJ ZAWARTOSCI

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
