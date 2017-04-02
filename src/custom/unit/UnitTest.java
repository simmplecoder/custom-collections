package custom.unit;

import custom.utilities.ANSIIColors;

import java.util.Scanner;

public abstract class UnitTest {
    private int runCount;
    private String testName;

    @Deprecated
    public UnitTest(int runCount, String testName, String testDescription) {
        if (runCount < 1) {
            throw new IllegalArgumentException();
        }

        this.runCount = runCount;
        this.testName = testName;
    }

    public UnitTest(int runCount, String testName) {
        if (runCount < 1) {
            throw new IllegalArgumentException();
        }

        this.runCount = runCount;
        this.testName = testName;
    }

    public UnitTest(int runCount) {
        this(runCount, "Anonymous test", "");
    }

    private void printPassed() {
        System.out.println(ANSIIColors.green + "Test Passed!" + ANSIIColors.reset);
    }

    private void printTestDescription(String description) {
        System.out.println("Test description: " + ANSIIColors.yellow + description + ANSIIColors.reset);
        System.out.println();
    }

    private void printFailed(String description) {
        System.out.println(ANSIIColors.red + "Test Failed!" + ANSIIColors.reset);
        System.out.println(description);
        System.out.println();
    }

    private void printTestName(String name) {
        System.out.println(ANSIIColors.blue + name + ANSIIColors.reset);
    }

    protected abstract void runKernel() throws TestFailed;

    private boolean getUserResponse(String reason) {
        System.out.println(ANSIIColors.red + "Test case failed. Proceed [Y/N]?" + ANSIIColors.reset);
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();

        while (!response.equals("Y") && !response.equals("y") && !response.equals("N") && !response.equals("n")) {
            System.out.println("Invalid response.");
            response = scanner.next();
        }

        return response.equals("Y") || response.equals("y");
    }

    public void run() throws TestFailed {
        printTestName(testName);

        for (int i = 0; i < runCount; ++i) {
            System.out.println("Going on run " + (i + 1));
            try {
                runKernel();
            } catch (Exception e) {
                printFailed(e.getMessage());

//                if (!getUserResponse("Unexpected exception is thrown."))
//                {
//                    System.out.println("Aborting test ...");
//                    throw e;
//                }
                throw e;
            }
        }

        printPassed();
    }
}
