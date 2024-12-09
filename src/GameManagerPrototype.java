import java.util.Scanner;

public class GameManagerPrototype 
{
    private static GameManagerPrototype instance;
    private Player p1, p2;
    private Board board1, board2;
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

    public void przedGra()
    {
        int wybor;
        wybor = interfejs.menu();

        switch (wybor) 
        {
            case 1:
                int wyborTrybuGry = 0;
                switch(wyborTrybuGry) 
                {
                    case 1:
                        p1 = new HumanPlayer(board1);
                        p2 = new HumanPlayer(board2);
                        break;
                    case 2:
                        p1 = new HumanPlayer(board1);
                        p2 = new ComputerPlayer(board2, board1, wyborTrudnosciBota());
                        break;
                    case 3:
                        p1 = new ComputerPlayer(board1, board2, wyborTrudnosciBota());
                        p2 = new ComputerPlayer(board2, board1, wyborTrudnosciBota());
                        break;
                    default:
                        throw new AssertionError();
                }
                setupGame();
                break;
            case 2:
                String nick = interfejs.wczytajNick();
                //IMPLEMENTACJA STATYSTYK I ICH WYPISYWANIA DLA DANEGO NICKNAME'U
                break;
            case 3:
                //DO IMPLEMENTACJI CUSTOMIZACJA PLANSZY (W TYM WYPADKU ZMIANA ZNAKOW STATKOW LUB WODY)
                break;
            case 4:
                //DO IMPLEMENTACJI HISTORII GRY
                break;
            default:
                throw new AssertionError();
        }
    }

    public AIStrategy wyborTrudnosciBota()
    {
        int trudnosc = interfejs.wyborTrudnosciBota();
        switch (trudnosc) 
        {
            case 1:
                return new AIEasy();
            // case 2:
            //     return new AIMedium();
            case 3:
                return new AIHard();
            default:
                return new AIEasy();
        }
    }

    public void setupGame() 
    {
        int[] iloscStatkow = interfejs.wczytywanieIlosciStatkow();
        //TWORZENIE STATKOW
        stawianieStatkow(p1);
        stawianieStatkow(p2);
    }
    
    public void startGame()
    {

    }

    public void stawianieStatkow(Player gracz)
    {
        boolean postawiono = false;
        int[] koordynaty;
        char kierunek;

        for(Ship statek : statki)
        {
            while(!postawiono)
            {
                interfejs.komunikatStatek(1, statek.getSize());
                koordynaty = interfejs.getKoordynaty();
                kierunek = interfejs.getUstawienie();
                postawiono = gracz.placeShips(koordynaty, kierunek, statek);
                if(!postawiono)
                {
                    interfejs.komunikatStatek(3, statek.getSize());
                }
            }
            interfejs.komunikatStatek(2, statek.getSize());
            postawiono = false;
            interfejs.pokazTablice(gracz.board);
        }
        interfejs.komunikatStatek(4, 0);
        interfejs.pokazTablice(gracz.board);
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
    public void atakPrzeciwnik2(Player gracz)
    {
        int[] koordynaty = new int[2];
        boolean trafionoStatek = false;

        if(gracz instanceof HumanPlayer)
        koordynaty=interfejs.getKoordynaty();
        else
        koordynaty=((ComputerPlayer)gracz).attackEnemy();
        // i tu by sie usuneło że ai samo zaznacza dla oponenta plansze
        
        trafionoStatek = gracz.makeMove(koordynaty);
        interfejs.komunikatPoStrzale(trafionoStatek);
    }
}