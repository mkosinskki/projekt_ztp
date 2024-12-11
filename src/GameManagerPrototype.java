import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameManagerPrototype {
    private static GameManagerPrototype instance;
    private Player p1, p2;
    private Board board1, board2;
    private Ship[] statki;
    private Interface interfejs;
    private GameHistory gameHistory;
    private PlayerList playerList;
    private int wyborTrybuGry;

    private GameManagerPrototype(Interface interfejs) {
        gameHistory = new GameHistory();
        playerList = new PlayerList();
        this.interfejs = interfejs;
    }

    public static GameManagerPrototype getInstance(Interface interfejs) {
        if (instance == null) {
            instance = new GameManagerPrototype(interfejs);
        }
        return instance;
    }

    public void aplikacja()
    {
        while(true)
        {
            przedGra();
            setupGame();
            startGame();
            gameHistory.saveToFile("History.txt");
        }
    }

    public void przedGra() 
    {
        int wybor;
        wybor = interfejs.menu();
        switch (wybor) 
        {
            case 1:
                wyborTrybuGry = interfejs.wyborTrybuGry();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
                String formattedDate = now.format(formatter);
                gameHistory.setDate(formattedDate);
                switch (wyborTrybuGry) 
                {
                    case 1:
                        gameHistory.setGameMode("Gracz vs Gracz");

                        String nick1 = interfejs.wczytajNick();
                        p1 = playerList.logowanie(nick1,"kkk");
                        interfejs.komunikatLogowanie(p1.nickname);
                        gameHistory.setPlayer1(nick1);

                        String nick2 = interfejs.wczytajNick();
                        p2 = playerList.logowanie(nick2,"kkk");
                        interfejs.komunikatLogowanie(p2.nickname);
                        gameHistory.setPlayer2(nick2);
                        break;
                    case 2:
                        gameHistory.setGameMode("Gracz vs AI");
                        p1 = new HumanPlayer(interfejs.wczytajNick());
                        gameHistory.setPlayer1(p1.nickname);

                        p2 = new ComputerPlayer("AI Shaniqua", wyborTrudnosciBota());
                        gameHistory.setPlayer2("AI Shaniqua");
                        break;
                    case 3:
                        gameHistory.setGameMode("Tryb symulacji");

                        p1 = new ComputerPlayer("AI Thanapat", wyborTrudnosciBota());
                        gameHistory.setPlayer1("AI Thanapat");

                        p2 = new ComputerPlayer("AI Bubbles", wyborTrudnosciBota());
                        gameHistory.setPlayer2("AI Bubbles");

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
                interfejs.customisationMenu();
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
        //POMOCNICZE DO TESTOW
        // board1 = new Board(10);
        // board2 = new Board(10);
        // p1 = new ComputerPlayer("AI Thanapat", new AIHard());
        // p2 = new ComputerPlayer("AI Bubbles", new AIEasy());
        // wyborTrybuGry = 3;
        //KONIEC POMOCNICZE

        //interfejs.customisationMenu();

        int[] iloscStatkow;
        
        switch(interfejs.wyborSetupu()) 
        {
            case 1: //WYBOR STANDARDOWEGO TRYBU GRY
                iloscStatkow = new int[]{4, 3, 2, 1};
                board1 = new Board(10);
                p1.setBoard(board1);
                board2 = new Board(10);
                p2.setBoard(board2);
                if(p1 instanceof ComputerPlayer)
                    ((ComputerPlayer) p1).setPlayerBoard(board2);
                if(p2 instanceof ComputerPlayer)
                    ((ComputerPlayer) p2).setPlayerBoard(board1);
                break;
            case 2: //WYBOR TRYBU GRY Z ROZMIAREM PLANSZY I ILOSCIA STATKOW
                iloscStatkow = interfejs.wczytywanieIlosciStatkow();
                int wielkoscTablicy = interfejs.wielkoscPlanszy();
                board1 = new Board(wielkoscTablicy);
                p1.setBoard(board1);
                board2 = new Board(wielkoscTablicy);
                p2.setBoard(board2);
                if(p1 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p1).setPlayerBoard(board2);
                }
                if(p2 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p2).setPlayerBoard(board1);
                }
                break;
            default:
                throw new AssertionError();
        }

        statki = new Ship[iloscStatkow.length];
        for(int i=0 ; i<iloscStatkow.length; i++)
        {
            statki[i]=new Ship(i+1);
        }

        stawianieStatkow(p1,iloscStatkow);
        stawianieStatkow(p2,iloscStatkow);

        //POMOCNICZE DO TESTOW
        startGame();        
    }

    public void startGame() 
    {
        boolean GameOver = false;
        Player winner = null;
        while (!GameOver) {
            atak(p1, p2);
            //gameHistory.addEvent(new Event(p1.nickname, "Atakuje", ""));
            GameOver = p2.board.areAllShipsSunk();
            if(GameOver){
                winner = p1;
                break;
            }

            atak(p2, p1);
            //gameHistory.addEvent(new Event(p1.nickname, "Atakuje", ""));
            GameOver = p1.board.areAllShipsSunk();
            if(GameOver){
                winner = p2;
                break;
            }
        }
        gameHistory.setWinner(winner.nickname);
        winner.addWinCount();
        interfejs.komunikatZwyciestwo(winner);
        playerList.updateWins(winner);
    }

    private void stawianieStatkow(Player gracz, int[] liczbaStatkow) {
        boolean postawiono = false;
        int[] koordynaty;
        char kierunek;
        for (int i=0; i<liczbaStatkow.length;i++)
            for(int j=0; j<liczbaStatkow[i];j++){
            if(gracz instanceof HumanPlayer){
            while (!postawiono)
            {
                interfejs.komunikatStatek(1, statki[i].getSize());
                koordynaty = interfejs.getKoordynaty();
                kierunek = interfejs.getUstawienie();
                postawiono = gracz.placeShips(koordynaty, kierunek, statki[i]);
                if (!postawiono)
                {
                    interfejs.komunikatStatek(3, statki[i].getSize());
                }
            }
            interfejs.komunikatStatek(2, statki[i].getSize());
            postawiono = false;
            interfejs.pokazTablice(gracz);}
            else{
                interfejs.komunikatStatek(1, statki[i].getSize());
                gracz.placeShips(statki[i]);
            }

        }
        interfejs.komunikatStatek(4, 0);
        if(gracz instanceof HumanPlayer)
        interfejs.pokazTablice(gracz);
    }


    public void atak(Player atakujacy, Player atakowany) {
        int[] koordynaty = new int[2];
        boolean trafionoStatek = false;

        switch (wyborTrybuGry) {
            case 1: //HUMAN VS HUMAN
                koordynaty = interfejs.getKoordynaty();
                trafionoStatek = atakowany.makeMove(koordynaty);
                gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
                break;
            case 2: // HUMAN VS AI
                if (atakujacy instanceof ComputerPlayer) {
                    koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
                    trafionoStatek = atakowany.makeMove(koordynaty);
                    interfejs.pokazTablice(atakowany);
                    gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
                }
                else {
                    koordynaty = interfejs.getKoordynaty();
                }
                break;
            case 3: //AI VS AI
                koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
                trafionoStatek = atakowany.makeMove(koordynaty);
                interfejs.pokazTablice(atakowany);
                gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
                break;
            default:
                throw new AssertionError();
        }
    }

    // public void atakPrzeciwnik2(Player gracz, Player oponent)
    // {
    //     int[] koordynaty = new int[2];
    //     boolean trafionoStatek = false;

    //     if(gracz instanceof HumanPlayer)
    //     koordynaty=interfejs.getKoordynaty();
    //     else{
    //     koordynaty=((ComputerPlayer)gracz).attackEnemy();// i tu by sie usuneło że ai samo zaznacza dla oponenta plansze
    //     if(oponent instanceof ComputerPlayer)
    //     interfejs.komunikatySymulacji();}
        
    //     trafionoStatek = oponent.makeMove(koordynaty);
    //     interfejs.komunikatPoStrzale(trafionoStatek);
    //     interfejs.komunikatPoStrzale(koordynaty, trafionoStatek);
    // }
    
        /*public void atakPrzeciwnik2 (Player gracz, Player oponent)
        {
            int[] koordynaty = new int[2];
            boolean trafionoStatek = false;

            if (gracz instanceof HumanPlayer)
                koordynaty = interfejs.getKoordynaty();
            else {
                koordynaty = ((ComputerPlayer) gracz).attackEnemy();// i tu by sie usuneło że ai samo zaznacza dla oponenta plansze
                if (oponent instanceof ComputerPlayer)
                    interfejs.komunikatySymulacji();
            }

            trafionoStatek = oponent.makeMove(koordynaty);
            interfejs.komunikatPoStrzale(koordynaty, trafionoStatek);
        }*/
    }
