package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.TestFailed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class LastIndexOfTest extends LinkedListTest {
    private ThreadLocalRandom random;

    public LastIndexOfTest(int runCount) {
        super(runCount);
        random = ThreadLocalRandom.current();
    }

    private void generateRandomizedCollection(Collection<Integer> c, int size) {
        for (int i = 0; i < size; ++i) {
            c.add(random.nextInt(0, 20));
        }
    }

    private void checkEquality(Collection<Integer> lhs, Collection<Integer> rhs) {
        Integer[] correctArray = lhs.toArray(new Integer[0]);
        Integer[] array = rhs.toArray(new Integer[0]);

        if (!Arrays.equals(correctArray, array)) {
            throw new TestFailed("Contents of list and correctAnswer don't match.");
        }
    }

    @Override
    protected void runKernel() throws TestFailed {
        LinkedList<Integer> list = new LinkedList<>();
        ArrayList<Integer> correctAnswer = new ArrayList<>();

        final int size = random.nextInt(sizeLowerBound, sizeUpperBound);
        generateRandomizedCollection(correctAnswer, size);
        list.addAll(correctAnswer);

        final int runCount = random.nextInt(50, 1000);
        for (int i = 0; i < runCount; ++i) {
            final int number = random.nextInt(0, 20);
            int correctIndex = correctAnswer.lastIndexOf(number);
            int index = list.lastIndexOf(number);
            if (correctIndex != index) {
                throw new TestFailed("lastIndexOf(Object o) of list and correctAnswer doesn't match.");
            }
        }
    }
}
