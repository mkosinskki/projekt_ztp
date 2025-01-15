public interface ICustomization {

    public static ICustomization getInstance(String nick){
        return null;
    }

    public Object getShip();

    public Object getWater();

    public char shipForSave();

    public char waterForSave();

    public void setShip(Object ship);

    public void setWater(Object water);
}
