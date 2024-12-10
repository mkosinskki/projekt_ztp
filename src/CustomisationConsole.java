import java.util.Dictionary;
import java.util.Hashtable;

public class CustomisationConsole{
    private char waterChar;
    private char shipChar;
    private static Dictionary<String,CustomisationConsole> customs = new Hashtable<>();

    public static CustomisationConsole getInstance(String nick){
        if(customs.get(nick) == null)
            customs.put(nick, new CustomisationConsole());
        return customs.get(nick);
    }
    private CustomisationConsole(){
        shipChar='S';
        waterChar='.';
    }
    public char getShip() {
        return shipChar;
    }

    public char getWater() {
        return waterChar;
    }

    public void setShip(char shipChar) {
        this.shipChar = shipChar;
    }

    public void setWater(char waterChar) {
        this.waterChar = waterChar;
    }
}
