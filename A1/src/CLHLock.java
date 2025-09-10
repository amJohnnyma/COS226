import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {
    private static class QNode {volatile boolean locked = false;}

    private final AtomicReference<QNode> tail;
    private final ThreadLocal<QNode> currNode;
    private final ThreadLocal<QNode> myPred;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        currNode = ThreadLocal.withInitial(QNode::new);
        myPred = new ThreadLocal<>();
    }

    public void lock() {
        QNode node = currNode.get();
        node.locked = true;

        QNode pred = tail.getAndSet(node);
        myPred.set(pred);

        while(pred.locked)
        {
            Thread.onSpinWait();
        }

    }

    public void unlock() {
        QNode node = currNode.get();
        node.locked = false;
        currNode.set(myPred.get());
    }
}

    
