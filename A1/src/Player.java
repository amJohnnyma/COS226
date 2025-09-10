import java.util.List;
import java.util.Random;

public class Player extends Thread {
    private final String name;
    private final List<TreasureChest> chests;
    private final int thinkTimeMs;  // OUTSIDE lock delay 
    private final int csWorkIters;  // INSIDE lock work 
    private final Random rand = new Random();
    private int totalCoins = 0;

    public Player(String name, List<TreasureChest> chests, int thinkTimeMs, int csWorkIters) {
        this.name = name;
        this.chests = chests;
        this.thinkTimeMs = thinkTimeMs;
        this.csWorkIters = csWorkIters;
    }

    @Override
    public void run() {
        while (chests.stream().anyMatch(TreasureChest::hasCoins)) {
            TreasureChest chest = chests.get(rand.nextInt(chests.size()));
            if (chest.hasCoins()) {
                int got = chest.takeCoins(name, csWorkIters);
                totalCoins += got;
            }
            
            
            try {
                if (thinkTimeMs > 0) Thread.sleep(thinkTimeMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public String getPlayerName() { return name; }
    public int getTotalCoins() { return totalCoins; }
}
