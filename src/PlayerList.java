import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerList {
    private List<HumanPlayer> playerList = new ArrayList<>();

    public PlayerList()
    {
        File file = new File("src\\PlayerList.txt");
        String nickname;
       /* try
        {
            // Scanner scanner = new Scanner(file);
            // while(scanner.hasNextLine()){
            //     nickname = scanner.nextLine();
            //     playerList.add(new HumanPlayer(nickname));
            // }


            List<String> lines = Files.readAllLines(Paths.get("src\\PlayerList.txt"));

            Pattern pattern = Pattern.compile(
                    "Nick: (.*?), Ilosc zwyciestw: (\d+), Osiagniecia: (.+)"
            );

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String nick = matcher.group(1);
                    int wins = Integer.parseInt(matcher.group(2));
                    String achievementsRaw = matcher.group(3);

                    HashMap<Integer,String> achievements = new HashMap<>();
                    Pattern achievementPattern = Pattern.compile("\d+ "([^"]+)"");
                    Matcher achievementMatcher = achievementPattern.matcher(achievementsRaw);
                    while (achievementMatcher.find()) {
                        achievements.add(achievementMatcher.group(1));
                    }
                    HumanPlayer pom = new HumanPlayer(nick);
                    pom.setWins(wins);
                    pom.setAchievementList(achievements);
                    playerList.add(pom);
                }
            }

            //return playerList;

            //chuj
            List<String> lines = Files.readAllLines(Paths.get("src\\PlayerList.txt"));

            for (String line : lines)
            {
                String[] parts = line.split(", ");
                String nick = parts[0].split(": ")[1];
                int wins = Integer.parseInt(parts[1].split(": ")[1]);
                HumanPlayer pom = new HumanPlayer(nick);
                pom.setWins(wins);
                playerList.add(pom);
                System.out.println(pom.nickname + "\n");
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }*/
        try {
            // Wczytaj linie z pliku
            List<String> lines = Files.readAllLines(Paths.get("src/PlayerList.txt"));

            // Wzorzec dla linii gracza
            Pattern pattern = Pattern.compile(
                    "Nick: (.*?), Ilosc zwyciestw: (\\d+), Trafione pola: (\\d+), Postawione statki: (\\d+), Osiagniecia: (.*)"
            );

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String nick = matcher.group(1);
                    int wins = Integer.parseInt(matcher.group(2));
                    int fieldsHit = Integer.parseInt(matcher.group(3));
                    int placedShips = Integer.parseInt(matcher.group(4));

                    String achievementsRaw = matcher.group(5).trim();

                    // Tworzenie osiągnieć
                    boolean[] achievements = new boolean[GameManager.achievements.length];
                    if (!achievementsRaw.isEmpty()) {
                        Pattern achievementPattern = Pattern.compile("(\\d+) \"([^\"]+)\"");
                        Matcher achievementMatcher = achievementPattern.matcher(achievementsRaw);

                        while (achievementMatcher.find()) {
                            int id = Integer.parseInt(achievementMatcher.group(1));
                            achievements[id]=true;
                        }
                    }

                    // Tworzenie obiektu HumanPlayer
                    HumanPlayer pom = new HumanPlayer(nick);
                    pom.setWins(wins);
                    pom.setAchievementList(achievements);
                    pom.setRecords(fieldsHit,placedShips);
                    playerList.add(pom);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HumanPlayer findPlayer(String nickname)
    {
        HumanPlayer temp = null;
        for(HumanPlayer player : playerList) 
        {
            if (player.nickname.equals(nickname)) 
            {
                temp = (HumanPlayer) player;
                return temp;
            }
        }
        return  temp;
    }

    public HumanPlayer loggingIn(String nickname)
    {
        HumanPlayer player = findPlayer(nickname);
        //jesli jest taki nickname w bazie, zostaje zwrocony player z listy
        if(player!=null)
        {
            return player;
        }
        //w innym wypadku zostaje utworzony nowy player
        HumanPlayer newPlayer = new HumanPlayer(nickname);
        playerList.add(newPlayer);
        saveToFile(newPlayer);
        return newPlayer;
    }

    public void updateAllPlayersInList(){
        for (HumanPlayer player : playerList) {
            updateWins(player);
        }
    }

    public void updateWins(Player player)
    {

        int temp = 0;
        for(Player test : playerList)
        {
            if(test.nickname.equals(player.nickname))
            {
                break;
            }
            temp++;
        }

        try 
        {
            List<String> lines = Files.readAllLines(Paths.get("src\\PlayerList.txt"));

            // Zmień zawartość wybranej linii (linii na ktorej zapisany jest wybrany gracz)
            lines.set(temp, player.toString());

            // Zapisz zmienione linie do pliku
            Files.write(Paths.get("src\\PlayerList.txt"), lines);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void saveToFile(HumanPlayer player)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\PlayerList.txt",true))) 
        {
            //writer.write("Nick: " + player.nickname + ", Ilosc zwyciestw: " + player.winCount + ", Osiagniecia: " + osiagniecia);
            writer.write(player.toString());
            writer.write("\n");
            writer.close();
        } 
        catch (IOException e) 
        {
            System.err.println("Błąd w zapisywaniu historii gry: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(HumanPlayer player: playerList)
        {
            sb.append(player.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
