package custom.linked_list_sorted_queue_tests;

import custom.collections.LinkedListSortedQueue;
import custom.unit.TestFailed;
import custom.unit.UnitTest;

import java.util.concurrent.ThreadLocalRandom;

public class EnqueueTest extends UnitTest {
    private ThreadLocalRandom random;

    public EnqueueTest(int runCount) {
        super(runCount, "Enqueuing test");
        random = ThreadLocalRandom.current();
    }

    @Override
    protected void runKernel() throws TestFailed {
        final int sizeLowerBound = 100;
        final int sizeUpperBound = 10000;

        int size = random.nextInt(sizeLowerBound, sizeUpperBound);
        LinkedListSortedQueue<Integer> sortedQueue = new LinkedListSortedQueue<>();

        for (int i = 0; i < size; ++i)
        {
            final int valueLowerBound = 0;
            final int valueUpperBound = 100;
            sortedQueue.enqueue(random.nextInt(valueLowerBound, valueUpperBound));
        }

        Integer prev = sortedQueue.dequeue();
        Integer current;
        while (sortedQueue.isEmpty())
        {
            current = sortedQueue.dequeue();
            if (prev.compareTo(current) >= 0)
            {
                throw new TestFailed("queue is not sorted");
            }
        }
    }
}
