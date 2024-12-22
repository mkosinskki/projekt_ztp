public class Ship {
    // private String type;
    private final int size;


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