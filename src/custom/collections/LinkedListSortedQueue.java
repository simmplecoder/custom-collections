package custom.collections;

import java.util.ListIterator;

public class LinkedListSortedQueue<E extends Comparable> {
    private LinkedList<E> storage;

    public LinkedListSortedQueue()
    {
        storage = new LinkedList<>();
    }

    public boolean enqueue(E element)
    {
        if (element == null)
        {
            throw new IllegalArgumentException();
        }

        LinkedList.Node current = storage.getRoot();
        if (current == null)
        {
            storage.add(element);
        }

        int index = 0;
        while (current != null && element.compareTo(current.getValue()) > 0)
        {
            current = current.getNext();
            ++index;
        }

        if (current == null)
        {
            storage.add(element);
            return true;
        }

        if (element.compareTo(current.getValue()) == 0)
        {
            return false;
        }

        storage.addAtNode(current, element);
        return true;
    }

    public E dequeue()
    {
        return storage.remove(0);
    }

    public int size()
    {
        return storage.size();
    }

    public boolean isEmpty()
    {
        return storage.isEmpty();
    }
}
