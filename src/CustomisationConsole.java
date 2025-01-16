import java.util.Dictionary;
import java.util.Hashtable;

public class CustomisationConsole implements ICustomization{
    private char waterChar;
    private char shipChar;
    private static Dictionary<String,CustomisationConsole> customs = new Hashtable<>();

    public static ICustomization getInstance(String nick)
    {
        if(customs.get(nick) == null)
        {
            customs.put(nick, new CustomisationConsole());
        }
        return customs.get(nick);
    }
    private CustomisationConsole()
    {
        shipChar='S';
        waterChar='.';
    }
    public Object getShip() {
        return shipChar;
    }

    public Object getWater() {
        return waterChar;
    }

    public char shipForSave(){
        return shipChar;
    }

    public char waterForSave(){
        return waterChar;
    }

    public void setShip(Object ship) {
        this.shipChar = (char)ship;
    }

    public void setWater(Object water) {
        this.waterChar = (char)water;
    }
}
