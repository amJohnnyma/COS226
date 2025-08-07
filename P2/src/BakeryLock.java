public class BakeryLock {
    private final ThreadLocal<Boolean> isLocked = ThreadLocal.withInitial(() -> false);
    private final int n;
    private final boolean[] choosing;
    private final int[] number;

    public BakeryLock(int input) {
    	//TODO: Implement Constructor
	n = input;
	choosing = new boolean[n];
	number = new int[n];
	for (int i = 0 ; i <n; i ++)
	{
		choosing[i] = false;
		number[i] = 0;
	}
    }

    public void lock(int threadId) {
        //TODO: Implement function
	choosing[threadId] = true;
	int max = 0;
	for (int i = 0; i < n; i ++)
	{
		if (number[i] > max)
		{
			max =number[i];
		}
	}
	number[threadId] = max + 1;
	choosing[threadId] = false;

	//wait for this threads turn
	for (int j = 0; j < n; j++)
	{
		if (j == threadId) continue;

		while(choosing[j])
		{
			Thread.yield(); // let others run instead of cpu spinning too much
		}

		//wait if j < or same num lower ID
		while(number[j] != 0 &&
				(number[j] < number[threadId] ||
				 (number[j] == number[threadId] && j < threadId)))
		{
			Thread.yield();
		}

	}
    }

    public void unlock(int threadId) {
        //TODO: Implement function
	number[threadId] = 0;
    }

}
