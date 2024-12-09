public class InterfaceGUI extends Interface {
    public int menu(){
        return 0;
    }

    public int wyborTrybuGry(){
        return 0;
    }

    public String wczytajNick(){
        return "";
    }

    public int[] getKoordynaty(){
        return new int[0];
    }

    public char getUstawienie(){
        return 0;
    }

    public int[] wczytywanieIlosciStatkow(){}

    public void komunikatPoStrzale(int[] koordynaty, boolean trafione){}

    public int wyborTrudnosciBota(){
        return 0;
    }

    public void pokazTablice(Board tablicaGracza){}

    public void komunikatStatek(int komunikat, int dlugoscStatku){}
}
