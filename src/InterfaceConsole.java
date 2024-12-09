import java.util.Scanner;

public class InterfaceConsole extends Interface {
    
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
