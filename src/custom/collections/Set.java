package custom.collections;

public interface Set<E> {
    boolean add(E element);
    boolean remove(E value);
    void clear();
    int size();
    boolean contains(E value);
    E removeAny();
}
