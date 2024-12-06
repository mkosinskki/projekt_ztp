public class Cell {
    private int x;                 // Współrzędna x
    private int y;                 // Współrzędna y
    private boolean hit;           // Czy komórka została trafiona
    private boolean containsShip;  // Czy komórka zawiera statek

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.hit = false;
        this.containsShip = false;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean containsShip() {
        return containsShip;
    }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", hit=" + hit +
                ", containsShip=" + containsShip +
                '}';
    }
}
