
public class SpaceShip extends Thread {
    private final int threadId;
    private final AndersonLock lock;
    private final int dockings;

    public SpaceShip(int id, AndersonLock lock, int dockings) {
        this.threadId = id;
        this.lock = lock;
        this.dockings = dockings;
    }

    private void dock() {
        lock.lock();
        System.out.println("Ship " + threadId + " is DOCKING");
        try{
            Thread.sleep(200); // docking simulation
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Ship " + threadId + " has LEFT the docking port");
        lock.unlock();
    }

    @Override
    public void run() {
        for(int i = 0; i < dockings; i ++)
        {
            dock();
            try{
                Thread.sleep((int)(Math.random() * 300));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public int getID(){
        return threadId;
    }
}
