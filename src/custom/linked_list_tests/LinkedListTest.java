package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.UnitTest;

import java.util.Collection;

public abstract class LinkedListTest extends UnitTest {
    public final int sizeLowerBound = 5;
    public final int sizeUpperBound = 10000;

    public LinkedListTest(int runCount, String testName, String testDescription) {
        super(runCount, testName, testDescription);
    }

    public LinkedListTest(int runCount) {
        super(runCount);
    }

    protected Collection<Integer> generateCollection(Collection<Integer> c, int size) {
        for (int i = 0; i < size; ++i) {
            c.add(i);
        }

        return c;
    }

    protected LinkedList<Integer> generateLinkedList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; ++i) {
            list.add(i);
        }

        return list;
    }

    protected Integer[] generateArray(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }

        Integer[] result = new Integer[size];

        for (int i = 0; i < size; ++i) {
            result[i] = i;
        }

        return result;
    }


    protected <E> void printList(String prefixText, LinkedList<E> list, String postFix) {
        System.out.println(prefixText + list.toString() + postFix);
    }
}
