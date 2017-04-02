package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.TestFailed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RemoveAtTest extends LinkedListTest {
    ThreadLocalRandom random;

    public RemoveAtTest(int runCount) {
        super(runCount, "remove(int index) method test.", "");
        random = ThreadLocalRandom.current();
    }

    @Override
    protected void runKernel() throws TestFailed {
        ArrayList<Integer> correctAnswer = new ArrayList<>();

        int size = random.nextInt(sizeLowerBound, sizeUpperBound);

        LinkedList<Integer> list = generateLinkedList(size);
        generateCollection(correctAnswer, size);

        int removalCount = random.nextInt(sizeLowerBound - 1, size);

        for (int i = 0; i < removalCount; ++i) {
            int currentSize = correctAnswer.size();
            int nextToRemoveIndex = random.nextInt(0, currentSize);

            list.remove(nextToRemoveIndex);
            correctAnswer.remove(nextToRemoveIndex);
        }

        if (correctAnswer.size() != list.size()) {
            throw new TestFailed("Lists don't have equal size.");
        }

        Integer[] correctAnswerArray = correctAnswer.toArray(new Integer[0]);
        Integer[] listArray = list.toArray(new Integer[0]);

        if (!Arrays.equals(correctAnswerArray, listArray)) {
            throw new TestFailed("lists don't match with contents.");
        }
    }
}
