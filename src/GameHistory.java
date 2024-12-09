import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GameHistory {
    private List<Event> events; // Lista zdarzeń w grze
    private String gameMode; // Tryb gry (np. "Standard", "Simulation")
    private String settings; //ustawienia np rozmiaru planszy itd
    private String date; // Data rozegrania gry
    private String winner; // Zwycięzca (np. "Player 1")
    private String player1; // Nazwa pierwszego gracza
    private String player2; // Nazwa drugiego gracza

    // Private constructor to restrict direct instantiation
    private GameHistory(Builder builder) {
        this.events = builder.events;
        this.gameMode = builder.gameMode;
        this.settings = builder.settings;
        if(builder.date.isEmpty())
            this.date = LocalDate.now().toString();
        else
            this.date = builder.date;
        this.winner = builder.winner;
        this.player1 = builder.player1;
        this.player2 = builder.player2;
    }

    // Nested Builder class
    public static class Builder {
        private List<Event> events = new ArrayList<>();
        private String gameMode;
        private String settings;
        private String date;
        private String winner;
        private String player1;
        private String player2;

        public Builder setGameMode(String gameMode) {
            this.gameMode = gameMode;
            return this;
        }
        public Builder setSettings(String settings) {
            this.settings = settings;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setPlayer1(String player1) {
            this.player1 = player1;
            return this;
        }

        public Builder setPlayer2(String player2) {
            this.player2 = player2;
            return this;
        }

        public Builder setWinner(String winner) {
            this.winner = winner;
            return this;
        }

        public Builder addEvent(String player, String action, String details) {
            Event event = new Event(player, action, details);
            this.events.add(event);
            return this;
        }

        public GameHistory build() {
            return new GameHistory(this);
        }
    }

    // Zwraca listę wszystkich zdarzeń
    public List<Event> getEvents() {
        return events;
    }

    // Eksport historii gry do tekstowego formatu (uwzględnia graczy i typy)
    public String exportHistory() {
        StringBuilder history = new StringBuilder();
        history.append("\n===NEW GAME===\n");
        history.append("Game Mode: ").append(gameMode).append("\n");
        history.append("Settings: ").append(gameMode).append("\n");
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