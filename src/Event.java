/**
 * Reprezentuje zdarzenie w grze, opisując ruch wykonany przez gracza lub AI.
 * <p>Każde zdarzenie zawiera informacje o graczu, typie akcji i szczegółach tej akcji.</p>
 *
 * @author [Twoje Imię]
 * @version 1.0
 */
public class Event {
    private String player; // Kto wykonał ruch (np. "Player 1" lub "AI")
    private String action; // Typ akcji (np. "shoot", "placeShip")
    private String details; // Szczegóły akcji (np. "at position (2,3)", "missed", "hit")

    /**
     * Tworzy nowe zdarzenie w grze.
     *
     * @param player gracz, który wykonał ruch (np. "Player 1", "AI")
     * @param action typ akcji wykonanej przez gracza (np. "shoot", "placeShip")
     * @param details szczegóły akcji (np. "at position (2,3)", "missed", "hit")
     */
    public Event(String player, String action, String details) {
        this.player = player;
        this.action = action;
        this.details = details;
    }

    /**
     * Pobiera nazwę gracza, który wykonał ruch.
     *
     * @return nazwa gracza (np. "Player 1", "AI")
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Pobiera typ akcji wykonanej przez gracza.
     *
     * @return typ akcji (np. "shoot", "placeShip")
     */
    public String getAction() {
        return action;
    }

    /**
     * Pobiera szczegóły akcji wykonanej przez gracza.
     *
     * @return szczegóły akcji (np. "at position (2,3)", "missed", "hit")
     */
    public String getDetails() {
        return details;
    }

    /**
     * Zwraca tekstową reprezentację zdarzenia w formacie:
     * <p>"Player: [gracz], Action: [akcja], Details: [szczegóły]".</p>
     *
     * @return tekstowa reprezentacja zdarzenia
     */
    @Override
    public String toString() {
        return String.format("Player: %s, Action: %s, Details: %s", player, action, details);
    }
}
