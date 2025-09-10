public class Improved_Filter extends Filter{
    private final Filter filter;
    private final int numThreads;

    // ticket for FIFO
    private volatile int nextTicket = 0;
    private volatile int currTicket = 0;
    private final int[] myTicket;

    public Improved_Filter(int num_of_threads) {
	super(num_of_threads);
	filter = new Filter(num_of_threads);
	numThreads = num_of_threads;
	myTicket = new int[numThreads];

	
        
	}

    @Override
    public void lock(int threadId) {
	    int ticket = getTicket(threadId);

	    while(currTicket != ticket)
	    {
		    Thread.yield();
	    }

	    filter.lock(threadId);
    }

    @Override
    public void unlock(int threadId) {
	filter.unlock(threadId);
	currTicket++;
    }

    private synchronized int getTicket(int threadId)
    {
	    int t = nextTicket++;
	    myTicket[threadId] = t;
	    return t;
    }
}
