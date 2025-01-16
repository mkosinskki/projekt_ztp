
public class Board {
    private final int size;
    private Cell[][] grid;

    public Board(int size)
    {

        this.size = size;
        this.grid = new Cell[size][size];
        initializeGrid();
    }

    public String getBoardForHistory(Player player)
    {
        StringBuilder sb = new StringBuilder();
        ICustomization customization = GameManager.getInstance(null).myInterface.getCustomization(player.nickname);
        for(Cell[] wiersz : grid)
        {
            for(Cell c : wiersz)
            {
                sb.append(c.containsShip()?customization.shipForSave():
                customization.waterForSave());
            }
        }
        return sb.toString();
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

    public boolean placeShip(Ship ship, int startX, int startY, boolean horizontal) 
    {
        int size = ship.getSize();
        if(startX<0||startY<0||startX>=this.size||startY>=this.size)return false;
        if (horizontal) 
        {
            if (startY + size - 1 >= this.size) return false; // Wychodzi poza planszę
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
            if (startX + size - 1 >= this.size) return false; // Wychodzi poza planszę
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
        return cell.containsShip(); //zeby uzytkownik wiedzial czy trafil 
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
}
