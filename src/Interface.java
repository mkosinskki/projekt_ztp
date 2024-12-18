public abstract class Interface {
    public abstract int menu();

    public abstract int wyborTrybuGry();

    public abstract String wczytajNick();
    
    public abstract int[] getKoordynaty();

    public abstract char getUstawienie();

    public abstract int wyborStatystyk();

    public abstract int[] wczytywanieIlosciStatkow();

    public abstract void komunikatPoStrzale(int[] koordynaty, boolean trafione);

    public abstract int wyborTrudnosciBota();

    public abstract void pokazTablice(Player gracz);

    public abstract void komunikatStatek(int komunikat, int dlugoscStatku);

    public abstract void komunikatGracz(Player player);

    public abstract void komunikatStatystykiWszystkich(PlayerList playerList);

    public abstract void komunikatZwyciestwo(Player Winner);

    public abstract void komunikatOsiagniecie(int i);

    public abstract int wyborSetupu();

    public abstract int wielkoscPlanszy();

    public abstract void customisationMenu(String nick);

    public abstract void bladCustomizacji(String nick);

    public abstract void komunikatLogowanie(String nick);
}
