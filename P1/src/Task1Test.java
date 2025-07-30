

public class Task1Test {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Test your program
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




    }
}
