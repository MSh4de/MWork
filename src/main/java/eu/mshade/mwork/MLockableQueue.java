package eu.mshade.mwork;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MLockableQueue<E> extends ConcurrentLinkedQueue<E> {

    private final Queue<E> whitelistQueue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean isLocked = new AtomicBoolean(false);

    public void lock() {
        super.forEach(whitelistQueue::add);
        isLocked.set(true);
    }

    public void unLock() {
        whitelistQueue.clear();
        isLocked.set(false);
    }

    @Override
    public E poll() {
        if(!isLocked.get())
            return super.poll();
        return null;
    }

    @Override
    public E peek() {
        if(!isLocked.get())
            return super.peek();
        return null;
    }

    @Override
    public E element() {
        if(!isLocked.get())
            return super.element();
        return null;
    }

    @Override
    public E remove() {
        if(!isLocked.get())
            return super.remove();
        return null;
    }

    @Override
    public boolean add(E e) {
        if(isLocked.get() && whitelistQueue.contains(e))
            return super.add(e);

        return false;
    }

    @Override
    public boolean offer(E e) {
        if(isLocked.get() && whitelistQueue.contains(e))
            return super.offer(e);

        return false;
    }

    public Queue<E> getFilter() {
        return whitelistQueue;
    }
}
