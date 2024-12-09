import java.sql.SQLOutput;
import java.util.Scanner;

public class InterfaceConsole extends Interface {
    
    @Override
    public int menu()
    {
        Scanner scanner = new Scanner(System.in);
        int wybor = 0;

        System.out.println("\nMenu uzytkownika\n");
        System.out.println("\t1) Rozpocznij nowa gre\n\t2) Sprawdz statystyki gracza\n\t3) Customizuj plansze");
        wybor = scanner.nextInt();

        scanner.close();
        return wybor;
    }

    @Override
    public int wyborTrybuGry()
    {
        Scanner scanner = new Scanner(System.in);
        int wybor;

        System.out.println("\n1) Gracz vs Gracz\n2)Gracz vs AI\n3) AI vs AI");
        wybor = scanner.nextInt();

        scanner.close();
        return wybor;
    }

    @Override
    public void pokazTablice(Board tablicaGracza)
    {
        System.out.println(tablicaGracza);
    }

    @Override
    public int wyborTrudnosciBota()
    {
        Scanner scanner = new Scanner(System.in);
        int trudnosc;

        System.out.println("\nWybierz poziom trudności bota:\n1) Latwy\t2) Sredni\t3)Trudny");
        trudnosc = scanner.nextInt();

        scanner.close();
        return trudnosc;
    }

    private int wczytajNajdluzszyStatek()
    {
        Scanner scanner = new Scanner(System.in);
        int rozmiar;

        System.out.println("Podaj dlugosc najdluzszego statku");
        rozmiar = scanner.nextInt();

        scanner.close();
        return rozmiar;
    }

    @Override
    public int[] wczytywanieIlosciStatkow()
    {
        Scanner scanner = new Scanner(System.in);
        int max = wczytajNajdluzszyStatek();
        int[] ilosciStatkow = new int[max];

        for(int a=0; a<max; a++)
        {
            System.out.println("\nPodaj ilosc statkow o dlugosci: " + (a+1) + " \n");
            ilosciStatkow[a] = scanner.nextInt();
        }

        scanner.close();
        return ilosciStatkow;
    }

    @Override
    public String wczytajNick()
    {
        Scanner scanner = new Scanner(System.in);
        String nick;

        System.out.println("Wprowadz nick gracza ktorego statystyki chcesz sprawdzic.");
        nick = scanner.nextLine();

        scanner.close();
        return nick;
    }

    @Override
    public int[] getKoordynaty()
    {
        System.out.println("Podaj koordynaty: ");
        Scanner scanner = new Scanner(System.in);
        int[] koordynaty = new int[2];

        koordynaty[0] = scanner.nextInt();
        koordynaty[1] = scanner.nextInt();
        //Dodac sprawdzanie poprawnosci koordynatow po rozmiarze planszy
        scanner.close();
        return koordynaty;
    }

    @Override
    public void komunikatStatek(int komunikat, int dlugoscStatku)
    {
        switch (komunikat) {
            case 1:
                System.out.println("Ustawianie statku o dlugosci: " + dlugoscStatku);
                break;
            case 2:
                System.out.println("Pomyslnie ustawiono statek o dlugosci: " + dlugoscStatku);
                break;
            case 3:
                System.out.println("Blad stawiania statku! Zostaniesz poproszony o ponowienie operacji.");
                break;
            case 4:
                System.out.println("Wszystkie statki ustawione prawidlowo");
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public char getUstawienie()
    {
        Scanner scanner = new Scanner(System.in);
        char ustawienie ='\0';

        System.out.println("Podaj znak ustawienia (poziomo/pionowo): h/v");

        while(Character.toLowerCase(ustawienie) != 'h' || Character.toLowerCase(ustawienie) !='v')
        {
            ustawienie = scanner.next().charAt(0);
        }
        scanner.close();
        return Character.toLowerCase(ustawienie);
    }

    @Override
    public void komunikatPoStrzale(int[] koordynaty, boolean trafione)
    {
        System.out.println("Strzelono w pole: " + koordynaty[0] + ", " + koordynaty[1]);
        if(trafione)
        {
            System.out.println("Trafiles w statek przeciwnika");
        }
        else
        {
            System.out.println("Nietrafiono w statek przeciwnika");
        }
    }
}
