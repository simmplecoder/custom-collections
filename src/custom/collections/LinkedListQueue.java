package custom.collections;

import java.util.List;

public class LinkedListQueue<E> implements Queue<E> {
    private List<E> storage;

    public LinkedListQueue() {
        storage = new LinkedList<>();
    }

    public LinkedListQueue(List<E> internalList) {
        if (internalList == null) {
            throw new IllegalArgumentException("null list is not allowed");
        }
        storage = new LinkedList<>();
        storage.addAll(internalList);
    }

    @Override
    public void enqueue(E element) {
        storage.add(element); //appends
    }

    @Override
    public E dequeue() {
        return storage.remove(0);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public String toString()
    {
        return storage.toString();
    }
}
