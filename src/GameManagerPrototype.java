import java.util.Scanner;

public class GameManagerPrototype {
    private static GameManagerPrototype instance;
    private Player p1, p2;
    private Board board1, board2;
    private Ship[] statki;
    private Interface interfejs;
    private GameHistory gameHistory;
    private int wyborTrybuGry;

    private GameManagerPrototype(Interface interfejs) {
        gameHistory = new GameHistory();
        this.interfejs = interfejs;
    }

    public static GameManagerPrototype getInstance(Interface interfejs) {
        if (instance == null) {
            instance = new GameManagerPrototype(interfejs);
        }
        return instance;
    }

    public void przedGra() {
        int wybor;
        wybor = interfejs.menu();
        switch (wybor) {
            case 1:
                wyborTrybuGry = interfejs.wyborTrybuGry();
                switch (wyborTrybuGry) {
                    case 1:
                        p1 = new HumanPlayer(interfejs.wczytajNick(),board1);
                        gameHistory.addEvent(p1.toString(),"Wczytanie gracza", "");
                        p2 = new HumanPlayer(interfejs.wczytajNick(), board2);
                        gameHistory.addEvent(p2.toString(),"Wczytanie gracza", "");
                        break;
                    case 2:
                        p1 = new HumanPlayer(interfejs.wczytajNick(), board1);
                        gameHistory.addEvent(p1.toString(),"Wczytanie gracza", "");
                        p2 = new ComputerPlayer("AI Shaniqua",board2, board1, wyborTrudnosciBota());
                        gameHistory.addEvent("AI Shaniqua","Wczytanie AI", "");
                        break;
                    case 3:
                        p1 = new ComputerPlayer("AI Thanapat",board1, board2, wyborTrudnosciBota());
                        gameHistory.addEvent("AI Thanapat","Wczytanie AI", "");
                        p2 = new ComputerPlayer("AI Bubbles",board2, board1, wyborTrudnosciBota());
                        gameHistory.addEvent("AI Bubbles","Wczytanie AI", "");
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

    public AIStrategy wyborTrudnosciBota() {
        int trudnosc = interfejs.wyborTrudnosciBota();
        switch (trudnosc) {
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

    public void setupGame() {
        int[] iloscStatkow = interfejs.wczytywanieIlosciStatkow();
        stawianieStatkow(p1);
        stawianieStatkow(p2);
    }

    public void startGame() {
        boolean GameOver = false;
        Player winner = null;
        while (!GameOver) {
            atak(p1, p2);
            GameOver = p2.board.areAllShipsSunk();
            if(GameOver){
                winner = p1;
                break;
            }

            atak(p2, p1);
            GameOver = p1.board.areAllShipsSunk();
            if(GameOver){
                winner = p2;
                break;
            }
        }
        interfejs.komunikatZwyciestwo(winner);
    }

    private void stawianieStatkow(Player gracz) {
        boolean postawiono = false;
        int[] koordynaty;
        char kierunek;

        for (Ship statek : statki)
        {
            if(gracz instanceof HumanPlayer){
            while (!postawiono)
            {
                interfejs.komunikatStatek(1, statek.getSize());
                koordynaty = interfejs.getKoordynaty();
                kierunek = interfejs.getUstawienie();
                postawiono = gracz.placeShips(koordynaty, kierunek, statek);
                if (!postawiono)
                {
                    interfejs.komunikatStatek(3, statek.getSize());
                }
            }
            interfejs.komunikatStatek(2, statek.getSize());
            postawiono = false;
            interfejs.pokazTablice(gracz.board);}
            else{
                interfejs.komunikatStatek(1, statek.getSize());
                gracz.placeShips(statek);
            }

        }
        interfejs.komunikatStatek(4, 0);
        if(gracz instanceof HumanPlayer)
        interfejs.pokazTablice(gracz.board);
    }


    public void atak(Player atakujacy, Player atakowany) {
        int[] koordynaty = new int[2];
        boolean trafionoStatek = false;

        switch (wyborTrybuGry) {
            case 1: //HUMAN VS HUMAN
                koordynaty = interfejs.getKoordynaty();
                trafionoStatek = atakowany.makeMove(koordynaty);
                break;
            case 2: // HUMAN VS AI
                if (atakujacy instanceof ComputerPlayer) {
                    koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
                    trafionoStatek = atakowany.makeMove(koordynaty);
                    interfejs.pokazTablice(atakowany.board);
                }
                else {
                    koordynaty = interfejs.getKoordynaty();
                }
                break;
            case 3: //AI VS AI
                koordynaty = ((ComputerPlayer) atakujacy).attackEnemy();
                trafionoStatek = atakowany.makeMove(koordynaty);
                interfejs.pokazTablice(atakowany.board);
                break;
            default:
                throw new AssertionError();
        }
        interfejs.komunikatPoStrzale(koordynaty, trafionoStatek);
    }
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
