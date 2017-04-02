package custom.collections;

public class LinkedListSortedQueue<E extends Comparable> implements SortedQueue<E> {
    private LinkedList<E> storage;

    public LinkedListSortedQueue() {
        storage = new LinkedList<>();
    }

    public boolean insert(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        LinkedList.Node current = storage.getRoot();
        if (current == null) {
            storage.add(element);
            return true;
        }

        int index = 0;
        while (current != null && element.compareTo(current.getValue()) > 0) {
            current = current.getNext();
            ++index;
        }

        if (current == null) {
            storage.add(element);
            return true;
        }

        if (element.compareTo(current.getValue()) == 0) {
            return false;
        }

        storage.addAtNode(current, element);
        return true;
    }

    public E dequeue() {
        return storage.remove(0);
    }

    public E get(int index) {
        return storage.get(index);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
