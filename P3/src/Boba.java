
public class Boba extends Thread {
    private final int threadId;
    Straw bobaStraw;
	
	Boba(Straw s, int threadId){
		this.bobaStraw = s;
		this.threadId = threadId;
	}
	
	public void run() {
        for (int i = 0; i < 3; i ++)
		{
			bobaStraw.sip(threadId);
			int x =(int)(Math.random()*100);
			try {
				Thread.sleep(x);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }

	}
}
