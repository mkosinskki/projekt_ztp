public class AIHard implements AIStrategy {
    @Override
    public int[] calculateMove(Board board) 
    {
        // Zaawansowana logika, np. przeszukiwanie planszy w celu wykrycia potencjalnych statków
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) 
            {
                Cell cell = board.getCell(i, j);
                if (!cell.isHit() && cell.containsShip()) 
                {
                    System.out.println("CZY TRAFIONE: " + cell.isHit());
                    System.out.println("Ruch bota: " + i + " " + j);
                    return new int[]{i, j};
                }
            }
        }
        // Domyślny ruch, jeśli nic nie wykryto
        System.out.println("Strzelam losowo");
        return new AIEasy().calculateMove(board);
    }
}