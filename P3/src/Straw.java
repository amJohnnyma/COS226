
public class Straw {
    private Filter lock;
	
	Straw(String lock_type) {
		if(lock_type == "V2"){
			lock = new Improved_Filter(3);
		}
		else{
			lock = new Filter(3);
		}
	}

	public void sip(int threadId){
		long x = (long)(Math.random()*100);
		lock.lock(threadId);
		try {
			Thread.sleep(x);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            
			lock.unlock(threadId);

		}
             
	}
}
