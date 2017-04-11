package custom.collections;

public class LLQMap<K, V> implements Map<K, V> {
    private LinkedListQueue<KeyValuePair<K, V>> storage;

    public LLQMap()
    {
        storage = new LinkedListQueue<>();
    }

    @Override
    public void define(K key, V value) {
        if (storage.isEmpty())
        {
            storage.enqueue(new KeyValuePair<>(key, value));
        }

        for (int i = 0; i < storage.size(); ++i)
        {
            KeyValuePair<K, V> pair = storage.dequeue();
            if (key.equals(pair.getKey()))
            {
                pair.setValue(value);
                storage.enqueue(pair);
                return;
            }
            storage.enqueue(pair);
        }

        storage.enqueue(new KeyValuePair<>(key, value));
    }

    @Override
    public V getValue(K key) {
        for (int i = 0; i < storage.size(); ++i)
        {
            KeyValuePair<K, V> pair = storage.dequeue();
            if (key.equals(pair.getKey()))
            {
                storage.enqueue(pair);
                return pair.getValue();
            }
            storage.enqueue(pair);
        }

        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < storage.size(); ++i)
        {
            KeyValuePair<K, V> pair = storage.dequeue();
            if (key.equals(pair.getKey()))
            {
                return pair.getValue();
            }
            storage.enqueue(pair);
        }

        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        return storage.dequeue();
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public String toString()
    {
        return storage.toString();
    }
}
