public abstract class Player {
    protected Board board; // Plansza gracza
    protected String nickname;
    protected int winCount;

    public Player(String nickname, Board board) {
        this.nickname = nickname;
        this.board = board;

    }

    public void addWinCount()
    {
        winCount++;
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

    @Override
    public String toString() {
        return nickname + " Liczba zwyciestw: " + winCount;
    }
}
