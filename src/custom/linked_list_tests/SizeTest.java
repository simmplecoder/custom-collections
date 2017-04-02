package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.TestFailed;
import custom.unit.UnitTest;

import java.util.concurrent.ThreadLocalRandom;

public class SizeTest extends UnitTest {
    private ThreadLocalRandom random;

    public SizeTest(int runCount, String testName, String testDescription) {
        super(runCount, testName, testDescription);
        random = ThreadLocalRandom.current();
    }

    public SizeTest(int runCount) {
        super(runCount);
        random = ThreadLocalRandom.current();
    }

    protected void runKernel() throws TestFailed {
        final int sizeLowerBound = 5;
        final int sizeUpperBound = 10000;

        LinkedList<Integer> list = new LinkedList<>();

        int size = random.nextInt(sizeLowerBound, sizeUpperBound);

        for (int i = 0; i < size; ++i) {
            list.add(0);
        }

        if (list.size() != size) {
            throw new TestFailed("size() doesn't return the actual size.");
        }

        //get new size
        size = random.nextInt(sizeLowerBound, sizeUpperBound);
        if (size > list.size()) {
            int currentSize = list.size();
            for (int i = currentSize; i < size; ++i) {
                list.add(0);
            }
        } else {
            int currentSize = list.size();
            for (int i = currentSize; i > size; --i) {
                list.remove(list.size() - 1);
            }
        }

        if (list.size() != size) {
            throw new TestFailed("size() didn't return correct number after modifications.");
        }
    }
}
