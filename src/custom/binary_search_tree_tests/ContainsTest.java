package custom.binary_search_tree_tests;

import custom.unit.TestFailed;
import custom.unit.UnitTest;

import java.util.concurrent.ThreadLocalRandom;

public class ContainsTest extends UnitTest{
    private ThreadLocalRandom random;

    public ContainsTest(int runCount) {
        super(runCount, "contains(E value) method test");
        random = ThreadLocalRandom.current();
    }

    @Override
    protected void runKernel() throws TestFailed {

    }
}
