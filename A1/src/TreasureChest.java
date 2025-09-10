import java.util.Random;

public class TreasureChest {
    private int coins;
    private final Lock lock;
    private final String name;
    private final Random rand = new Random();

    public TreasureChest(String name, int coins, Lock lock) {
        this.name = name;
        this.coins = coins;
        this.lock = lock;
    }

    public boolean hasCoins() {
        return coins > 0;
    }

    public String getName() { return name; }

    /**
     * csWorkIters simulates work INSIDE the critical section
     * Returns coins taken (0 if empty).
     */
    public int takeCoins(String playerName, int csWorkIters) {
        lock.lock();
        try{
            if(!hasCoins()) return 0;

            int take = 1 + rand.nextInt(3);
            if(take > coins){
                take = coins;
            }
            busyWork(csWorkIters);
            coins -= take;

            return take;



        }
        finally{lock.unlock();}

    }

    // Simulates work INSIDE the lock (e.g., parsing/IO in real apps).
    private static void busyWork(int iters) {
        // A tiny CPU loop to inflate the critical section.
        // (No allocations, avoids JIT elimination by using a volatile sink).
        int x = 0;
        for (int i = 0; i < iters; i++) { x ^= i; }
        if (x == 42) System.out.print(""); // keep the compiler honest
    }
}
