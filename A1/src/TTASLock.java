
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class TTASLock implements Lock {
    private final AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 1000;  // in microseconds
    private static final int MAX_DELAY = 1_000_000;  // max backoff delay (µs)

    // Counter to track how many times backoff (sleep) occurred
    public final AtomicInteger backoffCount = new AtomicInteger(0);

    public void lock() {
        int delay = MIN_DELAY;
        while(true)
        {
            while(state.get()) {Thread.onSpinWait();} //wait until lock looks free

            if(!state.getAndSet(true)) //acquire it
                return;


               int sleepTime = ThreadLocalRandom.current().nextInt(delay);
               LockSupport.parkNanos(sleepTime);

               if(delay< MAX_DELAY)
               {
                   delay = Math.min(MAX_DELAY, delay*2);
               }

            


        }
    }

    public void unlock() {
        state.set(false);
    }
}
