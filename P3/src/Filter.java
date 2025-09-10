
public class Filter {
    int threadNum;
    int[] level;
	volatile int[] vic;
    int[] inStraw;

    public Filter(int num_of_threads) {
	this.threadNum = num_of_threads;
	this.level = new int[threadNum];
	this.vic = new int[threadNum];
    this.inStraw = new int[threadNum];
	for (int i = 0; i < threadNum; i ++)
	{
		level[i] = 0;
        inStraw[i] = 0;
	}
	}

	public void lock(int threadId) {     
	System.out.println("Boba " + threadId + " waiting patiently to be sipped");
	int me = threadId;
	for (int i = 1; i < threadNum; i++)
	{
		level[me] = i;
		vic[i] = me;
		// spin during conflict
		for(int k = 0; k < threadNum; k++)
		{
			while(k != me && level[k] >= i && vic[i] == me)
			{
				Thread.yield();
			}	
		}

	}
    System.out.println("Boba " + threadId + " is being sipped up the straw");
	}

	public void unlock(int threadId) {
    inStraw[threadId]++;
    if(inStraw[threadId] > 2)
    {
        System.out.println("Boba " + threadId + " is being consumed");
        inStraw[threadId] = 0 ;
    }
	int me = threadId;
	level[me] = 0;
	}
}
