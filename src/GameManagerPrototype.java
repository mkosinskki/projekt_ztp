import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class GameManagerPrototype {
    private static GameManagerPrototype instance;
    private Player p1, p2;
    private Board board1, board2;
    private Ship[] ships;
    private Interface Interface;
    private GameHistory gameHistory;
    private PlayerList playerList;
    private int wyborTrybuGry;
    private HashMap<Integer,String> achievements;

    private GameManagerPrototype(Interface ChosenInterface) {
        gameHistory = new GameHistory();
        playerList = new PlayerList();
        this.Interface = ChosenInterface;
        achievements = new HashMap<>();
        achievements.put(1, "jestes Bogiem");
        achievements.put(2,"uswiadom to sobie");
        achievements.put(3,"wyobraz to sobie");
    }

    public static GameManagerPrototype getInstance(Interface Interface) {
        if (instance == null) {
            instance = new GameManagerPrototype(Interface);
        }
        return instance;
    }
    

    public void app()
    {
        while(true)
        {
            userMenu();
            setupGame();
            startGame();
            gameHistory.saveToFile("History.txt");
        }
    }

    public void userMenu() 
    {
        int wybor;
        wybor = Interface.menu();
        switch (wybor) 
        {
            case 1:
                wyborTrybuGry = Interface.chooseGameMode();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
                String formattedDate = now.format(formatter);
                gameHistory.setDate(formattedDate);
                switch (wyborTrybuGry) 
                {
                    case 1:
                        gameHistory.setGameMode("Gracz vs Gracz");

                        String nick1 = Interface.getNickname();
                        p1 = playerList.logowanie(nick1);
                        Interface.loggedInMessage(p1.nickname);
                        gameHistory.setPlayer1(nick1);

                        String nick2 = Interface.getNickname();
                        p2 = playerList.logowanie(nick2);
                        Interface.loggedInMessage(p2.nickname);
                        gameHistory.setPlayer2(nick2);
                        break;
                    case 2:
                        gameHistory.setGameMode("Gracz vs AI");
                        p1 = new HumanPlayer(Interface.getNickname());
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
                switch (Interface.chooseStatistics()) {
                    case 1:
                        //opcja 1 - chcesz zobaczyc statystyki konkretnego gracza:
                        String nick = Interface.getNickname();
                        Interface.showPlayer(playerList.findPlayer(nick));
                        break;
                    case 2:
                        //opcja 2 - chcesz zobaczyc statystyki wszystkich graczy:
                        Interface.showAllPlayersStatistics(playerList);
                        break;
                }
                //IMPLEMENTACJA STATYSTYK I ICH WYPISYWANIA DLA DANEGO NICKNAME'U
                break;
            case 3:
                Interface.customisationMenu();
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
        int mode = Interface.chooseAIdifficulty();
        switch (mode) 
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
        
        switch(Interface.chooseSetup())
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
                iloscStatkow = Interface.getShipCount();
                int wielkoscTablicy = Interface.getBoardSize();
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

        ships = new Ship[iloscStatkow.length];
        for(int i=0 ; i<iloscStatkow.length; i++)
        {
            ships[i]=new Ship(i+1);
        }

        placingShips(p1,iloscStatkow);
        placingShips(p2,iloscStatkow);

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
        if(winner instanceof HumanPlayer) {
            if (achievements.containsKey(winner.winCount)) //jesli w liscie kryteriow osiagniec jest liczba zwyciestw zwyciezcy
            {
                ((HumanPlayer) winner).getAchievements().put(winner.winCount, achievements.get(winner.winCount)); //dodaje osiagniecie
                Interface.achievementMessage(winner);
            }
            playerList.updateWins(winner);
        }
        Interface.winnerMessage(winner);

    }

    private void placingShips(Player player, int[] shipCount) {
        boolean placed = false;
        int[] coordinates;
        char direction;
        for (int i=0; i<shipCount.length;i++)
            for(int j=0; j<shipCount[i];j++){
            if(player instanceof HumanPlayer){
            while (!placed)
            {
                Interface.MessagesRegardingShip(1, ships[i].getSize());
                coordinates = Interface.getCoordinates();
                direction = Interface.getDirection();
                placed = player.placeShips(coordinates, direction, ships[i]);
                if (!placed)
                {
                    Interface.MessagesRegardingShip(3, ships[i].getSize());
                }
            }
            Interface.MessagesRegardingShip(2, ships[i].getSize());
            placed = false;
            Interface.showBoard(player);}
            else{
                Interface.MessagesRegardingShip(1, ships[i].getSize());
                player.placeShips(ships[i]);
            }

        }
        Interface.MessagesRegardingShip(4, 0);
        if(player instanceof HumanPlayer)
            Interface.showBoard(player);
    }



    public void atak(Player attacking, Player attacked) {
        int[] coordinates = new int[2];
        boolean shipHit = false;

        switch (wyborTrybuGry) {
            case 1: //HUMAN VS HUMAN
                coordinates = Interface.getCoordinates();
                shipHit = attacked.makeMove(coordinates);
                gameHistory.addEvent(new Event(attacking.nickname, " zaatakowal gracza " + attacked.nickname, "Strzal w pole x: " + coordinates[0] + " y: " + coordinates[1]));
                break;
            case 2: // HUMAN VS AI
                if (attacking instanceof ComputerPlayer) {
                    coordinates = ((ComputerPlayer) attacking).attackEnemy();
                    shipHit = attacked.makeMove(coordinates);
                    Interface.showBoard(attacked);
                    gameHistory.addEvent(new Event(attacking.nickname, " zaatakowal gracza " + attacked.nickname, "Strzal w pole x: " + coordinates[0] + " y: " + coordinates[1]));
                }
                else {
                    coordinates = Interface.getCoordinates();
                }
                break;
            case 3: //AI VS AI
                coordinates = ((ComputerPlayer) attacking).attackEnemy();
                shipHit = attacked.makeMove(coordinates);
                Interface.showBoard(attacked);
                gameHistory.addEvent(new Event(attacking.nickname, " zaatakowal gracza " + attacked.nickname, "Strzal w pole x: " + coordinates[0] + " y: " + coordinates[1]));
                break;
            default:
                throw new AssertionError();
        }
    }
    }
