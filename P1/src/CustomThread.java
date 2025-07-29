

public class CustomThread extends Thread {
    private final FlockOfSheep baa;
    private final HerdOfCows moo;
    private final int threadId;
    private final int max_count = 2;

	public CustomThread(FlockOfSheep baa, int threadId) {
        this.baa = baa;
        this.moo = null;
        this.threadId = threadId;
    }
    public CustomThread(HerdOfCows moo, int threadId) {
        this.moo = moo;
        this.baa = null;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        //TODO: Count all the animals!       
    }

    public void releaseTheSheep() {
        //TODO: Count all the sheep! 
    }

	public void releaseTheCows() {
        //TODO: Count all the cows!    
    }

}
