import java.util.Dictionary;
import java.util.Hashtable;

public class CustomisationGUI implements ICustomization{
    private static Dictionary<String,CustomisationGUI> customs = new Hashtable<>();
    public static ICustomization getInstance(String nick)
    {
        if(customs.get(nick) == null)
        {
            customs.put(nick, new CustomisationGUI());
        }
        return customs.get(nick);
    }
    private CustomisationGUI()
    {

    }
    public Object getShip() {
        return 2;
    }

    public Object getWater() {
        return 2;
    }

    public char shipForSave(){
        return 'S';
    }

    public char waterForSave(){
        return '.';
    }

    public void setShip(Object ship) {
    }

    public void setWater(Object water) {
    }
}