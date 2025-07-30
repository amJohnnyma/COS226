

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
	System.out.println("Custom thread is running: " + this.threadId);
	if(baa != null)
	{
		//System.out.println("Counting sheep");
		releaseTheSheep();
	}
	else if(moo != null)
	{
		//System.out.println("Counting cows");
		releaseTheCows();
	}
	else{
		System.out.println("Not counting");
	}
	
    }

    public void releaseTheSheep() {
        //TODO: Count all the sheep! 
	while(baa.getSheepAmount() < 10)
	{
	baa.countSheep(threadId);
	System.out.println("Thread-" + threadId + " (ID " + threadId + ") has incremented to " + baa.getSheepAmount());
	}
    }

	public void releaseTheCows() {
        //TODO: Count all the cows!    
	while(moo.getCowAmount() < 20)
	{
	moo.countCows(threadId);
	System.out.println("Thread-" + threadId + " (ID " + threadId + ") has incremented to " + moo.getCowAmount());
	}
    }

}
