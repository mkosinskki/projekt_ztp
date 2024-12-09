public abstract class Player {
    protected Board board; // Plansza gracza

    public Player(Board board) {
        this.board = board;
    }

    // // Logika rozmieszczania statków
    // public abstract void placeShips();

    // // Logika wykonywania ruchu
    // public abstract int[] makeMove(); // Zwraca współrzędne [x, y]

    // Logika rozmieszczania statków
    public abstract boolean placeShips(int koordynaty[], char kierunek, Ship statek);
    public abstract boolean placeShips(Ship Statek);
    // Logika wykonywania ruchu
    public abstract boolean makeMove(int koordynaty[]); // Zwraca współrzędne [x, y]
}
