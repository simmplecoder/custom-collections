package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.TestFailed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class IndexOfTest extends LinkedListTest {
    ThreadLocalRandom random;

    public IndexOfTest(int runCount) {
        super(runCount, "indexOf(Object o) method test.", "");
        random = ThreadLocalRandom.current();
    }

    private void generateRandomizedCollection(Collection<Integer> c, int size) {
        for (int i = 0; i < size; ++i) {
            c.add(random.nextInt(0, 20));
        }
    }

    private void checkListsEquality(LinkedList<Integer> lhs, ArrayList<Integer> rhs) {
        Integer[] correctArray = rhs.toArray(new Integer[0]);
        Integer[] array = lhs.toArray(new Integer[0]);

        if (!Arrays.equals(correctArray, array)) {
            throw new TestFailed("Lists wasn't generated to be equal.");
        }
    }

    @Override
    protected void runKernel() throws TestFailed {
        int size = random.nextInt(sizeLowerBound, sizeUpperBound);
        ArrayList<Integer> correctAnswer = new ArrayList<>();

//        LinkedList<Integer> list = generateLinkedList(size);
//        generateCollection(correctAnswer, size);
        generateRandomizedCollection(correctAnswer, size);
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(correctAnswer);

        checkListsEquality(list, correctAnswer);

        final int sampleCountLowerBound = 10;
        final int sampleCountUpperBound = 500;
        int sampleCount = random.nextInt(sampleCountLowerBound, sampleCountUpperBound);

        for (int i = 0; i < sampleCount; ++i) {
            int number = random.nextInt(0, size); //since range of values i [0, size] by generateXXX function
            int correctIndex = correctAnswer.indexOf(number);
            int returnedIndex = list.indexOf(number);

            if (correctIndex != returnedIndex) {
                throw new TestFailed("Indexes returned from correctAnswer and list don't match");
            }
        }
    }
}
