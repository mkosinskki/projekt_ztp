public abstract class Player {
    protected Board board; // Plansza gracza

    public Player(Board board) {
        this.board = board;
    }

    // Logika rozmieszczania statków
    public abstract void placeShips();

    // Logika wykonywania ruchu
    public abstract int[] makeMove(); // Zwraca współrzędne [x, y]
}
