package custom.linked_list_tests;

import custom.collections.CustomLinkedList;
import custom.utilities.ANSIIColors;

import java.util.Iterator;

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

    private static void containsTest()
    {
        printTestName("Contains test");
        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            list.add(new DummyType(i));
        }

        System.out.println("list = " + list.toString());

        boolean result = list.contains(new DummyType(5));
        if (!result)
        {
            printFailed();
        }

        System.out.println("contains(new DummyType(5)) = " + result);

        result =list.contains(new DummyType(2));
        if (!result)
        {
            printFailed();
        }

        System.out.println("contains(new DummyType(2)) = " + result);
        printPassed();
    }

    private static void iteratorTraversingTest()
    {
        printTestName("Iterator traversing test");
        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            list.add(new DummyType(i));
        }

        printList(list);

        Iterator<DummyType> listIterator = list.iterator();

        int[] correctResult1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        iteratorTraversePrintElements(listIterator, correctResult1);

        //try to remove elements in the middle and rerun
        System.out.println("Removing elements at 2, 3, 4 and rerunning:\n");

        list.remove(new DummyType(2));
        list.remove(new DummyType(3));
        list.remove(new DummyType(4));

        listIterator = list.iterator(); //reset the iterator
        int[] correctResult2 = {0, 1, 5, 6, 7, 8, 9};
        iteratorTraversePrintElements(listIterator, correctResult2);

        printPassed();
    }

    private static void iteratorTraversePrintElements(Iterator<DummyType> listIterator, int[] correctResult) {
        for (int i = 0; i < correctResult.length; ++i)
        {
            DummyType current = listIterator.next();
            System.out.println("Iterator.next() = " + current.toString());
            if (!current.equals(new DummyType(correctResult[i])))
            {
                printFailed("Iterator doesn't properly return underlying elements");
            }
        }
    }

    private static void iteratorRemovalTest()
    {
        printTestName("Iterator remove method test:");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        for (int i = 0; i < 10 ; ++i)
        {
            list.add(new DummyType(i));
        }

        System.out.println("list = " + list.toString());
        Iterator<DummyType> listIterator = list.iterator();


        for (int i = 0; i < 3; ++i)
        {
            listIterator.remove();
            listIterator.next();
        }

        System.out.println("Trying to remove 3 elements from the beginning: list = " + list.toString());

        for (int i = 0; i < 3; ++i)
        {
            listIterator.next();
        }

        listIterator.remove();
        listIterator.next(); //nullified due to previous remove
        System.out.println("Trying to remove the 7th element DummyType(6): list = " + list.toString());


        listIterator.next();
        listIterator.next();

        listIterator.remove();
        System.out.println("Trying to remove at the end: list = " + list.toString());

        printPassed();
    }

    private static void addAllTest()
    {
        printTestName("Add all test:");

        CustomLinkedList<DummyType> firstList = new CustomLinkedList<>();
        CustomLinkedList<DummyType> secondList = new CustomLinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            firstList.add(new DummyType(i));
            secondList.add(new DummyType(10 + i));
        }

        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());

        System.out.println("Merging lists into firstList:");
        firstList.addAll(secondList);

        CustomLinkedList<DummyType> correctAnswer = new CustomLinkedList<>();
        for (int i = 0; i < 20; ++i)
        {
            correctAnswer.add(new DummyType(i));
        }

        printList(firstList);
        if (!firstList.equals(correctAnswer))
        {
            printFailed();
        }

        System.out.println("clearing second list, " +
                "adding 47 and merging with firstlist again");
        secondList.clear();
        secondList.add(new DummyType(47));
        firstList.addAll(secondList);
        correctAnswer.add(new DummyType(47));
        printList(firstList);
        if (!firstList.equals(correctAnswer))
        {
            printFailed("List doesn't match correct Answer." +
                    " Correct answer is: " + correctAnswer.toString());
            return;
        }

        printPassed();
    }

    private static void removeAllTest()
    {
        printTestName("removeAllTest:");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();
        CustomLinkedList<DummyType> correctAnswer = new CustomLinkedList<>();
        for (int i = 0; i < 10; i++)
        {
            list.add(new DummyType(i));
            correctAnswer.add(new DummyType(i));
        }

        CustomLinkedList<DummyType> anotherList = new CustomLinkedList<>();

        anotherList.add(new DummyType(6));
        anotherList.add(new DummyType(7));

        System.out.println("Removing all elements from list that are equal to elements" +
                "in anotherList.");
        printList(list);
        System.out.println("Another list = " + anotherList.toString());
        boolean hasChanged = list.removeAll(anotherList);
        System.out.println("result: list = " + list.toString());
        System.out.println("List has changed contents: " + hasChanged);

        correctAnswer.remove(new DummyType(6));
        correctAnswer.remove(new DummyType(7));

        if (!list.equals(correctAnswer))
        {
            printFailed("List doesn't match correct answer. " +
                    "Correct answer is = " + correctAnswer.toString());
            return;
        }

        if (!hasChanged)
        {
            printFailed("List did change " +
                    "but result of the function doesn't indicate that.");
            return;
        }

        System.out.println("Trying to remove non existent elements now:");
        anotherList.clear();
        anotherList.add(new DummyType(100));
        anotherList.add(new DummyType(19));
        printList(list);
        System.out.println("Another list = " + anotherList.toString());

        hasChanged = list.removeAll(anotherList);

        System.out.println("result: list = " + list.toString());
        System.out.println("List has changed contents: " + hasChanged);

        if (!list.equals(correctAnswer))
        {
            printFailed("List doesn't match correct answer. " +
                    "Correct answer is = " + correctAnswer.toString());
            return;
        }

        if (hasChanged)
        {
            printFailed("List did not change " +
                    "but result of the function says that it did.");
            return;
        }

        printPassed();
    }

    private static void toArrayTest()
    {
        printTestName("toArray method test (no argument version):");

        CustomLinkedList<DummyType> list = new CustomLinkedList<>();
        for (int i = 0; i < 5; ++i)
        {
            list.add(new DummyType(i));
        }

        System.out.println("Outputting list.toArray() with 5 elements:");
        printList(list);
        Object[] array = list.toArray();

        if (array.length != list.size())
        {
            printFailed("At least size of the array and list don't match");
            return;
        }

        System.out.print("Array = [");
        DummyType element = (DummyType) array[0];
        System.out.print(element.toString());
        for (int i = 1; i < array.length; ++i)
        {
            System.out.print(' ');
            element = (DummyType) array[i];
            if (!element.equals(list.get(i)))
            {
                printFailed("Element of list at " + i +
                        " doesn't match wih the element in array at the same index");
                return;
            }
            System.out.print(element.toString());
        }
        System.out.println(']');

        printPassed();
    }

    public static void main(String[] args)
    {
        adderTest();
        getterTest();

        sizeTest();

        equalityTest();
        hashCodeTest();

        removalTest();

        iteratorTraversingTest();
        iteratorRemovalTest();
        containsTest();
        addAllTest();
        removeAllTest();
        toArrayTest();
    }

    private static void printList(CustomLinkedList<DummyType> list)
    {
        System.out.println("list = " + list.toString());
    }
}
