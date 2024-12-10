import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerList {
    private List<Player> playerList = new ArrayList<>(); // tu taktyczny singletonik potem
    private HashMap<String, String> hasla = new HashMap<>();

    private List<Player> findPlayer(String nickname){
        List<Player> pom = new ArrayList<>();
        for(Player player : playerList)
        {
            pom.add(player);
        }
        return  pom;
    }

    public Player logowanie(String nickname, String haslo)
    {
        List<Player> pom = findPlayer(nickname);
        for(Player player : pom)
        {
            //jesli jest taki zestaw nickname i haslo, zostaje zwrocony player z listy
            if(hasla.containsKey(player.nickname) && hasla.get(player.nickname).equals(haslo))
            {
                return player;
            }
        }
        //w innym wypadku zostaje utworzony nowy player
        return new HumanPlayer(nickname, new Board(8));
    }
}
