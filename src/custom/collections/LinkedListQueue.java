package custom.collections;

public class LinkedListQueue<E> {
    private LinkedList<E> storage;

    public LinkedListQueue()
    {
        storage = new LinkedList<E>();
    }

    public void enqueue(E element)
    {
        storage.add(element); //appends
    }

    public E dequeue()
    {
        return storage.remove(0);
    }

    public int size()
    {
        return storage.size();
    }

    public void clear()
    {
        storage.clear();
    }

    public boolean isEmpty()
    {
        return storage.isEmpty();
    }
}
