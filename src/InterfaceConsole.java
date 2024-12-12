// import java.util.Scanner;

// public class InterfaceConsole extends Interface 
// {
//     Scanner scanner = new Scanner(System.in);
//     @Override
//     public int menu()
//     {
//         // Scanner scanner = new Scanner(System.in);
//         int wybor = 0;

//         System.out.println("\nMenu uzytkownika\n");
//         System.out.println("\t1) Rozpocznij nowa gre\n\t2) Sprawdz statystyki gracza\n\t3) Customizuj plansze");
//         wybor = scanner.nextInt();

//         // scanner.close();
//         return wybor;
//     }

//     @Override
//     public int wyborTrybuGry()
//     {
//         // Scanner scanner = new Scanner(System.in);
//         int wybor;

//         System.out.println("\n1) Gracz vs Gracz\n2)Gracz vs AI\n3) AI vs AI");
//         wybor = scanner.nextInt();

//         // scanner.close();
//         return wybor;
//     }

//     @Override
//     public void pokazTablice(Player gracz)
//     {
//         CustomisationConsole customisation = CustomisationConsole.getInstance(gracz.nickname);
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < gracz.board.getSize(); i++) {
//             for (int j = 0; j < gracz.board.getSize(); j++) {
//                 sb.append(gracz.board.getGrid()[i][j].containsShip() ? customisation.getShip() : customisation.getWater());
//                 sb.append(gracz.board.getGrid()[i][j].isHit() ? "X" : " ");
//             }
//             sb.append("\n");
//         }
//         System.out.println(sb.toString());
//     }

//     @Override
//     public int wyborTrudnosciBota()
//     {
//         // Scanner scanner = new Scanner(System.in);
//         int trudnosc;

//         System.out.println("\nWybierz poziom trudności bota:\n1) Latwy\t2) Sredni\t3)Trudny");
//         trudnosc = scanner.nextInt();

//         // scanner.close();
//         return trudnosc;
//     }

//     private int wczytajNajdluzszyStatek()
//     {
//         // Scanner scanner = new Scanner(System.in);
//         int rozmiar;

//         System.out.println("Podaj dlugosc najdluzszego statku");
//         rozmiar = scanner.nextInt();

//         // scanner.close();
//         return rozmiar;
//     }

//     @Override
//     public int[] wczytywanieIlosciStatkow()
//     {
//         // Scanner scanner = new Scanner(System.in);
//         int max = wczytajNajdluzszyStatek();
//         int[] ilosciStatkow = new int[max];
//         System.out.println("MAX " + max);

//         for(int a=0; a<max; a++)
//         {
//             System.out.println("\nPodaj ilosc statkow o dlugosci: " + (a+1) + " \n");
//             ilosciStatkow[a] = scanner.nextInt();
//         }

//         // scanner.close();
//         return ilosciStatkow;
//     }

//     @Override
//     public String wczytajNick()
//     {
//         Scanner scanner1 = new Scanner(System.in);
//         String nick;
//         //scanner.nextLine();
//         //scanner.skip("\n");
//         System.out.println("Wprowadz nick gracza: "); //zrobic rozne wersje dla historii i 2 graczy i statystyk
//         nick = scanner1.nextLine();

//         // scanner1.close();
//         return nick;
//     }

//     @Override
//     public int[] getKoordynaty()
//     {
//         System.out.println("Podaj koordynaty: ");
//         // Scanner scanner = new Scanner(System.in);
//         int[] koordynaty = new int[2];

//         koordynaty[0] = scanner.nextInt();
//         koordynaty[1] = scanner.nextInt();
//         //Dodac sprawdzanie poprawnosci koordynatow po rozmiarze planszy
//         // scanner.close();
//         return koordynaty;
//     }

//     @Override
//     public void komunikatStatek(int komunikat, int dlugoscStatku)
//     {
//         switch (komunikat) {
//             case 1:
//                 System.out.println("Ustawianie statku o dlugosci: " + dlugoscStatku);
//                 break;
//             case 2:
//                 System.out.println("Pomyslnie ustawiono statek o dlugosci: " + dlugoscStatku);
//                 break;
//             case 3:
//                 System.out.println("Blad stawiania statku! Zostaniesz poproszony o ponowienie operacji.");
//                 break;
//             case 4:
//                 System.out.println("Wszystkie statki ustawione prawidlowo");
//                 break;
//             default:
//                 throw new AssertionError();
//         }
//     }

//     @Override
//     public char getUstawienie()
//     {
//         Scanner scannerTest = new Scanner(System.in);
//         char ustawienie ='\0';

//         System.out.println("Podaj znak ustawienia (poziomo/pionowo): h/v");

//         while(Character.toLowerCase(ustawienie) != 'h' || Character.toLowerCase(ustawienie) !='v')
//         {
//             ustawienie = scanner.next().charAt(0);
//         }
//         // scanner.close();
//         return Character.toLowerCase(ustawienie);
//     }

//     @Override
//     public void komunikatPoStrzale(int[] koordynaty, boolean trafione)
//     {
//         System.out.println("Strzelono w pole: " + koordynaty[0] + ", " + koordynaty[1]);
//         if(trafione)
//         {
//             System.out.println("Trafiles w statek przeciwnika");
//         }
//         else
//         {
//             System.out.println("Nietrafiono w statek przeciwnika");
//         }
//     }

//     @Override
//     public void komunikatZwyciestwo(Player Winner) {
//         System.out.println("Wygral :" + Winner.toString());
//     }

//     @Override
//     public void komunikatOsiagniecie(Player Zdobywający) 
//     {
//         System.out.println("achievement get"); //prototyp
//     }

//     @Override
//     public int wielkoscPlanszy()
//     {
//         System.out.println("Podaj wielkosc planszy");
//         int wielkosc = scanner.nextInt();
//         return wielkosc;
//     }

//     @Override
//     public int wyborSetupu()
//     {
//         System.out.println("\nWybierz tryb gry:\n1) Standardowy\t2) Wlasne ustawienia planszy i statkow");
//         int wybor = scanner.nextInt();
//         return wybor;
//     }

//     @Override
//     public void customisationMenu()
//     {
//         CustomisationConsole customisation=CustomisationConsole.getInstance(wczytajNick());
//         // Scanner scanner = new Scanner(System.in);
//         String pomocniczy;
//         Boolean menu = true;
//         while(menu)
//         {
//             System.out.println("Wybierz opcje:\n\t0: Personalizuj statek\n\t1: Personalizuj wode\n\tKazdy inny znak: Wyjdz");
//             switch(scanner.next())
//             {
//                 case "0":
//                     System.out.println("Podaj znak, ktory ustawic jako statek");
//                     customisation.setShip(scanner.next().charAt(0));
//                     break;
//                 case "1":
//                     System.out.println("Podaj znak, ktory ustawic jako wode");
//                     customisation.setWater(scanner.next().charAt(0));
//                     break;
//                 default: menu=false;
//             }
//         }
//     }

//     @Override
//     public void komunikatLogowanie(String nick)
//     {
//         System.out.println("Pomyslnie zalogowano jako: " + nick);
//     }
// }

import java.sql.Time;
import java.util.Scanner;
import java.util.Timer;

public class InterfaceConsole extends Interface {
    private final Scanner scanner = new Scanner(System.in);

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawny format. Podaj liczbę całkowitą.");
            }
        }
    }

    private String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private char readChar(String prompt, char... validChars) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.length() == 1) {
                char ch = input.charAt(0);
                for (char valid : validChars) {
                    if (ch == valid) {
                        return ch;
                    }
                }
            }
            System.out.println("Niepoprawny znak. Spróbuj ponownie.");
        }
    }

    @Override
    public int menu() {
        return readInt("\nMenu użytkownika\n" +
                "1) Rozpocznij nową grę\n" +
                "2) Sprawdź statystyki gracza\n" +
                "3) Customizuj planszę");
    }

    @Override
    public int wyborTrybuGry() {
        return readInt("\n1) Gracz vs Gracz\n2) Gracz vs AI\n3) AI vs AI");
    }

    @Override
    public void pokazTablice(Player gracz) {
        CustomisationConsole customisation = CustomisationConsole.getInstance(gracz.nickname);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gracz.board.getSize(); i++) {
            for (int j = 0; j < gracz.board.getSize(); j++) {
                sb.append(gracz.board.getGrid()[i][j].containsShip() ? customisation.getShip() : customisation.getWater());
                sb.append(gracz.board.getGrid()[i][j].isHit() ? "X" : " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public int wyborTrudnosciBota() {
        return readInt("\nWybierz poziom trudności bota:\n1) Łatwy\n2) Średni\n3) Trudny");
    }

    @Override
    public int[] wczytywanieIlosciStatkow() {
        int max = readInt("Podaj długość najdłuższego statku");
        int[] ilosciStatkow = new int[max];
        for (int a = 0; a < max; a++) {
            ilosciStatkow[a] = readInt("Podaj ilość statków o długości: " + (a + 1));
        }
        return ilosciStatkow;
    }

    @Override
    public String wczytajNick() {
        return readString("Wprowadź nick gracza: ");
    }

    @Override
    public void komunikatGracz(Player player)
    {
        System.out.println(player.toString());
    }

    @Override
    public void komunikatStatystykiWszystkich(PlayerList playerList) {
        System.out.println(playerList.toString());
    }

    @Override
    public int wyborStatystyk() {
        return readInt("wybierz opcje 1-statystyki konkretnego gracza, 2-statystyki wszystkich");
    }

    @Override
    public int[] getKoordynaty() {
        System.out.println("Podaj koordynaty:");
        int x = readInt("Podaj współrzędną X:");
        int y = readInt("Podaj współrzędną Y:");
        return new int[]{x, y};
    }

    @Override
    public void komunikatStatek(int komunikat, int dlugoscStatku) {
        switch (komunikat) {
            case 1:
                System.out.println("Ustawianie statku o długości: " + dlugoscStatku);
                break;
            case 2:
                System.out.println("Pomyślnie ustawiono statek o długości: " + dlugoscStatku);
                break;
            case 3:
                System.out.println("Błąd stawiania statku! Zostaniesz poproszony o ponowienie operacji.");
                break;
            case 4:
                System.out.println("Wszystkie statki ustawione prawidłowo");
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public char getUstawienie() {
        return readChar("Podaj znak ustawienia (poziomo/pionowo): h/v", 'h', 'v');
    }

    @Override
    public void komunikatPoStrzale(int[] koordynaty, boolean trafione) {
        System.out.println("Strzelono w pole: " + koordynaty[0] + ", " + koordynaty[1]);
        if (trafione) {
            System.out.println("Trafiłeś w statek przeciwnika");
        } else {
            System.out.println("Nie trafiono w statek przeciwnika");
        }
    }

    @Override
    public void komunikatZwyciestwo(Player Winner) {
        System.out.println("Wygrał: " + Winner.toString());
    }

    @Override
    public void komunikatOsiagniecie(int i) {
        System.out.println("Achievment got: "+GameManagerPrototype.osiagniecia[i]);
        App.Delay(2000);
}

    @Override
    public int wielkoscPlanszy() {
        return readInt("Podaj wielkość planszy:");
    }

    @Override
    public int wyborSetupu() {
        return readInt("\nWybierz tryb gry:\n1) Standardowy\n2) Własne ustawienia planszy i statków");
    }

    @Override
    public void customisationMenu(String nick) {
        CustomisationConsole customisation = CustomisationConsole.getInstance(nick);
        boolean menu = true;
        while (menu) {
            switch (readString("Wybierz opcję:\n\t0: Personalizuj statek\n\t1: Personalizuj wodę\n\tKażdy inny znak: Wyjdź")) {
                case "0":
                    customisation.setShip(readChar("Podaj znak, który ustawić jako statek", 's', '#')); // Przykład znaków
                    break;
                case "1":
                    customisation.setWater(readChar("Podaj znak, który ustawić jako wodę", '~', '.')); // Przykład znaków
                    break;
                default:
                    menu = false;
            }
        }
    }
    
    @Override
    public void bladCustomizacji(String nick){
        System.out.println("Gracz "+nick+" nie wygral jeszcze ani jednej gry aby odblokowac customizacje\n");
    }


    @Override
    public void komunikatLogowanie(String nick) {
        System.out.println("Pomyślnie zalogowano jako: " + nick);
    }
}
