
//Java hello world
public class Main{
	public static void main(String[] args)
	{
		//Not using CustomThread but proof of concept
		PetersonLock lock = new PetersonLock();

		Runnable task = () ->{
		int threadId = Integer.parseInt(Thread.currentThread().getName());
		lock.lock(threadId);
		
		try{
			Thread.sleep(500);
		}
		catch(InterruptedException e){
			Thread.currentThread().interrupt();

		}
		System.out.println("Thread" + threadId + " is leaving the critical zone");
		lock.unlock(threadId);


		};

		Thread t0 = new Thread(task,"0");
		Thread t1 = new Thread(task,"1");

		t0.start();
		t1.start();
	}
}
