import java.util.Random;
import java.util.Scanner;

public class GameConsole implements IGame {
    private Player player1;
    private Player player2;
    private Board board1;
    private Board board2;
    private Ship[] statki;

    public void setupGame() {
        Scanner scnaner = new Scanner(System.in);
        System.out.println("Wybierz rozmiar tablicy AxA, napisz A:\n");
        board1 = new Board(scnaner.nextInt());
        board2 = new Board(board1.getSize());
        //ustawianie statkow (statki)
        
        System.out.println("Podaj ilosc statkow: ");
        //DO IMPLEMENTACJI

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
        // SPRAWDZAMY ILE ZWYCIESTW MA GRACZ | OD TEGO ZALEZY JAKI MOZE WYBRAC WYGLAD STATKU NP. (H zamiast S), ORAZ JAK WYGLADA WODA (ZAMIENIC . na cos innego)
        // ARRAYLIST STRINGOW 


        //nick iloscZwyciestw
        //osiagniecia
        //
        //nastepny gracz

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

    public void stawianieStatkow(Player gracz)
    {
        boolean postawiono = false;
        int[] koordynaty = new int[2];
        char kierunek;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nUstawianie statków\n");
        System.out.println(gracz.board);
        for(Ship statek : statki)
        {
            while(!postawiono)
            {
                System.out.println("Ustawianie statku o dlugosci " + statek.getSize() + ". Podaj wspolrzedne poczatku (x y) statku oraz ustawienie (h / v)");
                koordynaty[0] = scanner.nextInt();
                koordynaty[1] = scanner.nextInt();
                kierunek = scanner.next().charAt(0);
                postawiono = gracz.placeShips(koordynaty, kierunek, statek);
                if(!postawiono)
                {
                    System.out.println("Blad stawiania statku, zostaniesz poproszony o jego ponowne ustawienie.");
                }
            }
            System.out.println("Pomyslnie postawiono statek.");
            postawiono = false;
            System.out.println(gracz.board);
        }
        System.out.println("Ustawiono wszystkie statki prawidlowo.");
        System.out.println(gracz.board);
    }

    public void ustawianieStatkowAI(Player AI)
    {
        System.out.println("Computer is placing ships...");
        for(Ship statek : statki)
        {
           AI.placeShips(statek);
        }
        System.out.println("Bot pomyslnie ustawil statki.");
    }

    public void atakAI(ComputerPlayer AI) //atakujacy gracz
    {
        int[] koordynaty = AI.attackEnemy(); 
        System.out.println("Przeciwnik zaatakowal " + koordynaty[0] + " " + koordynaty[1]);
    }

    public void atakPrzeciwnik(Player gracz) //przekazujesz przeciwnika ktorego atakuejsz
    {
        int[] koordynaty = new int[2];
        boolean trafionoStatek = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj koordynaty: x y");
        koordynaty[0] = scanner.nextInt();
        koordynaty[1] = scanner.nextInt();
        scanner.close();
        
        trafionoStatek = gracz.makeMove(koordynaty);
        if(trafionoStatek)
        {
            System.out.println("Trafiony");
        }
        else
        {
            System.out.println("Nietrafiony");
        }
    }
}
