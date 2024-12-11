import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerList {
    private List<HumanPlayer> playerList = new ArrayList<>(); // tu taktyczny singletonik potem
    private HashMap<String, String> hasla = new HashMap<>();

    public PlayerList()
    {
        File file = new File("src\\PlayerList.txt");
        String nickname;
        try 
        {
            // Scanner scanner = new Scanner(file);
            // while(scanner.hasNextLine()){
            //     nickname = scanner.nextLine();
            //     playerList.add(new HumanPlayer(nickname));
            // }

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
        }
    }

    private HumanPlayer findPlayer(String nickname)
    {
        HumanPlayer pom = null;
        for(HumanPlayer player : playerList) 
        {
            if (player.nickname.equals(nickname)) 
            {
                pom = (HumanPlayer) player;
                return pom;
            }
        }
        return  pom;
    }

    public HumanPlayer logowanie(String nickname, String haslo)
    {
        HumanPlayer player = findPlayer(nickname);
        //jesli jest taki zestaw nickname i haslo, zostaje zwrocony player z listy
        if(player!=null) 
        {
            // if (hasla.containsKey(player.nickname) && hasla.get(player.nickname).equals(haslo)) 
            // {
            //     return player; trzeba dodac hasla potem
            // }
            return player;
        }

        //w innym wypadku zostaje utworzony nowy player
        HumanPlayer gracz = new HumanPlayer(nickname);
        playerList.add(gracz);
        saveToFile(gracz);
        return gracz;
    }

    public void updateWins(Player player)
    {
        int pom = 0;
        int a = 0;
        for(Player test : playerList)
        {
            if(test.nickname.equals(player.nickname))
            {
                pom = a;
                break;
            }
            a++;
        }

        try 
        {
            List<String> lines = Files.readAllLines(Paths.get("src\\PlayerList.txt"));

            // Zmień zawartość wybranej linii
            lines.set(a, "Nick: " + player.nickname + ", Ilosc zwyciestw: " + player.winCount);

            // Zapisz zmienione linie do pliku
            Files.write(Paths.get("src\\PlayerList.txt"), lines);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void saveToFile(Player player) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\PlayerList.txt",true))) 
        {
            writer.write("Nick: " + player.nickname + ", Ilosc zwyciestw: " + player.winCount);
            writer.write("\n");
            writer.close();
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }

}
