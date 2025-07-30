
public class Task2Test {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Test your program

	int threadCount = 4;

	HerdOfCows cows = new HerdOfCows(threadCount);
	CustomThread[] threads = new CustomThread[threadCount];

	for(int i = 0; i < threadCount; i++)
	{
		threads[i] = new CustomThread(cows, i);
	}

	for(CustomThread thread : threads)
	{
		thread.start();
	}

	for(CustomThread thread : threads)
	{
		try
		{
			thread.join();
		}
		catch (InterruptedException e){
		e.printStackTrace();
		}
	}

	System.out.println("Final cows counted: " + cows.getCowAmount());




    }
}
