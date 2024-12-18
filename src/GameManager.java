import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameManager {
    public final static String[] osiagniecia = {"1 win", "5 wins", "30 ships placed", "you hit an enemy 20 times"};
    private static GameManager instance;
    private Player p1, p2;
    private Ship[] statki;
    public final Interface interfejs;
    private GameHistory gameHistory;
    private PlayerList playerList;
    private int wyborTrybuGry;

    private GameManager(Interface interfejs) {
        gameHistory = new GameHistory();
        playerList = new PlayerList();
        this.interfejs = interfejs;
    }

    public void updateAllPlayers(){
        playerList.updateAllPlayersInList();
    }

    public static GameManager getInstance(Interface interfejs) {
        if (instance == null) {
            instance = new GameManager(interfejs);
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

                        String nick2 = interfejs.wczytajNick();
                        p2 = playerList.logowanie(nick2,"kkk");
                        interfejs.komunikatLogowanie(p2.nickname);
                        break;
                    case 2:
                        gameHistory.setGameMode("Gracz vs AI");
                        p1 = new HumanPlayer(interfejs.wczytajNick());

                        p2 = new ComputerPlayer("AI Shaniqua", wyborTrudnosciBota());
                        break;
                    case 3:
                        gameHistory.setGameMode("Tryb symulacji");

                        p1 = new ComputerPlayer("AI Thanapat", wyborTrudnosciBota());

                        p2 = new ComputerPlayer("AI Bubbles", wyborTrudnosciBota());

                        break;
                    default:
                        throw new AssertionError();
                }
                gameHistory.setPlayer1(p1.nickname);
                gameHistory.setPlayer2(p2.nickname);
                setupGame();
                break;
            case 2:
                switch (interfejs.wyborStatystyk()) {
                    case 1:
                        //opcja 1 - chcesz zobaczyc statystyki konkretnego gracza:
                        String nick = interfejs.wczytajNick();
                        interfejs.komunikatGracz(playerList.findPlayer(nick));
                        break;
                    case 2:
                        //opcja 2 - chcesz zobaczyc statystyki wszystkich graczy:
                        interfejs.komunikatStatystykiWszystkich(playerList);
                        break;
                }
                //IMPLEMENTACJA STATYSTYK I ICH WYPISYWANIA DLA DANEGO NICKNAME'U
                break;
            case 3:
                String nick = interfejs.wczytajNick();
                if(playerList.findPlayer(nick).getOsiagniecie(0))
                    interfejs.customisationMenu(nick);
                else
                interfejs.bladCustomizacji(nick);
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
                p1.setBoard(new Board(10));
                p2.setBoard(new Board(10));
                if(p1 instanceof ComputerPlayer)
                    ((ComputerPlayer) p1).setPlayerBoard(p2.board);
                if(p2 instanceof ComputerPlayer)
                    ((ComputerPlayer) p2).setPlayerBoard(p1.board);
                break;
            case 2: //WYBOR TRYBU GRY Z ROZMIAREM PLANSZY I ILOSCIA STATKOW
                iloscStatkow = interfejs.wczytywanieIlosciStatkow();
                int wielkoscTablicy = interfejs.wielkoscPlanszy();
                p1.setBoard(new Board(wielkoscTablicy));
                p2.setBoard(new Board(wielkoscTablicy));
                if(p1 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p1).setPlayerBoard(p2.board);
                }
                if(p2 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p2).setPlayerBoard(p1.board);
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
        if(winner instanceof HumanPlayer)
            playerList.updateWins(winner);
        interfejs.komunikatZwyciestwo(winner);
        gameHistory.exportHistory();
        gameHistory.saveToFile("History.txt");
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

    // public void atak(Player atakujacy, Player atakowany) {
    //     int[] koordynaty = new int[2];
    //     boolean trafionoStatek = false;

    //     switch (wyborTrybuGry) {
    //         case 1: //HUMAN VS HUMAN
    //             koordynaty = interfejs.getKoordynaty();
    //             trafionoStatek = atakowany.makeMove(koordynaty);
    //             gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
    //             break;
    //         case 2: // HUMAN VS AI
    //             if (atakujacy instanceof ComputerPlayer) {
    //                 koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
    //                 trafionoStatek = atakowany.makeMove(koordynaty);
    //                 interfejs.pokazTablice(atakowany);
    //                 gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
    //             }
    //             else {
    //                 koordynaty = interfejs.getKoordynaty();
    //             }
    //             break;
    //         case 3: //AI VS AI
    //             koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
    //             trafionoStatek = atakowany.makeMove(koordynaty);
    //             interfejs.pokazTablice(atakowany);
    //             gameHistory.addEvent(new Event(atakujacy.nickname, " zaatakowal gracza " + atakowany.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
    //             break;
    //         default:
    //             throw new AssertionError();
    //     }
    // }

    public void atak(Player gracz, Player oponent)
    {
        int[] koordynaty = new int[2];
        boolean trafionoStatek = false, isPlayerHuman = gracz instanceof HumanPlayer;

        if(isPlayerHuman)//gdy człowiek
        koordynaty=interfejs.getKoordynaty();
        else//gdy AI
        koordynaty=((ComputerPlayer)gracz).attackEnemy();// i tu by sie usuneło że ai samo zaznacza dla oponenta plansze
        // if(oponent instanceof ComputerPlayer)    //chyba nie potrzebne bo AI vs AI i tak to samo wypisuje
        trafionoStatek = oponent.makeMove(koordynaty);
        if(!isPlayerHuman)
        interfejs.pokazTablice(oponent);
        else
        ((HumanPlayer)gracz).updateHits(trafionoStatek);
        gameHistory.addEvent(new Event(gracz.nickname, " zaatakowal gracza " + oponent.nickname, "Strzal w pole x: " + koordynaty[0] + " y: " + koordynaty[1]));
        interfejs.komunikatPoStrzale(koordynaty, trafionoStatek);
    }    
}
