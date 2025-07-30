

public class FlockOfSheep {
    private int sheepNum = 0;
	private final PetersonLock lock = new PetersonLock();
    
    public int getSheepAmount() {
        return sheepNum;
    }

    public int countSheep(int threadId) {
        //TODO: Implement function
	lock.lock(threadId);
	int temp = sheepNum;
	try{
		sheepNum++;
	}
	finally {
	lock.unlock(threadId);
	}
	return temp;
    }
}
