package custom.collections;

public interface SortedQueue<E> {
    boolean insert(E element);

    E dequeue();

    E get(int index);

    int size();

    void clear();

    boolean isEmpty();
}
