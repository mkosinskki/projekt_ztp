class Event {
    private String player; // Kto wykonał ruch (np. "Player 1" lub "AI")
    private String action; // Typ akcji (np. "shoot", "placeShip")
    private String details; // Szczegóły akcji (np. "at position (2,3)", "missed", "hit")

    public Event(String player, String action, String details) 
    {
        this.player = player;
        this.action = action;
        this.details = details;
    }

    public String getPlayer() {
        return player;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return String.format("Player: %s, Action: %s, Details: %s", player, action, details);
    }
}