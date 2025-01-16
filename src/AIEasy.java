import java.util.Random;

public class AIEasy implements AIStrategy {
    private Random random;

    public AIEasy() {
        this.random = new Random();
    }

    @Override
    public int[] calculateMove(Board board) 
    {
        int size = board.getSize();
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        return new int[]{y, x};
    }
}