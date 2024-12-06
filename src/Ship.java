public class Ship {
    private String type;
    private int size;

    public Ship(String type, int size) 
    {
        this.type = type;
        this.size = size;
    }

    public int getSize() 
    {
        return size;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}