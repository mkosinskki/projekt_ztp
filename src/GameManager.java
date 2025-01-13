import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameManager {
    public final static String[] achievements = {"1 win", "5 wins", "30 ships placed", "you hit an enemy 20 times"};
    public final static IObserver[] usableObservers = {new OneWinObserver(),
        new FiveOrMoreWinsObserver(),
        new ShipsPlacedObserver(),
        new EnemiesHitObserver()};
    private static GameManager instance;
    private Player p1, p2;
    private Ship[] ships;
    public Interface myInterface;
    private GameHistory gameHistory;
    private PlayerList playerList;
    private int wyborTrybuGry;

    private GameManager(Interface ChosenInterface){
        gameHistory = new GameHistory();
        playerList = new PlayerList();
        this.myInterface = ChosenInterface;
    }

    public void updateAllPlayers(){
        playerList.updateAllPlayersInList();
            }

    public static GameManager getInstance(Interface myInterface) {
        if (instance == null) {
            instance = new GameManager(myInterface);
        }
        return instance;
    }
    
    public void app()
    {
        while(true)
        {
            userMenu();
            gameHistory.saveToFile("src/History.txt");
        }
    }

    public void userMenu()
    {
        int wybor;
        wybor = myInterface.menu();
        switch (wybor) 
        {
            case 1:
                wyborTrybuGry = myInterface.chooseGameMode();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
                String formattedDate = now.format(formatter);
                gameHistory.setDate(formattedDate);
                switch (wyborTrybuGry) 
                {
                    case 1:
                        gameHistory.setGameMode("Gracz vs Gracz");

                        String nick1 = myInterface.getNickname();
                        p1 = playerList.loggingIn(nick1);
                        myInterface.loggedInMessage(p1.nickname);

                        String nick2 = myInterface.getNickname();
                        p2 = playerList.loggingIn(nick2);
                        myInterface.loggedInMessage(p2.nickname);
                        break;
                    case 2:
                        gameHistory.setGameMode("Gracz vs AI");
                        p1 = new HumanPlayer(myInterface.getNickname());

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
                startGame();
                break;
            case 2:
                switch (myInterface.chooseStatistics()) 
                {
                    case 1:
                        //opcja 1 - chcesz zobaczyc statystyki konkretnego gracza:
                        String nick = myInterface.getNickname();
                        myInterface.showPlayer(playerList.findPlayer(nick));
                        break;
                    case 2:
                        //opcja 2 - chcesz zobaczyc statystyki wszystkich graczy:
                        myInterface.showAllPlayersStatistics(playerList);
                        break;
                }
                //IMPLEMENTACJA STATYSTYK I ICH WYPISYWANIA DLA DANEGO NICKNAME'U
                break;
            case 3:
                String nick = myInterface.getNickname();
                if(playerList.findPlayer(nick).getMyAchievements(0))
                {
                    myInterface.customisationMenu();
                }
                else
                {
                    myInterface.CustomisationErrorMessage(nick);
                }
                //DO IMPLEMENTACJI CUSTOMIZACJA PLANSZY (W TYM WYPADKU ZMIANA ZNAKOW STATKOW LUB WODY)
                break;
            case 4:
                //DO IMPLEMENTACJI HISTORII GRY
                break;
            case 5:
                System.exit(1);
                break;
            default:
                throw new AssertionError();
        }
    }

    public AIStrategy wyborTrudnosciBota() 
    {
        int mode = myInterface.chooseAIdifficulty();
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
        int[] shipCount;
        
        switch(myInterface.chooseSetup())
        {
            case 1: //WYBOR STANDARDOWEGO TRYBU GRY
                shipCount = new int[]{4, 3, 2, 1};
                p1.setBoard(new Board(10));
                p2.setBoard(new Board(10));
                if(p1 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p1).setPlayerBoard(p2.board);
                }
                if(p2 instanceof ComputerPlayer)
                {
                    ((ComputerPlayer) p2).setPlayerBoard(p1.board);
                }
                break;
            case 2: //WYBOR TRYBU GRY Z ROZMIAREM PLANSZY I ILOSCIA STATKOW
                shipCount = myInterface.getShipCount();
                int boardSize = myInterface.getBoardSize();
                p1.setBoard(new Board(boardSize));
                p2.setBoard(new Board(boardSize));
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

        ships = new Ship[shipCount.length];
        for(int i=0 ; i<shipCount.length; i++)
        {
            ships[i]=new Ship(i+1);
        }

        placingShips(p1,shipCount);
        placingShips(p2,shipCount);      
    }

    public void startGame() 
    {
        boolean GameOver = false;
        Player winner = null;
        while (!GameOver) 
        {
            atak(p1, p2);
            //gameHistory.addEvent(new Event(p1.nickname, "Atakuje", ""));
            GameOver = p2.board.areAllShipsSunk();
            if(GameOver)
            {
                winner = p1;
                break;
            }

            atak(p2, p1);
            //gameHistory.addEvent(new Event(p1.nickname, "Atakuje", ""));
            GameOver = p1.board.areAllShipsSunk();
            if(GameOver)
            {
                winner = p2;
                break;
            }
        }
        gameHistory.setWinner(winner.nickname);
        winner.addWinCount();

        if(winner instanceof HumanPlayer)
        {
            playerList.updateWins(winner);
        }

        myInterface.winnerMessage(winner);
        gameHistory.exportHistory();
        gameHistory.saveToFile("src/History.txt");
    }

    private void placingShips(Player player, int[] shipCount) 
    {
        boolean placed = false;
        int[] coordinates;
        char direction;
        for (int i=0; i<shipCount.length;i++)
        {
            for(int j=0; j<shipCount[i];j++)
            {
                if(player instanceof HumanPlayer)
                {
                    while (!placed)
                    {
                        myInterface.MessagesRegardingShip(1, ships[i].getSize());
                        coordinates = myInterface.getCoordinates();
                        direction = myInterface.getDirection();
                        placed = player.placeShips(coordinates, direction, ships[i]);
                        if (!placed)
                        {
                            myInterface.MessagesRegardingShip(3, ships[i].getSize());
                        }
                    }
                    myInterface.MessagesRegardingShip(2, ships[i].getSize());
                    placed = false;
                    myInterface.showBoard(player);
                }
                else
                {
                    myInterface.MessagesRegardingShip(1, ships[i].getSize());
                    player.placeShips(ships[i]);
                }
            }
        }
        myInterface.MessagesRegardingShip(4, 0);
        if(player instanceof HumanPlayer)
        {
            myInterface.showBoard(player);
        }
    }

    public void atak(Player player, Player opponent)
    {
        int[] coordinates = new int[2];
        boolean hitShip = false, isPlayerHuman = player instanceof HumanPlayer;

        if(isPlayerHuman)//gdy człowiek
        {
            coordinates=myInterface.getCoordinates(); 
        }
        else//gdy AI
        {
            coordinates=((ComputerPlayer)player).attackEnemy();// i tu by sie usuneło że ai samo zaznacza dla oponenta plansze
        }
        hitShip = opponent.board.markShot(coordinates[0],coordinates[1]);
        if(!isPlayerHuman)
        {
            myInterface.showBoard(opponent);
            myInterface.shotResultMessage(coordinates, hitShip);
            App.Delay(2500);
        }
        else
        {
            ((HumanPlayer)player).updateHits(hitShip);
            myInterface.shotResultMessage(coordinates, hitShip);
        }
        gameHistory.addEvent(new Event(player.nickname, " zaatakowal gracza " + opponent.nickname, "Strzal w pole x: " + coordinates[0] + " y: " + coordinates[1]));
    }
}
