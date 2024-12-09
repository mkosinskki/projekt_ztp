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
        Scanner scanner = new Scanner(System.in);
        int[] koordynaty = new int[2];

        koordynaty[0] = scanner.nextInt();
        koordynaty[1] = scanner.nextInt();
        //Dodac sprawdzanie poprawnosci koordynatow po rozmiarze planszy
        scanner.close();
        return koordynaty;
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
    public void komunikatPoStrzale(boolean trafione)
    {
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
