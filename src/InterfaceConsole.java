import java.util.Scanner;

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
        return readInt("\n1) Gracz vs Gracz\n2) Gracz vs AI\n3) AI vs AI");
    }

    @Override
    public void showBoard(Player player) 
    {
        CustomisationConsole customisation = CustomisationConsole.getInstance(player.nickname);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < player.board.getSize(); i++) 
        {
            for (int j = 0; j < player.board.getSize(); j++) 
            {
                if(!player.board.getGrid()[i][j].isHit())
                sb.append(player.board.getGrid()[i][j].containsShip() ? customisation.getShip() : customisation.getWater());
                else
                sb.append("X");
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
        return readInt("\nwybierz opcje\n1-statystyki konkretnego gracza\n2-statystyki wszystkich\n");
    }

    @Override
    public int[] getCoordinates() 
    {
        System.out.println("Podaj koordynaty:");
        int x = readInt("Podaj współrzędną X:");
        int y = readInt("Podaj współrzędną Y:");
        return new int[]{x, y};
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
                System.out.println("Błąd stawiania statku! Zostaniesz poproszony o ponowienie operacji.");
                break;
            case 4:
                System.out.println("Wszystkie statki ustawione prawidłowo");
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
        System.out.println("Wygrał: " + Winner.toString());
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
    public void customisationMenu() 
    {
        CustomisationConsole customisation = CustomisationConsole.getInstance(getNickname());
        boolean menu = true;
        while (menu) 
        {
            switch (readString("Wybierz opcję:\n\t0: Personalizuj statek\n\t1: Personalizuj wodę\n\tKażdy inny znak: Wyjdź")) 
            {
                case "0":
                    customisation.setShip(readChar("Podaj znak, który ustawić jako statek", 's', '#')); // Przykład znaków
                    break;
                case "1":
                    customisation.setWater(readChar("Podaj znak, który ustawić jako wodę", '~', '.')); // Przykład znaków
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
    public void errorMesseges(int errorNumber)
    {
        switch (errorNumber) 
        {
            case 0:
                System.out.println("Podano niepoprawne koordynaty.");
                break;
            case 1:
                break;
            default:
                System.out.println("Nieznany blad.");
        }
    }
}