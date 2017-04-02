package custom.binary_search_tree_tests;

import custom.trees.BinarySearchTree;
import custom.unit.TestFailed;
import custom.unit.UnitTest;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class RemoveTest extends UnitTest{
    private ThreadLocalRandom random;
    private static final int VALUE_UPPER_BOUND = 1000;


    RemoveTest(int runCount) {
        super(runCount, "remove(E value) method test");
        random = ThreadLocalRandom.current();
    }

    private int[] generateRandomizedArray()
    {
        int size = random.nextInt(10, 10000);
        int[] arr = new int[size];

        for (int i = 0; i < size; ++i)
        {
            arr[i] = random.nextInt(0, VALUE_UPPER_BOUND);
        }

        return arr;
    }

    @Override
    protected void runKernel(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Set<Integer> correctAnswer = new TreeSet<>();

        int[] arr = generateRandomizedArray();

        for (int number : arr) {
            tree.add(number);
            correctAnswer.add(number);
        }

        int removeCount = random.nextInt(5, arr.length - 1);
        for (int i = 0; i < removeCount; ++i)
        {
            int val = random.nextInt(0, 30);
            tree.remove(val);
            correctAnswer.remove(val);
        }

        int addCount = random.nextInt(5, VALUE_UPPER_BOUND);
        for (int i = 0; i < addCount; ++i)
        {
            int val = random.nextInt(0, VALUE_UPPER_BOUND);
            tree.add(val);
            correctAnswer.add(val);
        }

        removeCount = random.nextInt(0, tree.size() - 1);
        for (int i = 0; i < removeCount; ++i)
        {
            int val = random.nextInt(0, 40);
            tree.remove(val);
            correctAnswer.remove(val);
        }

        String str = tree.toString();
        String correctStr = correctAnswer.toString();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < correctStr.length(); ++i)
        {
            if (correctStr.charAt(i) != ',')
            {
                builder.append(correctStr.charAt(i));
            }
        }

        correctStr = builder.toString();

        if (!str.equals(correctStr))
        {
            throw new TestFailed("answer and correct answer don't match");
        }
    }
}
