public class Ship {
    // private String type;
    private int size;

    //mozna dodac abstrakcyjne statki np ShipSubmarine itp, ktore beda mialy rozmiar i tylko ustawiasz je na mapie

    public Ship(int size) 
    {
        this.size = size;
    }
    // public Ship Clone(){
    //     return new Ship(size);
    // }

    public int getSize() 
    {
        return size;
    }

    // @Override
    // public String toString() {
    //     return "Ship{" +
    //             "type='" + type + '\'' +
    //             ", size=" + size +
    //             '}';
    // }
}