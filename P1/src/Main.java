
//Java hello world
public class Main{
	public static void main(String[] args)
	{
		CustomThread t0 = new CustomThread(new FlockOfSheep(), 0);
		t0.run();
	}
}
