public class Main
{
	public static void main(String[] args)
	{
		int numBoba = 3;
		Straw strawd = new Straw("");
		Boba[] bobasd = new Boba[numBoba];
		for(int i = 0; i < numBoba; i ++)
		{
			bobasd[i] = new Boba(strawd, i);
			bobasd[i].start();
		}
		for(int i = 0; i < numBoba; i ++)
		{
			try
			{
				bobasd[i].join();
			}
			catch(InterruptedException e)
			{
                Thread.currentThread().interrupt();
			}
		}
        System.out.println("V2 time\n\n");
		Straw straw = new Straw("V2");
		Boba[] bobas = new Boba[numBoba];
		for(int i = 0; i < numBoba; i ++)
		{
			bobas[i] = new Boba(straw, i);
			bobas[i].start();
		}
		for(int i = 0; i < numBoba; i ++)
		{
			try
			{
				bobas[i].join();
			}
			catch(InterruptedException e)
			{
                Thread.currentThread().interrupt();
			}
		}
		

	}
}
