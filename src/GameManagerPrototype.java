import java.util.Scanner;

public class GameManagerPrototype 
{
    private static GameManagerPrototype instance;
    private Player p1, p2;
    private Ship[] statki;
    private Interface interfejs;

    private GameManagerPrototype(Interface interfejs) 
    {
        this.interfejs = interfejs;
    }

    public static GameManagerPrototype getInstance(Interface interfejs) 
    {
        if (instance == null) 
        {
            instance = new GameManagerPrototype(interfejs);
        }
        return instance;
    }

    public void setupGame() 
    {
        
    }
    
    public void startGame()
    {

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