package custom.linked_list_tests;

import custom.collections.LinkedList;
import custom.utilities.ANSIIColors;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleTest {
    private static void printPassed()
    {
        System.out.println(ANSIIColors.green + "Test Passed!" + ANSIIColors.reset);
        System.out.println();
    }

    private static void printFailed()
    {
        printFailed("");
    }

    private static void printFailed(String description)
    {
        System.out.println(ANSIIColors.red + "Test Failed!" + ANSIIColors.reset);
        System.out.println(description);
        System.out.println();
    }

    private static void printTestName(String name)
    {
        System.out.println(ANSIIColors.blue + name + ANSIIColors.reset);
    }

    private static void adderTest()
    {
        printTestName("Item addition test:");

        LinkedList<DummyType> list = new LinkedList<>();

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
        LinkedList<DummyType> list = new LinkedList<>();

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

        LinkedList<DummyType> list = new LinkedList<>();

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

        LinkedList<DummyType> firstList = new LinkedList<>();
        LinkedList<DummyType> secondList = new LinkedList<>();

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

        Integer dummyObject = 12;
        if (firstList.equals(dummyObject))
        {
            printFailed("The list compared to be equal to integer");
        }

        printPassed();
    }

    private static void hashCodeTest()
    {
        printTestName("List hashcode test:");

        LinkedList<DummyType> firstList = new LinkedList<>();
        LinkedList<DummyType> secondList = new LinkedList<>();

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

        LinkedList<DummyType> list = new LinkedList<>();
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

        LinkedList<DummyType> correctList = new LinkedList<>();

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
        LinkedList<DummyType> list = new LinkedList<>();

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
        LinkedList<DummyType> list = new LinkedList<>();

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

        LinkedList<DummyType> list = new LinkedList<>();

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

        LinkedList<DummyType> firstList = new LinkedList<>();
        LinkedList<DummyType> secondList = new LinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            firstList.add(new DummyType(i));
            secondList.add(new DummyType(10 + i));
        }

        System.out.println("firstList = " + firstList.toString());
        System.out.println("secondList = " + secondList.toString());

        System.out.println("Merging lists into firstList:");
        firstList.addAll(secondList);

        LinkedList<DummyType> correctAnswer = new LinkedList<>();
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

        LinkedList<DummyType> list = new LinkedList<>();
        LinkedList<DummyType> correctAnswer = new LinkedList<>();
        for (int i = 0; i < 10; i++)
        {
            list.add(new DummyType(i));
            correctAnswer.add(new DummyType(i));
        }

        LinkedList<DummyType> anotherList = new LinkedList<>();

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

        LinkedList<DummyType> list = new LinkedList<>();
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

    private static void intBasedRemoveTest()
    {
        printTestName("int based remove test:");

        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> correctAnswer = new LinkedList<>();

        for (int i = 0; i < 10; ++i)
        {
            list.add(i);
            correctAnswer.add(i);
        }

        printList(list);
        System.out.println("Trying to remove element on index 3");
        list.remove(3);
        correctAnswer.remove(new Integer(3));
        printList(list);

        if (!list.equals(correctAnswer))
        {
            printFailed("It either didn't remove anything or removed incorrect one");
            throw new RuntimeException();
        }

        printPassed();
    }

    private static void parametrizedtoArrayTest()
    {
        printTestName("Parametrized Array test:");

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 1000; ++i)
        {
            System.out.println("Going on run " + i);
            final int sizeLowerBound = 5;
            final int sizeUpperBound = 5000;
            final int arraySize = random.nextInt(sizeLowerBound, sizeUpperBound);

            Integer[] correctAnswer = new Integer[arraySize];
            LinkedList<Integer> list = new LinkedList<>();

            for (int j = 0; j < arraySize; ++j)
            {
                final int valueLowerBound = 0;
                final int valueUpperBound = 500;
                Integer number = random.nextInt(valueLowerBound, valueUpperBound);
                list.add(number);
                correctAnswer[j] = number;
            }

            int resultArraySize = random.nextInt(sizeLowerBound, arraySize + 1);
            Integer[] result = list.toArray(new Integer[resultArraySize]);

            if (!Arrays.equals(result, correctAnswer))
            {
                printFailed("Resulting array and correct answer doesn't match");
                throw new RuntimeException();
            }
        }

        printPassed();
    }

    private static void retainAllTest()
    {
        printTestName("retainAll test:");

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 1000; ++i)
        {
            System.out.println("Going on run " + (i + 1));

            final int validValuesSizeLowerBound = 5;
            final int validValuesSizeUpperBound = 500;
            final int validValuesArraySize = random.nextInt(validValuesSizeLowerBound,
                    validValuesSizeUpperBound);

            //generate valid values
            Integer[] validValuesArray = new Integer[validValuesArraySize];
            for (int j = 0; j < validValuesArraySize; ++j)
            {
                final int validValuesLowerBound = 0;
                final int validValuesUpperBound = 500;
                validValuesArray[j] = random.nextInt(validValuesLowerBound, validValuesUpperBound);
            }
            Arrays.sort(validValuesArray);

            final int arraySizeLowerBound = validValuesSizeLowerBound;
            final int arraySizeUpperBound = 1000;
            int arraySize = random.nextInt(arraySizeLowerBound, arraySizeUpperBound);
            Integer[] correctAnswer = new Integer[arraySize];
            LinkedList<Integer> list = new LinkedList<>();
            int correctAnswerCount = 0;

            for (int j = 0; j < arraySize; ++j)
            {
                final int valueLowerBound = 0;
                final int valueUpperBound = 1000;
                Integer number = random.nextInt(valueLowerBound, valueUpperBound);
                list.add(number);
                int index = Arrays.binarySearch(validValuesArray, number);
                if (index >= 0)
                {
                    correctAnswer[correctAnswerCount++] = number;
                }
            }

            //fill the rest of the correctAnswer
            for (int j = correctAnswerCount; j < arraySize; ++j)
            {
                correctAnswer[j] = 0;
            }

            list.retainAll(Arrays.asList(validValuesArray));
            Integer[] result = list.toArray(new Integer[0]);
            Arrays.sort(result);
            Arrays.sort(correctAnswer, 0, correctAnswerCount);
            if (result.length != correctAnswerCount)
            {
                printFailed("List doesn't contain correct number of valid values");
                throw new RuntimeException();
            }

//            if (!Arrays.equals(result, 0, correctAnswerCount, correctAnswer, 0, correctAnswerCount))
//            {
//                printFailed("List has correct length but incorrect values in it");
//                throw new RuntimeException();
//            }
        }
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

        intBasedRemoveTest();
        parametrizedtoArrayTest();
        retainAllTest();

        SizeTest szTest = new SizeTest(100, "UnitTest based size test",
                "The test uses new UnitTest class, which aims to improve test quality.");

        AddAtTest addAtTest = new AddAtTest(50);
        RemoveAtTest removeAtTest = new RemoveAtTest(50);
        IndexOfTest indexOfTest = new IndexOfTest(50);
        LastIndexOfTest lastIndexOfTest = new LastIndexOfTest(50);

        try {
            szTest.run();
            addAtTest.run();
            removeAtTest.run();
            indexOfTest.run();
            lastIndexOfTest.run();
        }
        catch (Exception e)
        {

        }
    }

    private static void printList(LinkedList<?> list)
    {
        System.out.println("list = " + list.toString());
    }
}
