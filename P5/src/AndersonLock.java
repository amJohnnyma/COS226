import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class AndersonLock {
    private final AtomicBoolean[] slots;
    private final int size;
    private final AtomicInteger tail = new AtomicInteger(0);
    private final ThreadLocal<Integer> localSlot = ThreadLocal.withInitial(() -> 0);

    public AndersonLock(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        size = capacity;
        slots = new AtomicBoolean[size];
        for(int i = 0; i < size; i ++)
        {
            slots[i] = new AtomicBoolean(false);
        }
        slots[0].set(true);
    }

    public void lock() {
        int slot = tail.getAndIncrement() % size;
        localSlot.set(slot);
        while(!slots[slot].get()) {Thread.onSpinWait();}
    }

    public void unlock() {
        int slot = localSlot.get();
        slots[slot].set(false);
        slots[(slot+1) % size].set(true);
    }
}
