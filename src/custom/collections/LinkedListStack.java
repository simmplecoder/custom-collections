package custom.collections;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> storage;

    public LinkedListStack()
    {
        storage = new LinkedList<>();
    }

    @Override
    public void push(E object) {
        storage.add(object);
    }

    @Override
    public E pop() {
        E result = storage.remove(storage.size() - 1);
        return result;
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
    public String toString() {
        return storage.toString();
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return storage.equals(o);
    }
}
