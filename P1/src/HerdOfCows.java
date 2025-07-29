
public class HerdOfCows {
    private int cowNum = 0;
    private final PattersonLock lock;

    public HerdOfCows(int threadNum) {
        this.lock = new PattersonLock(threadNum);
    }

	public int getCowAmount() {
        return cowNum;
    }

	public int countCows(int threadId) {
        //TODO: Implement function
	return 0;
    }
}
 
