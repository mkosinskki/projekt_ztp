
public class Board {
    private final int size;
    private Cell[][] grid;

    public Board(int size)
    {

        this.size = size;
        this.grid = new Cell[size][size];
        initializeGrid();
    }

    public int getSize()
    {
        return size;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int x, int y)
    {
        return grid[x][y];
    }

    private void initializeGrid() 
    {
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean placeShip(Ship ship, int startX, int startY, boolean horizontal) {
        int size = ship.getSize();
        if (horizontal) 
        {
            if (startY + size > this.size) return false; // Wychodzi poza planszę
            for (int i = 0; i < size; i++) 
            {
                if (grid[startX][startY + i].containsShip()) return false; // Kolizja
            }
            for (int i = 0; i < size; i++) 
            {
                grid[startX][startY + i].setContainsShip(true);
            }
        } 
        else 
        {
            if (startX + size > this.size) return false; // Wychodzi poza planszę
            for (int i = 0; i < size; i++) 
            {
                if (grid[startX + i][startY].containsShip()) return false; // Kolizja
            }
            for (int i = 0; i < size; i++) 
            {
                grid[startX + i][startY].setContainsShip(true);
            }
        }
        return true;
    }

    public boolean markShot(int x, int y) 
    {
        Cell cell = grid[x][y];
        if (cell.isHit()) return false; // Już trafione
        cell.setHit(true);
        return cell.containsShip(); //zeby uzytkownik wiedzial czy trafil | w gamemenager dodac sprawdzanie areAllShipsSunk jezeli zwroci trafienie w statek
    }

    public boolean areAllShipsSunk() 
    {
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                if (grid[i][j].containsShip() && !grid[i][j].isHit()) 
                {
                    return false; // Istnieje statek, który nie jest trafiony
                }
            }
        }
        return true;
    }

  //  @Override
/*    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(grid[i][j].containsShip() ? shipChar : waterChar);
                sb.append(grid[i][j].isHit() ? "X" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }*/
}
