public abstract class Interface {
    public abstract int menu();

    public abstract int wyborTrybuGry();

    public abstract String wczytajNick();
    
    public abstract int[] getKoordynaty();

    public abstract char getUstawienie();

    public abstract int[] wczytywanieIlosciStatkow();

    public abstract void komunikatPoStrzale(int[] koordynaty, boolean trafione);

    public abstract int wyborTrudnosciBota();

    public abstract void pokazTablice(Board tablicaGracza);

    public abstract void komunikatStatek(int komunikat, int dlugoscStatku);

    public abstract void komunikatZwyciestwo(Player Winner);

    public abstract int wyborSetupu();

    public abstract int wielkoscPlanszy();
}
