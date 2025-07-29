
public class PetersonLock {
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;
    
    PetersonLock(){
    	flag[0] = false;
    	flag[1] = false;
    }

    public void lock(int i) {
        // TODO: Implement lock
	int j = 1-i;
	flag[i] = true;
	victim = i;
	while(flag[j] && victim == i) {};

    }

    public void unlock(int i) {
        // TODO: Implement unlock
	flag[i] = false;

    }
}
