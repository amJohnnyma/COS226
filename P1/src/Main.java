
//Java hello world
public class Main{
	public static void main(String[] args)
	{
		FlockOfSheep sheep = new FlockOfSheep();
		CustomThread[] threads = new CustomThread[2];
		CustomThread t0 = new CustomThread(sheep, 0);
		CustomThread t1 = new CustomThread(sheep, 1);

		threads[0] = t0;
		threads[1] = t1;

		for(int i = 0; i < 2; i++)
		{
			threads[i].start();
		}

		
		try{
			
			for(CustomThread thread : threads){
				thread.join();
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("Final sheep counted: " + sheep.getSheepAmount());

		int threadCount = 8;

		HerdOfCows cows = new HerdOfCows(threadCount);
		CustomThread[] threads0 = new CustomThread[threadCount];

		for(int i = 0; i < threadCount; i++)
		{
			threads0[i] = new CustomThread(cows, i);
		}

		for(CustomThread thread : threads0)
		{
			thread.start();
		}

		for(CustomThread thread : threads0)
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

