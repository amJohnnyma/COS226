public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int numThreads = 10;
        ButterCroissant croissants = new ButterCroissant(numThreads);
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new BakeryWorkerThread(i, croissants);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Total croissants baked: " + croissants.getTotal());
    }
}

