package custom.collections;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by olzhas on 1/25/17.
 */
public class CustomStack<T> {
    private LinkedList<T> storage;
    int size;

    public void push(T object)
    {
        storage.add(object);
    }

    public T pop()
    {
        if (storage.size() == 0)
        {
            throw new EmptyStackException();
        }

        return storage.pop();
    }
}
