package custom.collections;

public class LinkedListStack<E> {
    private LinkedList<E> storage;

    public void push(E object)
    {
        storage.add(object);
    }

    public E pop()
    {
        E result = storage.pop();
        return result;
    }

    public int size()
    {
        return storage.size();
    }

    public void clear()
    {
        storage.clear();
    }

    @Override
    public String toString()
    {
        return storage.toString();
    }

    @Override
    public int hashCode()
    {
        return storage.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        return storage.equals(o);
    }
}
