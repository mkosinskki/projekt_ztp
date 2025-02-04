public abstract class Player 
{
    protected Board board; // Plansza gracza
    protected String nickname;
    protected int winCount;

    public Player(String nickname) 
    {
        this.nickname = nickname;

    }

    public void addWinCount()
    {
        winCount++;
    }


    // Logika rozmieszczania statków
    public abstract boolean placeShips(int koordynaty[], char kierunek, Ship statek);
    // Logika wykonywania ruchu
    public abstract boolean makeMove(int koordynaty[]); // Zwraca współrzędne [x, y]

    public abstract void setBoard(Board board);

    public String getNickname()
    {
        return nickname;
    }

    @Override
    public String toString() 
    {
        return "Nick: " + nickname + ", Ilosc zwyciestw: " + winCount;
    }
}
