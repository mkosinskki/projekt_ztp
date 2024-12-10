public class InterfaceGUI extends Interface {
    @Override
    public int menu(){
        return 0;
    }
    @Override
    public int wyborTrybuGry(){
        return 0;
    }
    @Override
    public String wczytajNick(){
        return "";
    }
    @Override
    public int[] getKoordynaty(){
        return new int[0];
    }
    @Override
    public char getUstawienie(){
        return 0;
    }

    @Override
    public int[] wczytywanieIlosciStatkow(){
        return new int[0];
    }

    @Override
    public void komunikatPoStrzale(int[] koordynaty, boolean trafione){}

    @Override
    public int wyborTrudnosciBota(){
        return 0;
    }

    @Override
    public void pokazTablice(Board tablicaGracza){}

    @Override
    public void komunikatStatek(int komunikat, int dlugoscStatku){}

    @Override
    public void komunikatZwyciestwo(Player Winner) {

    }

    @Override
    public void komunikatOsiagniecie(Player Zdobywający) {

    public int wyborSetupu()
    {
        return 0;
    }

    @Override
    public int wielkoscPlanszy()
    {
        return 0;
    }
}
