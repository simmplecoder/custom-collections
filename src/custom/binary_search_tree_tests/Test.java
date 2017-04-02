package custom.binary_search_tree_tests;

import custom.trees.BinarySearchTree;

import java.util.Arrays;

public class Test {

    public static void main(String[] args)
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Integer[] arr = {5, 12, 3, 6, 14, 2, -6, 8, 0};

        for (Integer anArr : arr) {
            tree.add(anArr);
        }

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        tree.add(5);
        tree.add(14);
        tree.add(-6);

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        Integer inputs[] = {12, 7, 2, -8, 5};
        Arrays.sort(arr);

        for (Integer input : inputs) {
            boolean isPresent = Arrays.binarySearch(arr, input) >= 0;
            if (tree.contains(input) != isPresent) {
                System.out.println("Test failed");
                System.exit(-1);
            }
        }

        tree.remove(0);
        tree.remove(6);
        tree.remove(3);

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        int anotherArr[] = {6, 13, 99, 11};
        for (Integer anAnotherArr : anotherArr) {
            tree.add(anAnotherArr);
        }

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        tree.remove(12);

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        tree.remove(5);

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        tree.removeAny();
        tree.removeAny();
        tree.removeAny();

        System.out.println("tree: " + tree.toString());
        System.out.println("tree size: " + tree.size());
        System.out.println();

        System.out.println("starting randomized tests...");
        RemoveTest rmTest = new RemoveTest(10000);
        rmTest.run();
    }
}
