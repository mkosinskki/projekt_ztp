import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    private List<HumanPlayer> playerList = new ArrayList<>(); // tu taktyczny singletonik potem
    private HashMap<String, String> hasla = new HashMap<>();

    public PlayerList()
    {
        File file = new File("src\\PlayerList.txt");
        String nickname;
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                nickname = scanner.nextLine();
                playerList.add(new HumanPlayer(nickname));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private HumanPlayer findPlayer(String nickname){
        HumanPlayer pom = null;
        for(HumanPlayer player : playerList) {
            if (player.nickname.equals(nickname)) {
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
        if(player!=null) {
            if (hasla.containsKey(player.nickname) && hasla.get(player.nickname).equals(haslo)) {
                return player;
            }
        }

        //w innym wypadku zostaje utworzony nowy player
        HumanPlayer gracz = new HumanPlayer(nickname);
        playerList.add(gracz);
        saveToFile(gracz);
        return gracz;
    }

    private void saveToFile(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\PlayerList.txt",true))) {
            writer.write(player.nickname);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }

}
