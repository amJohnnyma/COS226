
public class ButterCroissant {
    private int count = 0;
    private final int maxThreads;
    //Add Lock here
    private BakeryLock lock;

    public ButterCroissant(int t) {
        this.count = 0;
        this.maxThreads = t;
        //Initalize lock here
	lock = new BakeryLock(maxThreads);
    }

    public int bake(int threadId) {
        //TODO: Implement function
//	System.out.println("Thread " + threadId + " wants to bake");
	lock.lock(threadId);
//	System.out.println("Thread " + threadId + " is baking");
	int temp = count;
	try
	{
		count++;
	}
	finally
	{
//		System.out.println("Thread " + threadId + " finished. " + getTotal());
		lock.unlock(threadId);
	}
	return temp;
    }

    public int getTotal() {
        return count;
    }
}
