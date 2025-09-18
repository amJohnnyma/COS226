import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    enum Contention {
        LOW(5, 1_000),
        MEDIUM(5, 100_000),
        HIGH(5, 1_000_000);

        final int thinkMs;
        final int csWorkIters;
        Contention(int thinkMs, int csWorkIters) {
            this.thinkMs = thinkMs;
            this.csWorkIters = csWorkIters;
        }
    }

    // all serial to avoid contention on cpu
    public static void main(String[] args) throws Exception {
        int[] numPlayers = {2, 8, 16};

        runAllExperiments("TTAS", new TTASLock(), numPlayers);
        runAllExperiments("CLH", new CLHLock(), numPlayers);
    }

    private static void runAllExperiments(String lockName, Lock lockPrototype, int[] numPlayers) throws IOException, InterruptedException {
        // Build a string like TTAS(10,10000,20,100000,40,1000000)
        StringBuilder sbName = new StringBuilder(lockName + "(");
        for (Contention c : Contention.values()) {
            sbName.append(c.thinkMs)
                  .append(",")
                  .append(c.csWorkIters)
                  .append(",");
        }
        sbName.setLength(sbName.length() - 1); // remove last comma
        sbName.append(")");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sbName.toString() + "_results.txt"))) {
            writer.write("Results for " + sbName.toString() + " Lock\n");
            writer.write("===============================\n\n");

            for (int n : numPlayers) {
                for (Contention c : Contention.values()) {
                    String result = runExperiment(n, c, lockPrototype);
                    writer.write(result + "\n\n");
                }
            }
        }
        System.out.println("Wrote results to " + sbName.toString() + "_results.txt");
    }


    private static String runExperiment(int players, Contention c, Lock lockPrototype) throws InterruptedException {
        // fresh lock for each run
        Lock lock;
        try {
            lock = lockPrototype.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate lock", e);
        }

        TreasureChest chest = new TreasureChest("Chest", 2000, lock);

        List<Player> threads = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            threads.add(new Player("P" + i, List.of(chest), c.thinkMs, c.csWorkIters));
        }

        long start = System.currentTimeMillis();

        for (Player p : threads) p.start();
        for (Player p : threads) p.join();

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        int sum = threads.stream().mapToInt(Player::getTotalCoins).sum();

        // fairness stats
        int min = threads.stream().mapToInt(Player::getTotalCoins).min().orElse(0);
        int max = threads.stream().mapToInt(Player::getTotalCoins).max().orElse(0);
        double avg = sum / (double) players;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
            "Players=%d, Contention=%s, Time=%d ms, CoinsTaken=%d, Min=%d, Max=%d, Avg=%.2f",
            players, c, elapsed, sum, min, max, avg
        ));

        // per-player breakdown
        sb.append("\n");
        for (Player p : threads) {
            sb.append("   ").append(p.getPlayerName())
              .append(" -> ").append(p.getTotalCoins()).append("\n");
        }

        return sb.toString();
    }
}
