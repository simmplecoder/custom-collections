package custom.collections;

public class LLHashSet<E> implements Set<E>, HashTableStats{
    private LinkedList<E>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public LLHashSet(int bucketCount)
    {
        if (bucketCount <= 0)
        {
            throw new IllegalArgumentException("bucketCount cannot be zero");
        }

//        buckets = (LinkedList<E>[]) new Object[bucketCount];
        buckets = new LinkedList[bucketCount];
        size = 0;
        for (int i = 0; i < buckets.length; ++i)
        {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean add(E element) {
        if (element == null)
        {
            throw new IllegalArgumentException("element cannot be null");
        }

        int bucketIndex = element.hashCode() % buckets.length;

        if (buckets[bucketIndex].contains(element))
        {
            return false;
        }

        buckets[bucketIndex].add(0, element);
        ++size;

        return true;
    }

    @Override
    public boolean remove(E value) {
        int bucketIndex = value.hashCode() % buckets.length;

        if (buckets[bucketIndex].remove(value))
        {
            --size;
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        buckets = (LinkedList<E>[]) new Object[buckets.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E value) {
//        for (LinkedList<E> bucket : buckets) {
//            if (bucket.contains(value)) {
//                return true;
//            }
//        }
        int hashCode = value.hashCode() % buckets.length;
        return buckets[hashCode].contains(value);
    }

    public double getLoadFactor()
    {
        return (double)size / buckets.length;
    }

    public double getBucketSizeStandardDev()
    {
        int sum = 0;
        for (int i = 0; i < buckets.length; ++i)
        {
            sum += buckets[i].size();
        }
        double mean = sum / buckets.length;

        double devsum = 0;
        for (int i = 0; i < buckets.length; ++i)
        {
            devsum += (buckets[i].size() - mean) * (buckets[i].size() - mean);
        }

        return Math.sqrt(devsum / (buckets.length - 1));
    }

    @Override
    public E removeAny() {
        int counter = 0;
        while (buckets[counter].isEmpty())
        {
            ++counter;
        }

        if (counter == buckets.length)
        {
            throw new IllegalStateException("trying to call removeAny() on empty set");
        }

        return buckets[counter].remove(0);
    }
}
