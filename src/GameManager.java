import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameManager {
    public final static String[] achievements = {"1 win", "5 wins", "30 ships placed", "you hit an enemy 20 times"};
    public final static IObserver[] usableObservers = 
    {
        new OneWinObserver(),
        new FiveOrMoreWinsObserver(),
        new ShipsPlacedObserver(),
        new EnemiesHitObserver()
    };
    private static GameManager instance;
    private Player p1, p2;
    private Ship[] ships;
    public Interface myInterface;
    private GameHistory gameHistory;
    private PlayerList playerList;
    private int chooseGameMode;

    private GameManager(Interface ChosenInterface)
    {
        gameHistory = new GameHistory();
        playerList = new PlayerList();
        this.myInterface = ChosenInterface;
    }

    public void updateAllPlayers()
    {
        playerList.updateAllPlayersInList();
            }

    public static GameManager getInstance(Interface myInterface) 
    {
        if (instance == null) 
        {
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
        while (true) 
        { 
            wybor = myInterface.menu();
            switch (wybor) 
            {
                case 1:
                    chooseGameMode = myInterface.chooseGameMode();
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
                    String formattedDate = now.format(formatter);
                    gameHistory.setDate(formattedDate);
                    boolean valid = false;

                    while(!valid)
                    {
                        switch (chooseGameMode) 
                        {
                            case 0:
                                userMenu();
                            case 1:
                                gameHistory.setGameMode("Gracz vs Gracz");

                                String nick1 = myInterface.getNickname();
                                p1 = playerList.loggingIn(nick1);
                                myInterface.loggedInMessage(p1.nickname);

                                String nick2 = myInterface.getNickname();
                                p2 = playerList.loggingIn(nick2);
                                myInterface.loggedInMessage(p2.nickname);
                                valid = true;
                                break;
                            case 2:
                                gameHistory.setGameMode("Gracz vs AI");
                                String nick3 = myInterface.getNickname();
                                p1 = playerList.loggingIn(nick3);
                                myInterface.loggedInMessage(p1.nickname);

                                p2 = new ComputerPlayer("AI Shaniqua", chooseBotDifficulty());
                                valid = true;
                                break;
                            case 3:
                                gameHistory.setGameMode("Tryb symulacji");

                                p1 = new ComputerPlayer("AI Thanapat", chooseBotDifficulty());

                                p2 = new ComputerPlayer("AI Bubbles", chooseBotDifficulty());
                                valid = true;
                                break;
                            default:
                                myInterface.errorMessages(1);
                                chooseGameMode = myInterface.chooseGameMode();
                                break;
                        }
                    }
                    gameHistory.setPlayer1(p1.nickname);
                    gameHistory.setPlayer2(p2.nickname);
                    setupGame();
                    startGame();
                    break;
                case 2:
                    switch (myInterface.chooseStatistics()) 
                    {
                        case 0:
                            userMenu();
                        case 1:
                            String nick = myInterface.getNickname();
                            myInterface.showPlayer(playerList.findPlayer(nick));
                            break;
                        case 2:
                            myInterface.showAllPlayersStatistics(playerList);
                            break;
                        default:
                            myInterface.errorMessages(1);
                            wybor = 2;
                            break;
                    }
                    break;
                case 3:
                    switch(myInterface.choosePlayerToCheck())
                    {
                        case 0:
                            userMenu();
                        case 1:
                            String nick = myInterface.getNickname();
                            if (playerList.findPlayer(nick).getMyAchievements(0)) 
                            {
                                myInterface.customisationMenu(nick);
                            } 
                            else 
                            {
                                myInterface.CustomisationErrorMessage(nick);
                            }
                            break;
                        default:
                        myInterface.errorMessages(1);
                        break;
                    }
                    break;
                case 4:
                    myInterface.showHistoryMenu(gameHistory);
                    break;
                case 5:
                    System.exit(1);
                    break;
                default:
                    myInterface.errorMessages(1);
                    break;
            }
        }
    }

    public AIStrategy chooseBotDifficulty() 
    {
        int mode = myInterface.chooseAIdifficulty();
        switch (mode)
        {
            case 1:
                return new AIEasy();
            case 2:
                return new AIMedium();
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
        myInterface.settingShipsMessage(p1.getNickname());
        placingShips(p1,shipCount);
        myInterface.settingShipsMessage(p2.getNickname());
        placingShips(p2,shipCount);      
    }

    public void startGame() 
    {
        boolean GameOver = false;
        Player winner = null;
        myInterface.attackingStageMessage();
        while (!GameOver) 
        {
            attack(p1, p2);
            GameOver = p2.board.areAllShipsSunk();
            if(GameOver)
            {
                winner = p1;
                break;
            }

            attack(p2, p1);
            GameOver = p1.board.areAllShipsSunk();
            if(GameOver)
            {
                winner = p2;
                break;
            }
        }
        gameHistory.setWinner(winner.nickname);
        winner.addWinCount();
        gameHistory.addEvent(new Event(winner.nickname,"won",""));
        if(p1 instanceof HumanPlayer)
        {
            playerList.updateWins(p1);
        }
        if(p2 instanceof HumanPlayer)
        {
            playerList.updateWins(p2);
        }
        myInterface.winnerMessage(winner);
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
                    myInterface.showBoard(player, true);
                    while (!placed)
                    {
                        myInterface.MessagesRegardingShip(1, ships[i].getSize());
                        coordinates = myInterface.getCoordinates();
                        if(ships[i].getSize() == 1)
                        {
                            direction = 'h';
                        }
                        else
                        {
                            direction = myInterface.getDirection();
                        }
                        placed = player.placeShips(coordinates, direction, ships[i]);
                        if (!placed)
                        {
                            myInterface.MessagesRegardingShip(3, ships[i].getSize());
                        }
                    }
                    myInterface.MessagesRegardingShip(2, ships[i].getSize());
                    placed = false;

                }
                else
                {
                    myInterface.MessagesRegardingShip(1, ships[i].getSize());
                    while (!placed)
                    {
                        coordinates = ((ComputerPlayer)player).getShipPlacement();
                        direction = ((ComputerPlayer)player).getShipDirection();
                        placed = player.placeShips(coordinates, direction, ships[i]);
                    }
                    placed = false;
                }
            }
        }
        myInterface.MessagesRegardingShip(4, 0);
        gameHistory.addEvent(new Event(player.nickname, "uzupelnil plansze", 
                                            player.board.getBoardForHistory(player)));
        if(player instanceof HumanPlayer)
        {
            myInterface.showBoard(player, true);
        }
        else if(p1 instanceof ComputerPlayer && p2 instanceof ComputerPlayer)
        {
            myInterface.showBoard(player, true);
            App.Delay(1500);
        }
    }

    public void attack(Player player, Player opponent)
    {
        int[] coordinates = new int[2];
        boolean hitShip = false, isPlayerHuman = player instanceof HumanPlayer;
        boolean poprawneKoordynaty = false;

        System.out.println("\n=========================================");
        System.out.println("Gracz: " + player.getNickname() + " atakuje\n");

        if(isPlayerHuman)//gdy człowiek
        {
            myInterface.showBoard(opponent, false);
            coordinates=myInterface.getCoordinates();
            System.out.println("=========================================\n");
            while(!poprawneKoordynaty)
            {
                if(coordinates[0] < player.board.getSize() && coordinates[1] < player.board.getSize() && coordinates[0] >= 0 && coordinates[1] >= 0)
                {
                    poprawneKoordynaty = true;
                }
                else
                {
                    myInterface.errorMessages(0);
                    coordinates=myInterface.getCoordinates(); 
                }
            }
        }
        else//gdy AI
        {
            coordinates=((ComputerPlayer)player).attackEnemy();
            System.out.println("=========================================\n");
        }

        hitShip = opponent.board.markShot(coordinates[0],coordinates[1]);

        if(!isPlayerHuman)
        {
            myInterface.shotResultMessage(coordinates, hitShip);
            myInterface.showBoard(opponent, false);
            App.Delay(2500);
        }
        else
        {
            ((HumanPlayer)player).updateHits(hitShip);
            myInterface.shotResultMessage(coordinates, hitShip);
        }
        gameHistory.addEvent(new Event(player.nickname, "zaatakowal gracza " + opponent.nickname, "Strzal w pole x: " + coordinates[0] + " y: " + coordinates[1]));
    }
}
