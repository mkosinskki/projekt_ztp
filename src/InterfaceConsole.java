import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterfaceConsole extends Interface 
{
    private final Scanner scanner = new Scanner(System.in);

    private int readInt(String prompt) 
    {
        while (true) 
        {
            try 
            {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Niepoprawny format. Podaj liczbę całkowitą.");
            }
        }
    }

    private String readString(String prompt) 
    {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private char readChar(String prompt, char... validChars) 
    {
        while (true) 
        {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.length() == 1) 
            {
                char ch = input.charAt(0);
                for (char valid : validChars) 
                {
                    if (ch == valid) 
                    {
                        return ch;
                    }
                }
            }
            System.out.println("Niepoprawny znak. Spróbuj ponownie.");
        }
    }

    private char readCharCustomization(String prompt, char... invalidChars) 
    {
        boolean signChosen=false;
        while (true) 
        {
            char ch;
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (input.length() == 1) 
            {
                ch = input.charAt(0);
                signChosen=true;
                for (char invalid : invalidChars) 
                {
                    if (ch == invalid) 
                    {
                        System.out.println("Niepoprawny znak. Spróbuj ponownie.");
                        signChosen=false;
                        continue;
                    }
                }
            }
            else{
                System.out.println("Niepoprawny znak. Spróbuj ponownie.");
                continue;
            }
            if(signChosen)
            return ch;
        }
    }

    @Override
    public int menu() 
    {
        return readInt("\nMenu użytkownika\n" +
                "1) Rozpocznij nową grę\n" +
                "2) Sprawdź statystyki gracza\n" +
                "3) Customizuj planszę\n" +
                "4) Przejrzyj historie gier\n" +
                "5) Wylacz gre\n");
    }

    @Override
    public int chooseGameMode() 
    {
        return readInt("\n0) Powrot\n1) Gracz vs Gracz\n2) Gracz vs AI\n3) AI vs AI\n");
    }

    @Override
    public void showBoard(Player player, boolean displayShips)
    {
        ICustomization customisation = CustomisationConsole.getInstance(player.nickname);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < player.board.getSize(); i++) 
        {
            for (int j = 0; j < player.board.getSize(); j++) 
            {
                // Jeśli tryb wyświetlania statków jest włączony
                if (displayShips) {
                    if (player.board.getGrid()[i][j].containsShip()) {
                        sb.append(customisation.getShip()); // Wyświetl statek
                    } else {
                        sb.append(customisation.getWater()); // Wyświetl wodę
                    }
                } else {
                    // Jeśli statki nie są wyświetlane
                    if (player.board.getGrid()[i][j].isHit()) {
                        if (player.board.getGrid()[i][j].containsShip()) {
                            sb.append("X"); // Trafiony statek
                        } else {
                            sb.append("O"); // Puste pole trafione
                        }
                    } else {
                        sb.append(customisation.getWater()); // Woda na nietrafionym polu
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public int chooseAIdifficulty() 
    {
        return readInt("\nWybierz poziom trudności bota:\n1) Łatwy\n2) Średni\n3) Trudny");
    }

    @Override
    public int[] getShipCount() 
    {
        int max = readInt("Podaj długość najdłuższego statku");
        int[] ilosciStatkow = new int[max];
        for (int a = 0; a < max; a++) 
        {
            ilosciStatkow[a] = readInt("Podaj ilość statków o długości: " + (a + 1));
        }
        return ilosciStatkow;
    }

    @Override
    public String getNickname() 
    {
        return readString("Wprowadź nick gracza: ");
    }

    @Override
    public void showPlayer(Player player)
    {
        System.out.println(player.toString());
    }

    @Override
    public void showAllPlayersStatistics(PlayerList playerList) 
    {
        System.out.println(playerList.toString());
    }

    @Override
    public int chooseStatistics() 
    {
        return readInt("\n0) Powrot\n1) statystyki konkretnego gracza\n2) statystyki wszystkich\n");
    }

    @Override
    public int[] getCoordinates() 
    {
        System.out.println("Podaj koordynaty:");
        int x = readInt("Podaj współrzędną X:");
        int y = readInt("Podaj współrzędną Y:");
        return new int[]{y, x};
    }

    @Override
    public void MessagesRegardingShip(int option, int ShipSize) 
    {
        switch (option) 
        {
            case 1:
                System.out.println("Ustawianie statku o długości: " + ShipSize);
                break;
            case 2:
                System.out.println("Pomyślnie ustawiono statek o długości: " + ShipSize);
                break;
            case 3:
                System.out.println("\nBłąd stawiania statku! Zostaniesz poproszony o ponowienie operacji.\n");
                break;
            case 4:
                System.out.println("\nWszystkie statki ustawione prawidłowo\n");
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public char getDirection() 
    {
        return readChar("Podaj znak ustawienia (poziomo/pionowo): h/v", 'h', 'v');
    }

    @Override
    public void shotResultMessage(int[] coordinates, boolean hit) 
    {
        System.out.println("Strzelono w pole: " + coordinates[0] + ", " + coordinates[1]);
        if (hit) 
        {
            System.out.println("Trafiłeś w statek przeciwnika");
        } 
        else 
        {
            System.out.println("Nie trafiono w statek przeciwnika");
        }
    }

    @Override
    public void winnerMessage(Player Winner) 
    {
        System.out.println("Wygrał: " + Winner.nickname);
    }

    @Override
    public void achievementMessage(int i) 
    {
        System.out.println("Zdobyto nowe osiagniecie!: " + GameManager.achievements[i]);
        App.Delay(2000);
}

    @Override
    public int getBoardSize() 
    {
        return readInt("Podaj wielkość planszy:");
    }

    @Override
    public int chooseSetup() 
    {
        return readInt("\nWybierz tryb gry:\n1) Standardowy\n2) Własne ustawienia planszy i statków");
    }

    @Override
    public void customisationMenu(String nickname) 
    {
        ICustomization customisation = CustomisationConsole.getInstance(nickname);
        boolean menu = true;
        while (menu) 
        {
            switch (readString("Wybierz opcję:\n\t0: Personalizuj statek\n\t1: Personalizuj wodę\n\tKażdy inny znak: Wyjdź")) 
            {
                case "0":
                    customisation.setShip(readCharCustomization("Podaj znak, który ustawić jako statek", (char)customisation.getWater(), 'X')); // Przykład znaków
                    break;
                case "1":
                    customisation.setWater(readCharCustomization("Podaj znak, który ustawić jako wodę", (char)customisation.getShip(), 'X')); // Przykład znaków
                    break;
                default:
                    menu = false;
            }
        }
    }

    @Override
    public void CustomisationErrorMessage(String nick)
    {
        System.out.println("Gracz " + nick + " nie wygral jeszcze ani jednej gry aby odblokowac customizacje\n");
    }


    @Override
    public void loggedInMessage(String nick) 
    {
        System.out.println("Pomyślnie zalogowano jako: " + nick);
    }

    @Override
    public void errorMessages(int errorNumber)
    {
        switch (errorNumber) 
        {
            case 0:
                System.out.println("Podano niepoprawne koordynaty.");
                break;
            case 1:
            System.out.println("Niepoprawny wybor");
                break;
            default:
                System.out.println("Nieznany blad.");
        }
    }

    @Override
    public void settingShipsMessage(String nickname)
    {
        System.out.println("\n" + nickname + " ustawia statki.\n");
    }

    @Override
    public void showHistoryMenu(GameHistory gameHistory)
    {
        StringBuilder sb = new StringBuilder();
        int gamesNumber=0;
        try 
        {
            List<String> lines = Files.readAllLines(Paths.get("src/History.txt"));

            Pattern gameModePattern = Pattern.compile("Game Mode: (.*)");
            Pattern datePattern = Pattern.compile("Date: (.*)");
            Pattern player1Pattern = Pattern.compile("  - Player 1: (.*)");
            Pattern player2Pattern = Pattern.compile("  - Player 2: (.*)");
            String wynik;
            for (String line : lines) 
            {
                Matcher matcher = gameModePattern.matcher(line);
                if (matcher.find()) 
                {
                    wynik = matcher.group(1);
                    sb.append((gamesNumber++)+") "+wynik);
                }
                matcher = datePattern.matcher(line);
                if (matcher.find()) 
                {
                    wynik = matcher.group(1);
                    sb.append(": "+wynik+" ");
                }
                matcher = player1Pattern.matcher(line);
                if (matcher.find()) 
                {
                    wynik = matcher.group(1);
                    sb.append(wynik+" vs ");
                }
                matcher = player2Pattern.matcher(line);
                if (matcher.find()) 
                {
                    wynik = matcher.group(1);
                    sb.append(wynik+"\n");
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        int game=0;
        boolean gameChosen=false;
        while(!gameChosen)
        {
            game=readInt(sb.toString());
            if(game<gamesNumber&&game>=0)
                gameChosen=true;
        }
        historyFinder(game);
    }

    private void historyFinder(int game)
    {
        String p1=null, p2=null; 
        char[] p1Board=null,p2Board=null;
        int boardSize=0;
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/History.txt"));
            Pattern pattern = Pattern.compile("Game Mode: (.*)");
            Pattern winPattern = Pattern.compile("(.*)Action: won, Details: ");
            int foundGames=0;
            boolean playesFound=false, boardFound=false,p1Move=true, gameFound=false, end=false;
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if(!gameFound && matcher.find()){
                    if(foundGames++==game){
                        gameFound=true;
                        pattern=Pattern.compile("  - Player 1: (.*)");}
                }
                else if(!playesFound && matcher.find()){
                    if(p1==null){
                        p1=matcher.group(1);
                        pattern=Pattern.compile("  - Player 2: (.*)");
                    }
                    else{
                        p2=matcher.group(1);
                        playesFound=true;
                        pattern=Pattern.compile("(.*)Action: uzupelnil plansze, Details: (.*)");}
                }
                else if(!boardFound && matcher.find()){
                    if(p1Board==null){
                        p1Board=matcher.group(2).toCharArray();
                        boardSize = (int)Math.sqrt(matcher.group(2).length());
                        System.out.print("Aby wyjsc wpisz 1\nKazdy inny przycisk powoduje przejscie dalej\n");
                        end=scanner.nextLine().equals("1");
                        if(end) break;
                        drawHistoryBoard(p1Board, boardSize);
                        System.out.print("Plansza gracza "+p1+"\n");
                        end=scanner.nextLine().equals("1");}
                    else{
                        p2Board=matcher.group(2).toCharArray();
                        boardFound=true;
                        drawHistoryBoard(p2Board, boardSize);
                        System.out.print("Plansza gracza "+p2+"\n");
                        end=scanner.nextLine().equals("1");
                        pattern=Pattern.compile("(.*)Action: zaatakowal gracza (.*), Details: Strzal w pole x: (\\d+) y: (\\d+)");}
                }
                else if(matcher.find()){
                    if(p1Move){
                        p2Board[Integer.parseInt(matcher.group(3))*boardSize+Integer.parseInt(matcher.group(4))]='X';
                        drawHistoryBoard(p2Board, boardSize);
                        System.out.print("Atak gracza "+p1+" na gracza "+p2+"\n");
                        p1Move=false;
                    }
                    else{
                        p1Board[Integer.parseInt(matcher.group(3))*boardSize+Integer.parseInt(matcher.group(4))]='X';
                        drawHistoryBoard(p1Board, boardSize);
                        System.out.print("Atak gracza "+p2+" na gracza "+p1+"\n");
                        p1Move=true;
                    }
                    end=scanner.nextLine().equals("1");
                }
                if(gameFound && (winPattern.matcher(line).find())){
                    System.out.print("Zwyciestwo gracza "+(p1Move?p2:p1)+"\n");
                    end=scanner.nextLine().equals("1");
                    break;
                }
                if(end) break;

            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawHistoryBoard(char[] board, int size)
    {
        for(int x=0;x<size;x++)
        {
            for(int y=0;y<size;y++)
            {
                System.out.print(board[x*size+y]);
            }
        System.out.print("\n");
        }
    }

    @Override
    public int choosePlayerToCheck()
    {
        return readInt("\n0) Powrot\n1) Podaj nick gracza którego plansza bedzie customizowana\n");
    }

    @Override
    public void attackingStageMessage() {
        System.out.println("Faza atakowania:");
    }

    @Override
    public ICustomization getCustomization(String nick)
    {
        return CustomisationConsole.getInstance(nick);
    }
}