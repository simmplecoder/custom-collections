package custom.collections;

public class LinkedListDequeue<E> implements Deque<E> {
    private LinkedList<E> storage;

    public LinkedListDequeue()
    {
        storage = new LinkedList<E>();
    }

    @Override
    public void pushToFront(E element) {
        storage.add(0, element);
    }

    @Override
    public void pushToBack(E element) {
        storage.add(element);
    }

    @Override
    public E popFromFront() throws Exception {
        return storage.remove(0);
    }

    @Override
    public E popFromBack() throws Exception {
        return storage.remove(storage.size() - 1);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
