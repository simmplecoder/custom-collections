package custom.collections;

public interface Queue<E> {
    void enqueue(E element);

    E dequeue();

    int size();

    void clear();

    boolean isEmpty();
}
