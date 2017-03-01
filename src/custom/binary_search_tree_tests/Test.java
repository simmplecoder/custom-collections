package custom.binary_search_tree_tests;

import custom.trees.BinarySearchTree;

public class Test {

    public static void main(String[] args)
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(4);

        for (int i = 0; i < 10; ++i)
        {
            tree.add(i);
        }

        System.out.println(tree.toString());
    }
}
