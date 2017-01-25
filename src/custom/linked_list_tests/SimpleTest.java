package custom.linked_list_tests;

import custom.collections.CustomLinkedList;
import custom.utilities.ANSIIColors;

/**
 * Created by olzhas on 1/25/17.
 */

public class SimpleTest {
    private static void printPassed()
    {
        System.out.println(ANSIIColors.GREEN + "Test Passed!" + ANSIIColors.RESET);
        System.out.println();
    }

    private static void printFailed()
    {
        printFailed("");
    }

    private static void printFailed(String description)
    {
        System.out.println(ANSIIColors.RED + "Test Failed!" + ANSIIColors.RESET);
        System.out.println(description);
        System.out.println();
    }

    private static void printTestName(String name)
    {
        System.out.println(ANSIIColors.BLUE + name + ANSIIColors.RESET);
    }

    private static void adderTest()
    {
        printTestName("Item addition test:");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        System.out.println("Initially list is empty.");
        list.add(new DummyType(12));
        System.out.println("Added DummyType(12): " + list);
        list.add(new DummyType(11));
        System.out.println("Added DummyType(11): " + list);
        System.out.println();
    }

    private static void getterTest()
    {
        printTestName("Item getting test:");
        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        for (int i = 0; i < 10; i++)
        {
            list.add(new DummyType(i));
        }

        System.out.println("List = " + list.toString());

        for (int i = 0; i < 10; i++)
        {
            System.out.println("get(" + i + "): " + list.get(i));
            if (!list.get(i).
                    equals(new DummyType(i)))
            {
                printFailed();
                return;
            }
        }
        printPassed();
    }

    private static void sizeTest()
    {
        printTestName("List size test:");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        final int initSize = 10;
        for (int i = 0; i < initSize; ++i)
        {
            list.add(new DummyType(i));
        }

        System.out.println("List = " + list.toString());
        System.out.println("List size: " + list.size());
        if (list.size() != initSize)
        {
            printFailed();
        }

        final int partialSize = 3;
        for (int i = 0; i < partialSize; ++i)
        {
            list.remove(new DummyType(i));
        }

        System.out.println("List =" + list.toString());
        System.out.println("List size: " + list.size());
        if (list.size() != initSize - partialSize)
        {
            printFailed();
        }

        list.clear();
        System.out.println("List =" + list.toString());
        System.out.println("List size: " + list.size());
        if (list.size() != 0)
        {
            printFailed();
        }

        printPassed();
    }

    private static void equalityTest()
    {
        printTestName("Lists equality test:");

        CustomLinkedList<DummyType> firstList = new CustomLinkedList<>();
        CustomLinkedList<DummyType> secondList = new CustomLinkedList<>();

        //try adding the same 10 items to both
        for (int i = 0; i < 10; ++i)
        {
            firstList.add(new DummyType(i));
            secondList.add(new DummyType(i));
        }

        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());
        if (!firstList.equals(secondList))
        {
            printFailed();
        }

        //try removing one element from first
        firstList.remove(firstList.get(0));
        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());
        if (firstList.equals(secondList)) //should be false
        {
            printFailed("The lists compared to be equal, but in fact they are not");
        }

        printPassed();
    }

    private static void hashCodeTest()
    {
        printTestName("List hashcode test:");

        CustomLinkedList<DummyType> firstList = new CustomLinkedList<>();
        CustomLinkedList<DummyType> secondList = new CustomLinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            firstList.add(new DummyType(i));
            secondList.add(new DummyType(i));
        }

        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());
        System.out.println("firstList hashcode: " + firstList.hashCode());
        System.out.println("secondList hashcode: " + secondList.hashCode());
        if (firstList.hashCode() != secondList.hashCode())
        {
            printFailed("Hashcodes doesn't much, but they should");
        }

        firstList.clear();
        secondList.clear();

        for (int i = 0; i < 10; ++i)
        {
            firstList.add(new DummyType(9 - i));
            secondList.add(new DummyType(i));
        }

        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());
        System.out.println("firstList hashcode: " + firstList.hashCode());
        System.out.println("secondList hashcode: " + secondList.hashCode());
        if (firstList.hashCode() == secondList.hashCode())
        {
            printFailed("Hashcodes shouldn't match, since lists are anagrams of each other");
        }

        printPassed();
    }

    private static void removalTest()
    {
        printTestName("Item removal test:");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();
        for (int i = 0; i < 10; ++i)
        {
            list.add(new DummyType(i));
        }

        System.out.println("List = " + list.toString());



        for (int i = 0; i < 10; i += 2)
        {
            list.remove(new DummyType(i));
            System.out.println("Removing DummyType(" + i + "). List = " + list.toString());
        }

        CustomLinkedList<DummyType> correctList = new CustomLinkedList<>();

        for (int i = 1; i < 10; i += 2)
        {
            correctList.add(new DummyType(i));
        }
        System.out.println("After removal = " +  list.toString());
        System.out.println("Correct Answer = " + correctList.toString());

        if (list.equals(correctList))
        {
            printPassed();
        }
        else
        {
            printFailed();
        }
    }

    public static void main(String[] args)
    {
        adderTest();
        getterTest();

        sizeTest();

        equalityTest();
        hashCodeTest();

        removalTest();

        //System.out.println(list.toString());
    }
}
