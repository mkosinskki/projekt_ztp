import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameHistory { // wyrzucony wzorzec builder, ale jest IoC
    private List<Event> events = new ArrayList<>(); // Lista zdarzeń w grze
    private String gameMode; // Tryb gry (np. "Standard", "Simulation")
    private String date; // Data rozegrania gry
    private String winner; // Zwycięzca (np. "Player 1")
    private String player1; // Nazwa pierwszego gracza
    private String player2; // Nazwa drugiego gracza

    public GameHistory setGameMode(String gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    public GameHistory setDate(String date) {
        this.date = date;
        return this;
    }

    public GameHistory setPlayer1(String player1) {
        this.player1 = player1;
        return this;
    }

    public GameHistory setPlayer2(String player2) {
        this.player2 = player2;
        return this;
    }

    public GameHistory addEvent(Event event) {
        //this.events.add(event);
        events.add(event);
        return this;
    }

    public GameHistory setWinner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameHistory() {

    }

    // Dodawanie zdarzenia do historii
    public void addEvent(String player, String action, String details) 
    {
        events.add(new Event(player, action, details));
    }


    // Zwraca listę wszystkich zdarzeń
    public List<Event> getEvents() {
        return events;
    }

    // Eksport historii gry do tekstowego formatu (uwzględnia graczy i typy)
    public String exportHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Game Mode: ").append(gameMode).append("\n");
        history.append("Date: ").append(date).append("\n");
        history.append("Players:\n");
        history.append("  - Player 1: ").append(player1).append("\n");
        history.append("  - Player 2: ").append(player2).append("\n");
        history.append("Winner: ").append(winner != null ? winner : "No winner").append("\n");
        history.append("Events:\n");

        for (Event event : events) {
            history.append("- ").append(event.toString()).append("\n");
        }

        return history.toString();
    }

    // Zapis historii gry do pliku tekstowego
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(exportHistory());
            writer.write("\n\n\n");
            System.out.println("Game history saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }

    // Odczyt historii gry z pliku tekstowego
    public static String loadFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error loading game history: " + e.getMessage());
            return null;
        }
    }
}