package custom.collections;

import custom.trees.BinarySearchTree;

public class BSTHashSet<E extends Comparable<E>> implements Set<E>, HashTableStats {
    private BinarySearchTree<E>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public BSTHashSet(int bucketCount)
    {
        if (bucketCount <= 0)
        {
            throw new IllegalArgumentException("bucketCount cannot be zero");
        }

        buckets = new BinarySearchTree[bucketCount];
        size = 0;
        for (int i = 0; i < buckets.length; ++i)
        {
            buckets[i] = new BinarySearchTree<>();
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

        buckets[bucketIndex].add(element);
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
        buckets = (BinarySearchTree<E>[]) new Object[buckets.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E value) {
//        for (BinarySearchTree<E> bucket : buckets) {
//            if (bucket.contains(value)) {
//                return true;
//            }
//        }
//
//        return false;
        int hashCode = value.hashCode() % buckets.length;
        return buckets[hashCode].contains(value);
    }

    public double getLoadFactor()
    {
        return size / buckets.length;
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

        return buckets[counter].removeAny();
    }
}
