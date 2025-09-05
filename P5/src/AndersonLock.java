import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class AndersonLock {
    // Main structure -> Only true slots allow thread to procees.
    // Each thread spins its own slot unlike TAS and TTAS with a shared variable
    private final AtomicBoolean[] slots;
    // total number of slots. wrap around when threads get new slots
    // circular array
    private final int size;
    //track next available slot
    // FIFO order
    // Modulo ensures wrap around because of circular array
    private final AtomicInteger tail = new AtomicInteger(0);
    // stores slot number for each thread individually
    // a slot must unlock its own slot
    private final ThreadLocal<Integer> localSlot = ThreadLocal.withInitial(() -> 0);

    public AndersonLock(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        size = capacity;
        slots = new AtomicBoolean[size];

        // "queue" of threads waiting to enter criticial section
        for(int i = 0; i < size; i ++)
        {
            slots[i] = new AtomicBoolean(false);
        }
        slots[0].set(true);
    }

    public void lock() {
        // assign ticket slot in the queue
        int slot = tail.getAndIncrement() % size;
        // save for later unlock
        localSlot.set(slot);
        while(!slots[slot].get()) {Thread.onSpinWait();} //optimize spinning
        // -> Low contention with AtomicBoolean for each thread to spin
        // -> Doesnt flood a single memory location
        // Number of slots is fixed
                                                         
    }

    public void unlock() {
        int slot = localSlot.get();
        // leave critical section
        slots[slot].set(false);
        slots[(slot+1) % size].set(true); // wrap around after last slot
    }
}
