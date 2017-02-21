package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.unit.TestFailed;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AddAtTest extends LinkedListTest{
    private ThreadLocalRandom random;

    public AddAtTest(int runCount)
    {
        super(runCount, "add(int index, E element) method test", "");
        random = ThreadLocalRandom.current();
    }

    @Override
    protected void runKernel() throws TestFailed
    {
        int size = random.nextInt(sizeLowerBound, sizeUpperBound);

        Integer[] correctAnswer = generateArray(size);

        int rangeHead = random.nextInt(0, size - 1);
        int rangeTail = random.nextInt(rangeHead, size - 1);

        LinkedList<Integer> list = new LinkedList<>();

        //fill everything before the range
        list.addAll(Arrays.asList(correctAnswer).subList(0, rangeHead));

        list.addAll(Arrays.asList(correctAnswer).subList(rangeTail + 1, size));

        for (int i = rangeHead; i <= rangeTail; ++i)
        {
            list.add(i, correctAnswer[i]);
        }

        if (!Arrays.equals(list.toArray(new Integer[0]), correctAnswer))
        {
            throw new TestFailed("Either list didn't add elements correctly or array is messed up");
        }

//        int size = 15;
//
//        Integer[] correctAnswer = generateArray(size);
//        int rangeHead = 5;
//        int rangeTail = 10;
//        LinkedList<Integer> list = new LinkedList<>();
//
//        list.addAll(Arrays.asList(correctAnswer).subList(0, rangeHead));
//        list.addAll(Arrays.asList(correctAnswer).subList(rangeTail + 1, size));
//
//        for (int i = rangeHead; i <= rangeTail; ++i)
//        {
//            list.add(i, correctAnswer[i]);
//        }
//
//        if (!Arrays.equals(list.toArray(new Integer[0]), correctAnswer))
//        {
//            throw new TestFailed("Either list didn't add elements correctly or array is messed up");
//        }
    }
}
